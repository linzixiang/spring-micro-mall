package com.linzx.core.web.base;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.map.MapProxy;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageHelper;
import com.linzx.core.common.bean.UserLoginPrincipal;
import com.linzx.core.common.constant.WebSessionConstant;
import com.linzx.core.common.service.DictOptionsService;
import com.linzx.core.framework.base.annotation.Excel;
import com.linzx.core.framework.base.annotation.ExcelColumn;
import com.linzx.core.framework.context.ContextManager;
import com.linzx.core.framework.support.xml.bean.DictBean;
import com.linzx.core.web.base.vo.DictOption;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 控制层基类
 */
public abstract class BaseController {

    public static final String PAGE_NUM = "pageNum"; // 第几页
    public static final String PAGE_SIZE = "pageSize"; // 每页大小
    public static final int PAGE_SIZE_DEFAULT = 20; // 默认分页大小
    public static final String ORDER_DIRECTION = "orderDirection"; // 排序方向参数
    public static final String ORDER_DIRECTION_DEFAULT = "asc"; // 默认排序方向
    public static final String ORDER_BY_COLUMN = "orderColumn"; // 排序字段

    @Autowired
    private DictOptionsService dictOptionService;

    /**
     * 开始分页
     */
    public void startPage() {
        MapProxy proxy = MapUtil.createProxy(ServletUtil.getParamMap(getRequest()));
        Integer pageNum = proxy.getInt(PAGE_NUM, 1);
        Integer pageSize = proxy.getInt(PAGE_SIZE, PAGE_SIZE_DEFAULT);
        String orderCol = proxy.getStr(ORDER_BY_COLUMN, "");
        if (StrUtil.isBlank(orderCol)) {
            // 不需要排序
            PageHelper.startPage(pageNum, pageSize);
        } else {
            String orderDirection = proxy.getStr(ORDER_DIRECTION, ORDER_DIRECTION_DEFAULT);
            PageHelper.startPage(pageNum, pageSize, orderCol + " " + orderDirection);
        }
    }

    /**
     * 获取request
     */
    public HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    public UserLoginPrincipal getUserLoginPrincipal() {
        HttpSession session = getRequest().getSession();
        Object loginPrincipal = session.getAttribute(WebSessionConstant.USER_LOGIN_PRINCIPAL);
        if (loginPrincipal != null) {
            return (UserLoginPrincipal) loginPrincipal;
        }
        return null;
    }

    /**
     * 导出Excel
     * @param dataList 数据集
     * @param clz 对象，属性用@ExcelColumn注解表示需要导出的列
     */
    protected void exportExcel(List<Map<String, Object>> dataList, Class<?> clz) throws IOException {
        Excel excel = AnnotationUtil.getAnnotation(clz, Excel.class);
        if (excel == null) { return; }
        Map<String, String> dictMap = new HashMap<>();
        List<Field> exportFields = new ArrayList<>();
        for (Field field :  ReflectUtil.getFields(clz)) {
            ExcelColumn excelColumn = AnnotationUtil.getAnnotation(field, ExcelColumn.class);
            if (excelColumn == null) {
                continue;
            }
            if (StrUtil.isNotBlank(excelColumn.dictCode())) {
                DictBean dictBean = ContextManager.getDictBeanCopy(excelColumn.dictCode());
                List<DictOption> dictOptions = dictOptionService.queryDict(dictBean);
                for (DictOption dictOption : dictOptions) {
                    dictMap.put(excelColumn.dictCode() + "." + dictOption.getDictValue(), dictOption.getDictLabel());
                }
            }
            exportFields.add(field);
        }
        if (CollectionUtil.isEmpty(exportFields)) {
            return;
        }
        CollectionUtil.sort(exportFields, new Comparator<Field>() {
            @Override
            public int compare(Field field1, Field field2) {
                ExcelColumn field1Col = AnnotationUtil.getAnnotation(field1, ExcelColumn.class);
                ExcelColumn field2Col = AnnotationUtil.getAnnotation(field2, ExcelColumn.class);
                if (field1Col.orderNum() == field2Col.orderNum())  {
                    return 0;
                } else if (field1Col.orderNum() > field2Col.orderNum()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        String sheetName = StrUtil.isNotBlank(excel.sheetName()) ? excel.sheetName() : excel.fileName();
        ExcelWriter writer = ExcelUtil.getWriterWithSheet(sheetName);
        // 写出表头
        List<String> headerRow = new ArrayList<>();
        for (Field field : exportFields) {
            ExcelColumn excelColumn = AnnotationUtil.getAnnotation(field, ExcelColumn.class);
            headerRow.add(excelColumn.name());
        }
        writer.writeHeadRow(headerRow);
        for (Map<String, Object> rowDataMap : dataList) {
            List<Object> row = new ArrayList<>();
            for (Field field : exportFields) {
                ExcelColumn excelColumn = AnnotationUtil.getAnnotation(field, ExcelColumn.class);
                Object value = MapUtil.get(rowDataMap, field.getName(), field.getType());
                if (StrUtil.isNotBlank(excelColumn.dictCode())) { // 如果是字典
                    value = MapUtil.getStr(dictMap, excelColumn.dictCode() + "." + value);
                }
                if (value instanceof Date) { // 日期类型格式化
                    value = DateUtil.format((Date) value, excelColumn.dateFormat());
                }
                row.add(value);
            }
            writer.writeRow(row);
        }
        HttpServletResponse response = getResponse();
        response.setContentType(ContentType.MULTIPART.toString());
        response.setHeader(Header.CONTENT_DISPOSITION.toString(), "attachment;fileName="+ excel.fileName() + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        writer.close();
        outputStream.flush();
    }

}

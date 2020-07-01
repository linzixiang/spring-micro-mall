package com.linzx.core.common.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapProxy;
import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.linzx.core.common.service.DictOptionsService;
import com.linzx.core.common.service.TableCodeService;
import com.linzx.core.framework.context.ContextManager;
import com.linzx.core.framework.support.xml.bean.CodeBean;
import com.linzx.core.framework.support.xml.bean.DictBean;
import com.linzx.core.web.base.BaseController;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import com.linzx.core.web.base.vo.DictOption;
import com.linzx.core.web.base.vo.DictVo;
import com.linzx.core.web.base.vo.TableCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用Controller
 */
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {

    @Autowired
    private TableCodeService tableCodeService;

    @Autowired
    private DictOptionsService dictOptionService;

    /**
     * 查询表字典
     * @param code 编码
     * @param searchKey 关键字
     * @return
     */
    @RequestMapping("/queryCode")
    public CommonAjaxResult queryCode(@RequestParam(name = "code") String code,
                                      @RequestParam(name = "searchKey", required = false) String searchKey,
                                      @RequestParam(name = "pageEnable", defaultValue = "true", required = false)String pageEnable) {
        MapProxy proxy = MapUtil.createProxy(ServletUtil.getParamMap(getRequest()));
        Integer pageNum = proxy.getInt(PAGE_NUM, 1);
        Integer pageSize = proxy.getInt(PAGE_SIZE, PAGE_SIZE_DEFAULT);
        CodeBean codeBean = ContextManager.getCodeBeanCopy(code);
        if (codeBean == null) {
            return CommonAjaxResult.fail("code.not.exist.error", code);
        }
        codeBean.setPageNum(pageNum);
        codeBean.setPageSize(pageSize);
        codeBean.setSearchKey(searchKey);
        codeBean.setPageEnable(Convert.toBool(pageEnable, true));
        List<TableCode> tableCodeList = tableCodeService.queryTableCode(codeBean);
        return CommonAjaxResult.ok().setResult(tableCodeList);
    }

    /**
     * 查询字典，支持批量操作
     * @param code 编码
     * @return
     */
    @RequestMapping("/queryDict")
    public CommonAjaxResult queryDict(@RequestParam("code") String[] code) {
        List<DictVo> dictOptionList = new ArrayList<>();
        for (String codeStr : code) {
            DictBean dictBean = ContextManager.getDictBeanCopy(codeStr);
            if (dictBean == null) {
                continue;
            }
            List<DictOption> dictOptions = dictOptionService.queryDict(dictBean);
            DictVo dictVo = new DictVo();
            dictVo.setCodeName(codeStr);
            dictVo.setOptions(dictOptions);
            dictOptionList.add(dictVo);
        }
        return CommonAjaxResult.ok().setResult(dictOptionList);
    }


}

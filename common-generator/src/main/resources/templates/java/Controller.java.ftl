package ${basePackageName}.${projectName}.${moduleName}.controller;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.Page;
import ${basePackageName}.${projectName}.${moduleName}.convert.${className}Convert;
import ${basePackageName}.${projectName}.${moduleName}.domain.${className};
import ${basePackageName}.${projectName}.${moduleName}.dto.${className?uncap_first}.request.${className}AddReq;
import ${basePackageName}.${projectName}.${moduleName}.dto.${className?uncap_first}.request.${className}EditReq;
import ${basePackageName}.${projectName}.${moduleName}.dto.${className?uncap_first}.request.${className}ListReq;
import ${basePackageName}.${projectName}.${moduleName}.dto.${className?uncap_first}.response.${className}ListRes;
import ${basePackageName}.${projectName}.${moduleName}.dto.${className?uncap_first}.response.${className}EditRes;
import ${basePackageName}.${projectName}.${moduleName}.service.I${className}Service;
import com.linzx.core.security.authz.RequiresPermissions;
import ${basePackageName}.core.web.base.BaseController;
import ${basePackageName}.core.web.base.vo.CommonAjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ${tableComment}
 * @author ${author}
 * @date ${datetime}
 */
@RestController
@RequestMapping("/${moduleName}/${classname}")
public class ${className}Controller extends BaseController {

    @Autowired
    private I${className}Service ${classname}Service;
	
	@Autowired
    private ${className}Convert ${classname}Convert;

    /**
     * ${tableComment} 列表
     */
	@RequiresPermissions("${moduleName}:${classname}:list")
    @RequestMapping("/list")
    public CommonAjaxResult list(${className}ListReq ${classname}ListReq) {
        startPage();
		List<${className}> ${classname}List = select${className}List(${classname}ListReq);
        Page page = (Page) ${classname}List;
        List<${className}ListRes> dataList = new ArrayList<>();
        for (${className} ${classname} : ${classname}List) {
            // TODO 数据转换：${className} => ${className}ListRes
			${className}ListRes ${classname}ListRes = ${classname}Convert.${classname}2${className}ListRes(${classname});
            dataList.add(${classname}ListRes);
        }
        return CommonAjaxResult.ok().setListResult(dataList, page);
    }
	
	/**
     * 导出或列表 数据查询
     */
    private List<${className}> select${className}List(${className}ListReq ${classname}ListReq) {
		// TODO 数据转换：${className}ListReq => ${className}
        ${className} ${classname}Param = ${classname}Convert.${classname}ListRes2${className}(${classname}ListReq);
        return ${classname}Service.selectList(${classname}Param).get();
    }

    /**
     * ${tableComment} 新增查询
     */
	@RequiresPermissions("${moduleName}:${classname}:add")
    @GetMapping("/preAdd")
    public CommonAjaxResult preAdd() {
        return CommonAjaxResult.ok();
    }

	/**
     * ${tableComment} 新增保存
     */
	@RequiresPermissions("${moduleName}:${classname}:add")
    @PostMapping("/saveAdd")
    public CommonAjaxResult saveAdd(@Validated ${className}AddReq ${classname}AddReq) {
        // TODO 数据转换：${className}AddReq => ${className}
		${className} ${classname} = ${classname}Convert.${classname}AddReq2${className}(${classname}AddReq);
        ${classname}Service.insert(${classname});
        return CommonAjaxResult.ok().setResult(${classname}.getId());
    }

    /**
     * ${tableComment} 修改查询
     */
	@RequiresPermissions("${moduleName}:${classname}:edit")
    @GetMapping("/preEdit/{${classname}Id}")
    public CommonAjaxResult preEdit(@PathVariable("${classname}Id") Long ${classname}Id) {
        ${className} ${classname} = ${classname}Service.getById(${classname}Id).get();
        // TODO 数据转换：${className} => ${className}EditRes
		${className}EditRes ${classname}EditRes = ${classname}Convert.${classname}2${className}EditRes(${classname});
        return CommonAjaxResult.ok().setResult(${classname}EditRes);
    }

	/**
     * ${tableComment} 修改保存
     */
	@RequiresPermissions("${moduleName}:${classname}:edit")
    @PostMapping("/saveEdit")
    public CommonAjaxResult saveEdit(@Validated ${className}EditReq ${classname}EditReq) {
        // TODO 数据转换：${className}EditReq => ${className}
		${className} ${classname} = ${classname}Convert.${classname}EditReq2${className}(${classname}EditReq);
        ${classname}Service.update(${classname});
        return CommonAjaxResult.ok();
    }

    /**
     * ${tableComment} 删除，例如${classname}Id=1&${classname}Id=2
     */
	@RequiresPermissions("${moduleName}:${classname}:remove")
    @PostMapping("/remove")
    public CommonAjaxResult remove(Long[] ${classname}Id) {
        ${classname}Service.deleteByIds(${classname}Id);
        return CommonAjaxResult.ok();
    }
	
	/**
     * ${tableComment} 导出
     */
	@RequiresPermissions("${moduleName}:${classname}:export")
	@RequestMapping("/export")
    public void export(${className}ListReq ${classname}ListReq) throws Exception {
        // 数据集准备
        List<${className}> ${classname}List = select${className}List(${classname}ListReq);
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (${className} ${classname} : ${classname}List) {
            ${className}ListRes ${classname}ListRes = ${classname}Convert.${classname}2${className}ListRes(${classname});
            dataList.add(BeanUtil.beanToMap(${classname}ListRes));
        }
        exportExcel(dataList, ${className}ListRes.class);
    }
	
}

package com.linzx.admin.system.controller;

import com.github.pagehelper.Page;
import com.linzx.admin.system.convert.DictOptionConvert;
import com.linzx.admin.system.domain.DictOption;
import com.linzx.admin.system.dto.dictOption.request.DictOptionAddReq;
import com.linzx.admin.system.dto.dictOption.request.DictOptionEditReq;
import com.linzx.admin.system.dto.dictOption.request.DictOptionListReq;
import com.linzx.admin.system.dto.dictOption.response.DictOptionListRes;
import com.linzx.admin.system.dto.dictOption.response.DictOptionEditRes;
import com.linzx.admin.system.service.IDictOptionService;
import com.linzx.core.web.base.BaseController;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典选项
 * @author linzixiang
 * @date 2020-06-10 15:23:11
 */
@RestController
@RequestMapping("/system/dictOption")
public class DictOptionController extends BaseController {

    @Autowired
    private IDictOptionService dictOptionService;

    @Autowired
    private DictOptionConvert dictOptionConvert;

    /**
     * 字典选项  列表
     */
    @RequestMapping("/list")
    public CommonAjaxResult list(DictOptionListReq dictOptionListReq) {
        startPage();
        // TODO 数据转换：DictOptionListReq => DictOption
        DictOption dictOptionParam = dictOptionConvert.dictOptionListRes2DictOption(dictOptionListReq);
        List<DictOption> dictOptionList = dictOptionService.selectList(dictOptionParam).get();
        Page page = (Page) dictOptionList;
        List<DictOptionListRes> dataList = new ArrayList<>();
        for (DictOption dictOption : dictOptionList) {
            // TODO 数据转换：DictOption => DictOptionListRes
            DictOptionListRes dictOptionListRes = dictOptionConvert.dictOption2DictOptionListRes(dictOption);
            dataList.add(dictOptionListRes);
        }
        return CommonAjaxResult.ok().setListResult(dataList, page);
    }

    /**
     * 字典选项  新增查询
     */
    @GetMapping("/preAdd")
    public CommonAjaxResult preAdd() {
        return CommonAjaxResult.ok();
    }

    /**
     * 字典选项  新增保存
     */
    @PostMapping("/saveAdd")
    public CommonAjaxResult saveAdd(DictOptionAddReq dictOptionAddReq) {
        // TODO 数据转换：DictOptionAddReq => DictOption
        DictOption dictOption = dictOptionConvert.dictOptionAddReq2DictOption(dictOptionAddReq);
        dictOptionService.insert(dictOption);
        return CommonAjaxResult.ok().setResult(dictOption.getId());
    }

    /**
     * 字典选项  修改查询
     */
    @GetMapping("/preEdit/{dictOptionId}")
    public CommonAjaxResult preEdit(@PathVariable("dictOptionId") Long dictOptionId) {
        DictOption dictOption = dictOptionService.getById(dictOptionId).get();
        // TODO 数据转换：DictOption => DictOptionEditRes
        DictOptionEditRes dictOptionEditRes = dictOptionConvert.dictOption2DictOptionEditRes(dictOption);
        return CommonAjaxResult.ok().setResult(dictOptionEditRes);
    }

    /**
     * 字典选项  修改保存
     */
    @PostMapping("/saveEdit")
    public CommonAjaxResult saveEdit(DictOptionEditReq dictOptionEditReq) {
        // TODO 数据转换：DictOptionEditReq => DictOption
        DictOption dictOption = dictOptionConvert.dictOptionEditReq2DictOption(dictOptionEditReq);
        dictOptionService.update(dictOption);
        return CommonAjaxResult.ok();
    }

}

package com.linzx.admin.system.controller;

import com.github.pagehelper.Page;
import com.linzx.admin.system.convert.DictConvert;
import com.linzx.admin.system.domain.Dict;
import com.linzx.admin.system.dto.dict.request.DictAddReq;
import com.linzx.admin.system.dto.dict.request.DictEditReq;
import com.linzx.admin.system.dto.dict.request.DictListReq;
import com.linzx.admin.system.dto.dict.response.DictDetailRes;
import com.linzx.admin.system.dto.dict.response.DictEditRes;
import com.linzx.admin.system.dto.dict.response.DictListRes;
import com.linzx.admin.system.dto.dict.response.DictAddRes;
import com.linzx.admin.system.service.IDictService;
import com.linzx.core.security.authz.RequiresPermissions;
import com.linzx.core.web.base.BaseController;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典信息
 * @author linzixiang
 * @date 2020-05-18 21:26:47
 */
@RestController
@RequestMapping("/system/dict")
public class DictController extends BaseController {

    @Autowired
    private IDictService dictService;

    @Autowired
    private DictConvert dictConvert;

    /**
     * 获取字典列表
     */
    @RequiresPermissions("system:dict:list")
    @RequestMapping("/list")
    public CommonAjaxResult list(DictListReq dictListReq) {
        startPage();
        Dict dictParam = dictConvert.dictListRes2Dict(dictListReq);
        List<Dict> dictList = dictService.selectList(dictParam).get();
        Page page = (Page) dictList;
        List<DictListRes> dataList = new ArrayList<>();
        for (Dict dict : dictList) {
            DictListRes dictListRes = dictConvert.dict2DictListRes(dict);
            dataList.add(dictListRes);
        }
        return CommonAjaxResult.ok().setListResult(dataList, page);
    }

    /**
     * 查询字典 详情
     * @param dictId 字典id
     * @return
     */
    @RequestMapping("/detail/{dictId}")
    public CommonAjaxResult detail(@PathVariable("dictId") Long dictId) {
        Dict dict = dictService.getById(dictId).get();
        DictDetailRes dictDetailRes = dictConvert.dict2DictDetailRes(dict);
        return CommonAjaxResult.ok().setResult(dictDetailRes);
    }

    /**
     * 新增字典 查询
     */
    @RequiresPermissions("system:dict:add")
    @GetMapping("/preAdd")
    public CommonAjaxResult preAdd() {
        DictAddRes preAddDto = new DictAddRes();
        return CommonAjaxResult.ok().setResult(preAddDto);
    }

    /**
     * 新增字典 保存
     */
    @RequiresPermissions("system:dict:add")
    @PostMapping("/saveAdd")
    public CommonAjaxResult saveAdd(DictAddReq dictAddReq) {
        Dict dict = dictConvert.dictAddReq2Dict(dictAddReq);
        dictService.insert(dict);
        return CommonAjaxResult.ok().setResult(dict.getId());
    }

    /**
     * 修改字典 查询
     */
    @RequiresPermissions("system:dict:edit")
    @GetMapping("/preEdit/{dictId}")
    public CommonAjaxResult preEdit(@PathVariable("dictId") Long dictId) {
        Dict dict = dictService.getById(dictId).get();
        DictEditRes dictEditRes = dictConvert.dict2DictEditRes(dict);
        return CommonAjaxResult.ok().setResult(dictEditRes);
    }

    /**
     * 修改字典 保存
     */
    @RequiresPermissions("system:dict:edit")
    @PostMapping("/saveEdit")
    public CommonAjaxResult saveEdit(DictEditReq dictEditReq) {
        Dict dict = dictConvert.dictEditReq2Dict(dictEditReq);
        dictService.update(dict);
        return CommonAjaxResult.ok();
    }

    /**
     * 删除字典，例如dictId=1&dictId=2
     */
    @RequiresPermissions("system:dict:remove")
    @PostMapping("/remove")
    public CommonAjaxResult remove(Long[] dictId) {
        dictService.deleteByIds(dictId);
        return CommonAjaxResult.ok();
    }

}

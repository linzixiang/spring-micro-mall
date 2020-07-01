package com.linzx.admin.system.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.linzx.admin.system.convert.DeptConvert;
import com.linzx.admin.system.domain.Dept;
import com.linzx.admin.system.dto.dept.request.DeptAddReq;
import com.linzx.admin.system.dto.dept.request.DeptEditReq;
import com.linzx.admin.system.dto.dept.request.DeptListReq;
import com.linzx.admin.system.dto.dept.response.DeptListRes;
import com.linzx.admin.system.dto.dept.response.DeptEditRes;
import com.linzx.admin.system.dto.dept.response.DeptTreeRes;
import com.linzx.admin.system.service.IDeptService;
import com.linzx.admin.system.service.impl.DeptService;
import com.linzx.core.common.exception.BizException;
import com.linzx.core.framework.tinyid.IdUtils;
import com.linzx.core.web.base.BaseController;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门
 * @author linzixiang
 * @date 2020-06-10 20:45:37
 */
@RestController
@RequestMapping("/system/dept")
public class DeptController extends BaseController {

    @Autowired
    private IDeptService deptService;
	
	@Autowired
    private DeptConvert deptConvert;

    /**
     * 部门 列表
     */
    @RequestMapping("/list")
    public CommonAjaxResult list(DeptListReq deptListReq) {
        startPage();
		Dept deptParam = deptConvert.deptListRes2Dept(deptListReq);
        deptParam.addParam("deptNameLike", deptListReq.getDeptNameLike());
        List<Dept> deptList = deptService.selectList(deptParam).get();
        Page page = (Page) deptList;
        List<DeptListRes> dataList = new ArrayList<>();
        for (Dept dept : deptList) {
			DeptListRes deptListRes = deptConvert.dept2DeptListRes(dept);
            dataList.add(deptListRes);
        }
        return CommonAjaxResult.ok().setListResult(dataList, page);
    }

    /**
     * 部门 新增查询
     */
    @GetMapping("/preAdd")
    public CommonAjaxResult preAdd() {
        return CommonAjaxResult.ok();
    }

	/**
     * 部门 新增保存
     */
    @PostMapping("/saveAdd")
    public CommonAjaxResult saveAdd(@Validated DeptAddReq deptAddReq) {
		Dept dept = deptConvert.deptAddReq2Dept(deptAddReq);
        dept.setId(IdUtils.getDBSequenceNextId(dept));
        deptService.setDeptAncestors(dept);
        deptService.insert(dept);
        return CommonAjaxResult.ok().setResult(dept.getId());
    }

    /**
     * 部门 修改查询
     */
    @GetMapping("/preEdit/{deptId}")
    public CommonAjaxResult preEdit(@PathVariable("deptId") Long deptId) {
        Dept dept = deptService.getById(deptId).get();
		DeptEditRes deptEditRes = deptConvert.dept2DeptEditRes(dept);
        return CommonAjaxResult.ok().setResult(deptEditRes);
    }

	/**
     * 部门 修改保存
     */
    @PostMapping("/saveEdit")
    public CommonAjaxResult saveEdit(@Validated DeptEditReq deptEditReq) {
        if (deptEditReq != null && deptEditReq.getParentId() < 0) {
            throw new BizException("request.param.error", new Object[]{ "parentId不合法" });
        }
		Dept dept = deptConvert.deptEditReq2Dept(deptEditReq);
        deptService.setDeptAncestors(dept);
        deptService.update(dept);
        return CommonAjaxResult.ok();
    }

    /**
     * 部门 删除，例如deptId=1&deptId=2
     */
    @PostMapping("/remove")
    public CommonAjaxResult remove(Long[] deptId) {
        deptService.deleteByIds(deptId);
        return CommonAjaxResult.ok();
    }


    /**
     * 部门树
     * @return
     */
    @GetMapping("/treeSelect")
    public CommonAjaxResult treeSelect() {
        Dept deptParam = new Dept();
        deptParam.setStatus(Dept.STATUS_NORMAL);
        deptParam.addOrderField("order_num", false);
        List<Dept> deptList = deptService.selectList(deptParam).get();
        deptList = deptService.buildDeptTree(deptList);
        List<DeptTreeRes> deptTreeResList = buildDeptTreeRouterRes(deptList);
        DeptTreeRes rootDeptTree = new DeptTreeRes();
        if (CollectionUtil.isNotEmpty(deptTreeResList)) {
            rootDeptTree = deptTreeResList.get(0);
        }
        return CommonAjaxResult.ok().setResult(rootDeptTree);
    }

    private List<DeptTreeRes> buildDeptTreeRouterRes(List<Dept> deptList) {
        List<DeptTreeRes> routerResList = new ArrayList<>();
        for (Dept dept : deptList) {
            DeptTreeRes deptTreeRes = new DeptTreeRes();
            deptTreeRes.setId(dept.getId());
            deptTreeRes.setLabel(dept.getDeptName());
            deptTreeRes.setAncestors(dept.getAncestors());
            List<Dept> cDept = dept.getChildren();
            if (cDept != null && cDept.size() > 0) {
                deptTreeRes.setChildren(buildDeptTreeRouterRes(cDept));
            }
            routerResList.add(deptTreeRes);
        }
        return routerResList;
    }

}

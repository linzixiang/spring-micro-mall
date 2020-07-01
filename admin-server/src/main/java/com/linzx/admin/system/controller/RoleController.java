package com.linzx.admin.system.controller;

import com.github.pagehelper.Page;
import com.linzx.admin.system.convert.RoleConvert;
import com.linzx.admin.system.domain.Role;
import com.linzx.admin.system.domain.RoleMenu;
import com.linzx.admin.system.dto.role.request.RoleAddReq;
import com.linzx.admin.system.dto.role.request.RoleEditReq;
import com.linzx.admin.system.dto.role.request.RoleListReq;
import com.linzx.admin.system.dto.role.response.RoleListRes;
import com.linzx.admin.system.dto.role.response.RoleEditRes;
import com.linzx.admin.system.service.IRoleService;
import com.linzx.admin.system.service.impl.RoleMenuService;
import com.linzx.core.security.authz.RequiresPermissions;
import com.linzx.core.web.base.BaseController;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 角色
 * @author linzixiang
 * @date 2020-06-10 16:33:11
 */
@RestController
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;
	
	@Autowired
    private RoleConvert roleConvert;

    /**
     * 角色 列表
     */
    @RequiresPermissions("system:role:list")
    @RequestMapping("/list")
    public CommonAjaxResult list(RoleListReq roleListReq) {
        startPage();
		Role roleParam = roleConvert.roleListRes2Role(roleListReq);
        List<Role> roleList = roleService.selectList(roleParam).get();
        Page page = (Page) roleList;
        List<RoleListRes> dataList = new ArrayList<>();
        for (Role role : roleList) {
			RoleListRes roleListRes = roleConvert.role2RoleListRes(role);
            dataList.add(roleListRes);
        }
        return CommonAjaxResult.ok().setListResult(dataList, page);
    }

    /**
     * 角色 新增查询
     */
    @RequiresPermissions("system:role:add")
    @GetMapping("/preAdd")
    public CommonAjaxResult preAdd() {
        return CommonAjaxResult.ok();
    }

	/**
     * 角色 新增保存
     */
    @RequiresPermissions("system:role:add")
    @PostMapping("/saveAdd")
    public CommonAjaxResult saveAdd(RoleAddReq roleAddReq) {
		Role role = roleConvert.roleAddReq2Role(roleAddReq);
        roleService.insert(role);
        List<Long> menuIdList = roleAddReq.getMenuId();
        List<RoleMenu> roleMenuList = new ArrayList<>();
        for (Long menuId : menuIdList) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(role.getRoleId());
            roleMenu.setMenuId(menuId);
            roleMenuList.add(roleMenu);
        }
        roleMenuService.insertBatch(roleMenuList);
        return CommonAjaxResult.ok().setResult(role.getId());
    }

    /**
     * 角色 修改查询
     */
    @RequiresPermissions("system:role:edit")
    @GetMapping("/preEdit/{roleId}")
    public CommonAjaxResult preEdit(@PathVariable("roleId") Long roleId) {
        Role role = roleService.getById(roleId).get();
        // TODO 数据转换：Role => RoleEditRes
		RoleEditRes roleEditRes = roleConvert.role2RoleEditRes(role);
        return CommonAjaxResult.ok().setResult(roleEditRes);
    }

	/**
     * 角色 修改保存
     */
    @RequiresPermissions("system:role:edit")
    @PostMapping("/saveEdit")
    public CommonAjaxResult saveEdit(RoleEditReq roleEditReq) {
        // TODO 数据转换：RoleEditReq => Role
		Role role = roleConvert.roleEditReq2Role(roleEditReq);
        roleService.update(role);
        return CommonAjaxResult.ok();
    }

    /**
     * 角色 删除，例如roleId=1&roleId=2
     */
    @RequiresPermissions("system:role:remove")
    @PostMapping("/remove")
    public CommonAjaxResult remove(Long[] roleId) {
        roleService.deleteByIds(roleId);
        return CommonAjaxResult.ok();
    }
}

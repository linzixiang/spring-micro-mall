package com.linzx.admin.system.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.Page;
import com.linzx.admin.system.convert.MenuConvert;
import com.linzx.admin.system.domain.Menu;
import com.linzx.admin.system.dto.menu.request.MenuAddReq;
import com.linzx.admin.system.dto.menu.request.MenuEditReq;
import com.linzx.admin.system.dto.menu.request.MenuListReq;
import com.linzx.admin.system.dto.menu.response.MenuListRes;
import com.linzx.admin.system.dto.menu.response.MenuEditRes;
import com.linzx.admin.system.dto.menu.response.MenuTreeRes;
import com.linzx.admin.system.service.IMenuService;
import com.linzx.core.common.exception.BizException;
import com.linzx.core.framework.tinyid.IdUtils;
import com.linzx.core.security.authz.RequiresPermissions;
import com.linzx.core.web.base.BaseController;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单 
 * @author linzixiang
 * @date 2020-06-10 15:05:14
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;
	
	@Autowired
    private MenuConvert menuConvert;

    /**
     * 菜单 列表
     */
    @RequiresPermissions("system:menu:list")
    @RequestMapping("/list")
    public CommonAjaxResult list(MenuListReq menuListReq) {
        startPage();
		Menu menuParam = menuConvert.menuListRes2Menu(menuListReq);
        menuParam.addParam("menuNameLike", menuListReq.getMenuNameLike());
        List<Menu> menuList = menuService.selectList(menuParam).get();
        Page page = (Page) menuList;
        List<MenuListRes> dataList = new ArrayList<>();
        for (Menu menu : menuList) {
			MenuListRes menuListRes = menuConvert.menu2MenuListRes(menu);
            dataList.add(menuListRes);
        }
        return CommonAjaxResult.ok().setListResult(dataList, page);
    }

    /**
     * 菜单  新增查询
     */
    @RequiresPermissions("system:menu:add")
    @GetMapping("/preAdd")
    public CommonAjaxResult preAdd() {
        return CommonAjaxResult.ok();
    }

	/**
     * 菜单  新增保存
     */
    @RequiresPermissions("system:menu:add")
    @PostMapping("/saveAdd")
    public CommonAjaxResult saveAdd(@Validated MenuAddReq menuAddReq) {
        if (menuAddReq.getParentId() < 0) {
            throw new BizException("request.param.error", new Object[]{ "parentId不合法" });
        }
		Menu menu = menuConvert.menuAddReq2Menu(menuAddReq);
        menu.setId(IdUtils.getDBSequenceNextId(menu));
        menuService.setMenuAncestors(menu);
        menuService.insert(menu);
        return CommonAjaxResult.ok().setResult("0");
    }

    /**
     * 菜单  修改查询
     */
    @RequiresPermissions("system:menu:edit")
    @GetMapping("/preEdit/{menuId}")
    public CommonAjaxResult preEdit(@PathVariable("menuId") Long menuId) {
        Menu menu = menuService.getById(menuId).get();
		MenuEditRes menuEditRes = menuConvert.menu2MenuEditRes(menu);
        return CommonAjaxResult.ok().setResult(menuEditRes);
    }

	/**
     * 菜单  修改保存
     */
    @RequiresPermissions("system:menu:edit")
    @PostMapping("/saveEdit")
    public CommonAjaxResult saveEdit(MenuEditReq menuEditReq) {
        if (menuEditReq != null && menuEditReq.getParentId() < 0) {
            throw new BizException("request.param.error", new Object[]{ "parentId不合法" });
        }
		Menu menu = menuConvert.menuEditReq2Menu(menuEditReq);
        menuService.setMenuAncestors(menu);
        menuService.update(menu);
        return CommonAjaxResult.ok();
    }

    /**
     * 菜单  删除，例如menuId=1&menuId=2
     */
    @RequiresPermissions("system:menu:remove")
    @PostMapping("/remove")
    public CommonAjaxResult remove(Long[] menuId) {
        menuService.deleteByIds(menuId);
        return CommonAjaxResult.ok();
    }

    /**
     * 菜单选择树
     * @param menuNameLike 菜单过滤关键字
     */
    @RequestMapping("/treeSelect")
    public CommonAjaxResult treeSelect(@RequestParam(name = "menuNameLike", required = false) String menuNameLike) {
        Menu param = new Menu();
        param.setVisible(Menu.VISIBLE_SHOW);
        param.addParam("menuNameLike", menuNameLike);
        param.addParam("menuTypes",new String[]{Menu.MENU_TYPE_C, Menu.MENU_TYPE_M});
        param.addOrderField("order_num", false);
        List<Menu> menuList = menuService.selectList(param).get();
        menuList = menuService.buildMenuChildren(menuList);
        List<MenuTreeRes> menuTreeList = this.buildMenuTree(menuList);
        MenuTreeRes menuTreeRes = new MenuTreeRes();
        menuTreeRes.setId(0L);
        menuTreeRes.setLabel("主类目");
        menuTreeRes.setChildren(menuTreeList);
        return CommonAjaxResult.ok().setResult(menuTreeRes);
    }

    private List<MenuTreeRes> buildMenuTree(List<Menu> menuList) {
        List<MenuTreeRes> routerResList = new ArrayList<>();
        for (Menu menu : menuList) {
            MenuTreeRes menuTreeRes = menuConvert.menu2MenuTreeRes(menu);
            if (CollectionUtil.isNotEmpty(menu.getChildren())) {
                menuTreeRes.setChildren(buildMenuTree(menu.getChildren()));
            }
            routerResList.add(menuTreeRes);
        }
        return routerResList;
    }

}

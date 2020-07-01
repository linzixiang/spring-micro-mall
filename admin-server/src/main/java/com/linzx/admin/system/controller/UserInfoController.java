package com.linzx.admin.system.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.linzx.admin.system.domain.Menu;
import com.linzx.admin.system.domain.User;
import com.linzx.admin.system.dto.userInfo.response.UserLoginInfo;
import com.linzx.admin.system.dto.userInfo.response.RouterMeta;
import com.linzx.admin.system.dto.userInfo.response.RouterVo;
import com.linzx.admin.system.dto.userInfo.response.UserVo;
import com.linzx.admin.system.service.IMenuService;
import com.linzx.admin.system.service.IUserService;
import com.linzx.core.common.bean.UserLoginPrincipal;
import com.linzx.core.security.authz.AuthorizationInfo;
import com.linzx.core.security.subject.DelegatingSubject;
import com.linzx.core.web.base.BaseController;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/system/userInfo")
public class UserInfoController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserService userService;

    /**
     * 获取用户角色，权限等基础信息
     * @return
     */
    @GetMapping("/getAuth")
    public CommonAjaxResult getAuth() {
        AuthorizationInfo authorizationInfo = DelegatingSubject.getInstance().getAuthorizationInfo();
        Set<String> roles = authorizationInfo.getRoles();
        Set<String> permissions = authorizationInfo.getStringPermissions();
        UserLoginInfo.UserLoginInfoBuilder builder = UserLoginInfo.builder().roles(roles).permissions(permissions);
        UserLoginPrincipal loginPrincipal = getUserLoginPrincipal();
        User user = userService.getById(Convert.toLong(loginPrincipal.getUserId())).get();
        String userNickName = StrUtil.isBlank(user.getNickName()) ? user.getAccount() : user.getNickName();
        UserVo userVo = UserVo.builder().avatar(user.getAvatar()).userName(userNickName).build();
        builder.user(userVo);
        return CommonAjaxResult.ok().setResult(builder.build());
    }

    /**
     * 获取路由信息
     */
    @GetMapping("/getRouters")
    public CommonAjaxResult getRouters() {
        UserLoginPrincipal loginPrincipal = getUserLoginPrincipal();
        User user = userService.getById(Convert.toLong(loginPrincipal.getUserId())).get();
        List<Menu> menuList = null;
        if (ObjectUtil.equal(user.getUserType(), User.USER_TYPE_1)) {
            // 超级用户，拥有所有菜单权限
            Menu param = new Menu();
            param.setVisible(Menu.VISIBLE_SHOW);
            param.addParam("menuTypes",new String[]{Menu.MENU_TYPE_C, Menu.MENU_TYPE_M});
            param.addOrderField("order_num", false);
            menuList = menuService.selectList(param).get();
        } else {
            menuList = new ArrayList<>();
        }
        menuList = menuService.buildMenuChildren(menuList);
        return CommonAjaxResult.ok().setResult(buildRouterRes(menuList));
    }

    private List<RouterVo> buildRouterRes(List<Menu> menuList) {
        List<RouterVo> routerResList = new ArrayList<>();
        for (Menu menu : menuList) {
            RouterVo.RouterVoBuilder builder = RouterVo.builder();
            builder.hidden("0".equals(menu.getVisible()))
                    .name(StrUtil.upperFirst(menu.getPath()))
                    .path(getRouterPath(menu))
                    .component(StrUtil.isBlank(menu.getComponent()) ? "Layout" : menu.getComponent())
                    .meta(RouterMeta.builder().icon(menu.getIcon()).title(menu.getMenuName()).build());
            List<Menu> cMenus = menu.getChildren();
            if (cMenus != null && cMenus.size() > 0 && "M".equals(menu.getMenuType())) {
                builder.alwaysShow(true)
                        .redirect("noRedirect")
                        .children(buildRouterRes(cMenus));
            }
            routerResList.add(builder.build());
        }
        return routerResList;
    }

    public String getRouterPath(Menu menu) {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录
        if (0 == menu.getParentId()) {
            routerPath = "/" + menu.getPath();
        }
        return routerPath;
    }

}

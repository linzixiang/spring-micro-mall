package com.linzx.admin.system.service;

import com.linzx.WebAdminApplication;
import com.linzx.admin.system.domain.Menu;
import com.linzx.admin.system.service.impl.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebAdminApplication.class)
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void buildMenuChildren() {
        Menu param = new Menu();
        param.setVisible(Menu.VISIBLE_SHOW);
        param.addParam("menuTypes",new String[]{Menu.MENU_TYPE_C, Menu.MENU_TYPE_M});
        List<Menu> menuList = menuService.selectList(param).get();
        menuList = menuService.buildMenuChildren(menuList);
        int i = 0;
    }

}

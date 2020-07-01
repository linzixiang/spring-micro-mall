package com.linzx;

import cn.hutool.core.convert.Convert;
import com.linzx.admin.system.domain.*;
import com.linzx.admin.system.service.impl.*;
import com.linzx.common.util.PasswordUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebAdminApplication.class)
public class AppDataInit {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DictOptionService dictOptionService;

    @Autowired
    private DictService dictService;

    /**
     * 初始化用户
     */
    @Test
     public void initUser() {
         User adminUser = new User();
         adminUser.setAccount("admin");
         adminUser.setNickName("超级管理员");
         adminUser.setUserType(User.USER_TYPE_1);
         adminUser.setSalt(PasswordUtils.randomSalt());
         adminUser.setPhoneNumber("15868144632");
         String password = PasswordUtils
                 .encryptPassword(adminUser.getAccount(), "123456", adminUser.getSalt());
         adminUser.setPassword(password);
         userService.insert(adminUser);
     }

    /**
     * 初始化菜单
     */
    @Test
     public void initMenu() {
        /**************************** 一级菜单 **********************************/
        Menu menuSystem = new Menu();
        menuSystem.setMenuName("系统管理");
        menuSystem.setParentId(new Long(0));
        menuSystem.setOrderNum(1);
        menuSystem.setPath("system");
        menuSystem.setMenuType(Menu.MENU_TYPE_M);
        menuSystem.setVisible(Menu.VISIBLE_SHOW);
        menuSystem.setRemark("系统管理目录");
        menuSystem.setId(menuService.getNextId(menuSystem));
        menuSystem.setAncestors(menuSystem.getId().toString());
        menuService.insert(menuSystem);
        Menu menuMonitor = new Menu();
        menuMonitor.setMenuName("系统监控");
        menuMonitor.setParentId(new Long(0));
        menuMonitor.setOrderNum(2);
        menuMonitor.setPath("monitor");
        menuMonitor.setMenuType(Menu.MENU_TYPE_M);
        menuMonitor.setVisible(Menu.VISIBLE_SHOW);
        menuMonitor.setRemark("系统监控目录");
        menuMonitor.setId(menuService.getNextId(menuSystem));
        menuMonitor.setAncestors(menuMonitor.getId().toString());
        menuService.insert(menuMonitor);
        Menu menuTool = new Menu();
        menuTool.setMenuName("系统工具");
        menuTool.setParentId(new Long(0));
        menuTool.setOrderNum(3);
        menuTool.setPath("tool");
        menuTool.setMenuType(Menu.MENU_TYPE_M);
        menuTool.setVisible(Menu.VISIBLE_SHOW);
        menuTool.setRemark("系统工具目录");
        menuTool.setId(menuService.getNextId(menuTool));
        menuTool.setAncestors(menuTool.getId().toString());
        menuService.insert(menuTool);

        /*****************************二级菜单****************************************/
        Menu menuUser = new Menu();
        menuUser.setMenuName("用户管理");
        menuUser.setParentId(menuSystem.getId());
        menuUser.setOrderNum(1);
        menuUser.setPath("user");
        menuUser.setMenuType(Menu.MENU_TYPE_C);
        menuUser.setVisible(Menu.VISIBLE_SHOW);
        menuUser.setRemark("用户管理菜单");
        menuUser.setComponent("system/user/index");
        menuUser.setPerms("system:user:list");
        menuUser.setId(menuService.getNextId(menuUser));
        menuUser.setAncestors(menuUser.getParentId() + "," + menuUser.getId().toString());
        menuService.insert(menuUser);
        Menu menuRole = new Menu();
        menuRole.setMenuName("角色管理");
        menuRole.setParentId(menuSystem.getId());
        menuRole.setOrderNum(2);
        menuRole.setPath("role");
        menuRole.setMenuType(Menu.MENU_TYPE_C);
        menuRole.setVisible(Menu.VISIBLE_SHOW);
        menuRole.setRemark("角色管理菜单");
        menuRole.setComponent("system/role/index");
        menuRole.setPerms("system:role:list");
        menuRole.setId(menuService.getNextId(menuRole));
        menuRole.setAncestors(menuRole.getParentId() + "," + menuRole.getId());
        menuService.insert(menuRole);
        Menu menuMenu = new Menu();
        menuMenu.setMenuName("菜单管理");
        menuMenu.setParentId(menuSystem.getId());
        menuMenu.setOrderNum(3);
        menuMenu.setPath("menu");
        menuMenu.setMenuType(Menu.MENU_TYPE_C);
        menuMenu.setVisible(Menu.VISIBLE_SHOW);
        menuMenu.setRemark("菜单管理菜单");
        menuMenu.setComponent("system/menu/index");
        menuMenu.setPerms("system:menu:list");
        menuMenu.setId(menuService.getNextId(menuMenu));
        menuMenu.setAncestors(menuMenu.getParentId() + "," + menuMenu.getId());
        menuService.insert(menuMenu);
     }

    /**
     * 初始化字典表
     */
    @Test
     public void initDict() {
        // 性别
        Dict sex = new Dict();
        sex.setDictCode("gender");
        sex.setDictName("性别");
        sex.setDictId(dictService.getNextId(sex));
        dictService.insert(sex);
        DictOption sexOption1 = new DictOption();
        sexOption1.setDictCode(sex.getDictCode());
        sexOption1.setDictValue("1");
        sexOption1.setDictLabel("男");
        sexOption1.setDictId(sex.getDictId());
        sexOption1.setOrderNum(100);
        dictOptionService.insert(sexOption1);
        DictOption sexOption2 = new DictOption();
        sexOption2.setDictCode(sex.getDictCode());
        sexOption2.setDictValue("2");
        sexOption2.setDictLabel("女");
        sexOption2.setOrderNum(90);
        sexOption2.setDictId(sex.getDictId());
        dictOptionService.insert(sexOption2);
        DictOption sexOption3 = new DictOption();
        sexOption3.setDictCode(sex.getDictCode());
        sexOption3.setDictValue("3");
        sexOption3.setDictLabel("未知");
        sexOption3.setOrderNum(80);
        sexOption3.setDictId(sex.getDictId());
        dictOptionService.insert(sexOption3);
        // 是否
        Dict yesNo = new Dict();
        yesNo.setDictCode("yes_no");
        yesNo.setDictName("是否");
        yesNo.setDictId(dictService.getNextId(yesNo));
        dictService.insert(yesNo);
        DictOption yesNoOption1 = new DictOption();
        yesNoOption1.setDictCode(yesNo.getDictCode());
        yesNoOption1.setDictValue("1");
        yesNoOption1.setDictLabel("是");
        yesNoOption1.setOrderNum(100);
        yesNoOption1.setDictId(yesNo.getDictId());
        dictOptionService.insert(yesNoOption1);
        DictOption yesNoOption2 = new DictOption();
        yesNoOption2.setDictCode(yesNo.getDictCode());
        yesNoOption2.setDictValue("0");
        yesNoOption2.setDictLabel("否");
        yesNoOption2.setOrderNum(90);
        yesNoOption2.setDictId(yesNo.getDictId());
        dictOptionService.insert(yesNoOption2);
        // 显示/隐藏
        Dict showHidden = new Dict();
        showHidden.setDictCode("show_hide");
        showHidden.setDictName("显示隐藏");
        showHidden.setDictId(dictService.getNextId(showHidden));
        dictService.insert(showHidden);
        DictOption showHiddenOption1 = new DictOption();
        showHiddenOption1.setDictCode(showHidden.getDictCode());
        showHiddenOption1.setDictValue("1");
        showHiddenOption1.setDictLabel("显示");
        showHiddenOption1.setOrderNum(100);
        showHiddenOption1.setDictId(showHidden.getDictId());
        dictOptionService.insert(showHiddenOption1);
        DictOption showHiddenOption2 = new DictOption();
        showHiddenOption2.setDictCode(showHidden.getDictCode());
        showHiddenOption2.setDictValue("0");
        showHiddenOption2.setDictLabel("隐藏");
        showHiddenOption2.setOrderNum(90);
        showHiddenOption2.setDictId(showHidden.getDictId());
        dictOptionService.insert(showHiddenOption2);
        // 正常/停用
        Dict normalDisable = new Dict();
        normalDisable.setDictCode("normal_disable");
        normalDisable.setDictName("正常停用");
        normalDisable.setDictId(dictService.getNextId(normalDisable));
        dictService.insert(normalDisable);
        DictOption normalDisableOption1 = new DictOption();
        normalDisableOption1.setDictCode(normalDisable.getDictCode());
        normalDisableOption1.setDictValue("1");
        normalDisableOption1.setDictLabel("正常");
        normalDisableOption1.setOrderNum(100);
        normalDisableOption1.setDictId(normalDisable.getDictId());
        dictOptionService.insert(normalDisableOption1);
        DictOption normalDisableOption2 = new DictOption();
        normalDisableOption2.setDictCode(normalDisable.getDictCode());
        normalDisableOption2.setDictValue("1");
        normalDisableOption2.setDictLabel("停用");
        normalDisableOption2.setOrderNum(100);
        normalDisableOption2.setDictId(normalDisable.getDictId());
        dictOptionService.insert(normalDisableOption2);
     }

     @Autowired
     private DeptService deptService;

    /**
      * 初始化部门
      */
     @Test
     public void initDept() {
        Dept rootDept = new Dept();
        rootDept.setParentId(0L);
        rootDept.setDeptName("若依科技");
        rootDept.setOrderNum(0);
        rootDept.setId(deptService.getNextId(rootDept));
        rootDept.setAncestors(Convert.toStr(rootDept.getId()));
        deptService.insert(rootDept);
        Dept shenzhenDept = new Dept();
        shenzhenDept.setParentId(rootDept.getId());
        shenzhenDept.setDeptName("深圳总公司");
        shenzhenDept.setOrderNum(100);
        shenzhenDept.setId(deptService.getNextId(shenzhenDept));
        shenzhenDept.setAncestors(rootDept.getAncestors() + "," + shenzhenDept.getId());
        deptService.insert(shenzhenDept);
        Dept changshaDept = new Dept();
        changshaDept.setParentId(rootDept.getId());
        changshaDept.setDeptName("长沙分公司");
        changshaDept.setOrderNum(90);
        changshaDept.setId(deptService.getNextId(changshaDept));
        changshaDept.setAncestors(rootDept.getAncestors() + "," + changshaDept.getId());
        deptService.insert(changshaDept);
        Dept developDept = new Dept();
        developDept.setParentId(changshaDept.getId());
        developDept.setDeptName("研发部门");
        developDept.setOrderNum(100);
        developDept.setId(deptService.getNextId(developDept));
        developDept.setAncestors(changshaDept.getAncestors() + "," + developDept.getId());
        deptService.insert(developDept);
        Dept marketDept = new Dept();
        marketDept.setParentId(changshaDept.getId());
        marketDept.setDeptName("市场部门");
        marketDept.setOrderNum(90);
        marketDept.setId(deptService.getNextId(marketDept));
        marketDept.setAncestors(changshaDept.getAncestors() + "," + marketDept.getId());
        deptService.insert(marketDept);
     }



}

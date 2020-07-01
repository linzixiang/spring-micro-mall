package com.linzx.admin.system.convert;

import com.linzx.admin.system.domain.Menu;
import com.linzx.admin.system.dto.menu.request.MenuAddReq;
import com.linzx.admin.system.dto.menu.request.MenuEditReq;
import com.linzx.admin.system.dto.menu.request.MenuListReq;
import com.linzx.admin.system.dto.menu.response.MenuEditRes;
import com.linzx.admin.system.dto.menu.response.MenuListRes;
import com.linzx.admin.system.dto.menu.response.MenuTreeRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Menu对象转换
 */
@Mapper(componentModel="spring")
public interface MenuConvert {

    /**
     * 列表查询参数
     */
    @Mappings({})
    Menu menuListRes2Menu(MenuListReq menuListReq);

    /**
     * 列表查询结果
     */
    @Mappings({})
    MenuListRes menu2MenuListRes(Menu menu);

    /**
     * 新增保存
     */
    @Mappings({})
    Menu menuAddReq2Menu(MenuAddReq menuAddReq);

    /**
     * 编辑查询
     */
    @Mappings({})
    MenuEditRes menu2MenuEditRes(Menu menu);

    /**
     * 编辑保存
     */
    @Mappings({})
    Menu menuEditReq2Menu(MenuEditReq menuEditReq);

    /**
     * 菜单选择树
     */
    @Mapping(source = "menuName", target = "label")
    MenuTreeRes menu2MenuTreeRes(Menu menu);
    
}

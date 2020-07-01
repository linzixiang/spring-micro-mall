package com.linzx.admin.system.convert;

import com.linzx.admin.system.domain.User;
import com.linzx.admin.system.dto.user.request.UserAddReq;
import com.linzx.admin.system.dto.user.request.UserEditReq;
import com.linzx.admin.system.dto.user.request.UserListReq;
import com.linzx.admin.system.dto.user.response.UserEditRes;
import com.linzx.admin.system.dto.user.response.UserListRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * User对象转换
 */
@Mapper(componentModel="spring")
public interface UserConvert {

    /**
     * 列表查询参数
     */
    @Mappings({})
    User userListRes2User(UserListReq userListReq);

    /**
     * 列表查询结果
     */
    @Mappings({})
    UserListRes user2UserListRes(User user);

    /**
     * 新增保存
     */
    @Mappings({})
    User userAddReq2User(UserAddReq userAddReq);

    /**
     * 编辑查询
     */
    @Mappings({})
    UserEditRes user2UserEditRes(User user);

    /**
     * 编辑保存
     */
    @Mappings({})
    User userEditReq2User(UserEditReq userEditReq);
    
}

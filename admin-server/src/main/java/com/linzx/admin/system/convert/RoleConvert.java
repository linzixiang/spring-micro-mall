package com.linzx.admin.system.convert;

import com.linzx.admin.system.domain.Role;
import com.linzx.admin.system.dto.role.request.RoleAddReq;
import com.linzx.admin.system.dto.role.request.RoleEditReq;
import com.linzx.admin.system.dto.role.request.RoleListReq;
import com.linzx.admin.system.dto.role.response.RoleEditRes;
import com.linzx.admin.system.dto.role.response.RoleListRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * Role对象转换
 */
@Mapper(componentModel="spring")
public interface RoleConvert {

    /**
     * 列表查询参数
     */
    @Mappings({})
    Role roleListRes2Role(RoleListReq roleListReq);

    /**
     * 列表查询结果
     */
    @Mappings({})
    RoleListRes role2RoleListRes(Role role);

    /**
     * 新增保存
     */
    @Mappings({})
    Role roleAddReq2Role(RoleAddReq roleAddReq);

    /**
     * 编辑查询
     */
    @Mappings({})
    RoleEditRes role2RoleEditRes(Role role);

    /**
     * 编辑保存
     */
    @Mappings({})
    Role roleEditReq2Role(RoleEditReq roleEditReq);
    
}

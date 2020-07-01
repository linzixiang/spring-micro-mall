package com.linzx.admin.system.convert;

import com.linzx.admin.system.domain.Dept;
import com.linzx.admin.system.dto.dept.request.DeptAddReq;
import com.linzx.admin.system.dto.dept.request.DeptEditReq;
import com.linzx.admin.system.dto.dept.request.DeptListReq;
import com.linzx.admin.system.dto.dept.response.DeptEditRes;
import com.linzx.admin.system.dto.dept.response.DeptListRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * Dept对象转换
 */
@Mapper(componentModel="spring")
public interface DeptConvert {

    /**
     * 列表查询参数
     */
    @Mappings({})
    Dept deptListRes2Dept(DeptListReq deptListReq);

    /**
     * 列表查询结果
     */
    @Mappings({})
    DeptListRes dept2DeptListRes(Dept dept);

    /**
     * 新增保存
     */
    @Mappings({})
    Dept deptAddReq2Dept(DeptAddReq deptAddReq);

    /**
     * 编辑查询
     */
    @Mappings({})
    DeptEditRes dept2DeptEditRes(Dept dept);

    /**
     * 编辑保存
     */
    @Mappings({})
    Dept deptEditReq2Dept(DeptEditReq deptEditReq);
    
}

package com.linzx.admin.system.service;

import com.linzx.admin.system.domain.Dept;

import java.util.List;
import java.util.Optional;

/**
 * 部门 服务层接口
 * @author linzixiang
 * @date  2020-06-10 20:45:37
 */
public interface IDeptService {

    /**
     * 构建部门树
     */
    List<Dept> buildDeptTree(List<Dept> deptList);

    /**
     * 设置层级
     */
    void setDeptAncestors(Dept dept);

    Optional<Dept> getById(Long id);

    Optional<List<Dept>> selectByIds(Long[] ids);

    Optional<List<Dept>> selectList(Dept dept);

    Integer insert(Dept dept);

    Integer insertBatch(List<Dept> configList);

    Integer update(Dept dept);

    Integer deleteById(Long id);

    Integer deleteByIds(Long[] ids);

}

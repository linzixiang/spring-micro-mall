package com.linzx.admin.system.service;

import com.linzx.admin.system.domain.Role;

import java.util.List;
import java.util.Optional;

/**
 * 角色  服务层接口
 * @author linzixiang
 * @date  2020-05-31 19:29:27
 */
public interface IRoleService {

    /**
     * 查找用户的角色
     * @param userId 用户id
     * @return
     */
    List<Role> selectRoleByUserId(Long userId);

    Optional<Role> getById(Long id);

    Optional<List<Role>> selectByIds(Long[] ids);

    Optional<List<Role>> selectList(Role role);

    Integer insert(Role role);

    Integer insertBatch(List<Role> roleList);

    Integer update(Role role);

    Integer deleteById(Long id);

    Integer deleteByIds(Long[] ids);

}

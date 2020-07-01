package com.linzx.admin.system.service;

import com.linzx.admin.system.domain.UserRole;

import java.util.List;
import java.util.Optional;

/**
 * 用户角色关联  服务层接口
 * @author linzixiang
 * @date  2020-06-10 17:05:18
 */
public interface IUserRoleService {

    /**
     * 修改 批量操作 \n
     * 1、postIds集合存在，数据库中不存在则新增 \n
     * 2、postIds集合不存在，数据库中存在则删除 \n
     * 3、postIds集合存在，数据库中也存在，忽略不操作 \n
     * @param userId 用户id
     * @param roleIds 角色id集合
     */
    void saveUserRoleBatch(Long userId, List<Long> roleIds);

    Optional<UserRole> getById(Long id);

    Optional<List<UserRole>> selectByIds(Long[] ids);

    Optional<List<UserRole>> selectList(UserRole userRole);

    Integer insert(UserRole userRole);

    Integer insertBatch(List<UserRole> userRoleList);

    Integer update(UserRole userRole);

    Integer deleteById(Long id);

    Integer deleteByIds(Long[] ids);

}

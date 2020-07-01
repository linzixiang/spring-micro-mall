package com.linzx.admin.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import com.linzx.admin.system.domain.UserPost;
import com.linzx.admin.system.domain.UserRole;
import com.linzx.admin.system.mapper.UserRoleMapper;
import com.linzx.admin.system.service.IUserRoleService;
import com.linzx.core.framework.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 用户角色关联  服务层实现
 * @author linzixiang
 * @date 2020-06-10 17:05:18
 */
@Service("userRoleService")
public class UserRoleService extends BaseService<Long, UserRole, UserRoleMapper> implements IUserRoleService {

    private UserRoleMapper userRoleMapper;

    public UserRoleService(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    protected UserRoleMapper getMapper() {
        return this.userRoleMapper;
    }

    @Override
    public void saveUserRoleBatch(Long userId, List<Long> roleIds) {
        UserRole params = new UserRole();
        params.setUserId(userId);
        Set<Long> roleIdSet = CollectionUtil.newHashSet(roleIds); // 最终需要保存的岗位
        List<UserRole> userRoleList = this.selectList(params).get(); // 已经保存的岗位
        List<UserRole> userRoleAddList = new ArrayList<>();
        List<Long> userRoleIdsDel = new ArrayList<>();
        for (UserRole userRole : userRoleList) {
            if (roleIdSet.remove(userRole.getRoleId())) { // 从集合roleIdSet中移除不需要处理的值
                // 数据库已保存的roleId忽略
                continue;
            }
            if (!roleIdSet.contains(userRole.getRoleId())) {
                // 数据库中有，但是最终保存的没有，需要删除
                userRoleIdsDel.add(userRole.getUseRoleId());
                roleIdSet.remove(userRole.getRoleId()); // 从集合中移除需要删除的值
            }
        }
        // roleIdSet剩余的值都是需要新增的
        Iterator<Long> roleIdSetIterator = roleIdSet.iterator();
        while (roleIdSetIterator.hasNext()) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleIdSetIterator.next());
            userRole.setUserId(userId);
            userRoleAddList.add(userRole);
        }
        this.insertBatch(userRoleAddList);
        // 删除
        Long[] ids = ArrayUtil.toArray(userRoleIdsDel, Long.class);
        this.deleteByIds(ids);
    }
}

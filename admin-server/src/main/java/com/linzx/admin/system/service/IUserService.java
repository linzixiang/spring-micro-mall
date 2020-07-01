package com.linzx.admin.system.service;

import com.linzx.admin.system.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * 用户  服务层接口
 * @author linzixiang
 * @date  2020-06-03 21:22:45
 */
public interface IUserService {

    Optional<User> getById(Long id);

    Optional<List<User>> selectByIds(Long[] ids);

    Optional<List<User>> selectList(User user);

    Integer insert(User user);

    Integer insertBatch(List<User> userList);

    Integer update(User user);

    Integer deleteById(Long id);

    Integer deleteByIds(Long[] ids);

}

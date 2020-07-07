package com.linzx.sso.admin.service;

import com.linzx.sso.admin.domain.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    Optional<User> getById(Long id);

    Optional<List<User>> selectByIds(Long[] ids);

    Optional<List<User>> selectList(User user);

    Integer insert(User user);

    Integer insertBatch(List<User> userList);

    Integer update(User user);

    Integer deleteById(Long id);

    Integer deleteByIds(Long[] ids);

    Boolean isExist(User entity);



}

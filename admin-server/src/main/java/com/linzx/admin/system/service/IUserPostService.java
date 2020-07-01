package com.linzx.admin.system.service;

import com.linzx.admin.system.domain.UserPost;

import java.util.List;
import java.util.Optional;

/**
 * 用户岗位关联 服务层接口
 * @author linzixiang
 * @date  2020-06-11 15:13:23
 */
public interface IUserPostService {

    /**
     * 修改 批量操作 \n
     * 1、postIds集合存在，数据库中不存在则新增 \n
     * 2、postIds集合不存在，数据库中存在则删除 \n
     * 3、postIds集合存在，数据库中也存在，忽略不操作 \n
     * @param userId 用户id
     * @param postIds 岗位id集合
     */
    void saveUserPostBatch(Long userId, List<Long> postIds);

    Optional<UserPost> getById(Long id);

    Optional<List<UserPost>> selectByIds(Long[] ids);

    Optional<List<UserPost>> selectList(UserPost userPost);

    Integer insert(UserPost userPost);

    Integer insertBatch(List<UserPost> userPostList);

    Integer update(UserPost userPost);

    Integer deleteById(Long id);

    Integer deleteByIds(Long[] ids);
}

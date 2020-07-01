package com.linzx.admin.system.service;

import com.linzx.admin.system.domain.Post;

import java.util.List;
import java.util.Optional;

/**
 * 岗位  服务层接口
 * @author linzixiang
 * @date  2020-06-10 22:35:48
 */
public interface IPostService {

    Optional<Post> getById(Long id);

    Optional<List<Post>> selectByIds(Long[] ids);

    Optional<List<Post>> selectList(Post post);

    Integer insert(Post post);

    Integer insertBatch(List<Post> postList);

    Integer update(Post post);

    Integer deleteById(Long id);

    Integer deleteByIds(Long[] ids);

}

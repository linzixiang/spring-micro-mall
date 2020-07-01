package com.linzx.admin.system.service.impl;

import com.linzx.admin.system.domain.Post;
import com.linzx.admin.system.mapper.PostMapper;
import com.linzx.admin.system.service.IPostService;
import com.linzx.core.framework.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * 岗位  服务层实现
 * @author linzixiang
 * @date 2020-06-10 22:35:48
 */
@Service("postService")
public class PostService extends BaseService<Long, Post, PostMapper> implements IPostService {

    private PostMapper postMapper;

    public PostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    protected PostMapper getMapper() {
        return this.postMapper;
    }

}

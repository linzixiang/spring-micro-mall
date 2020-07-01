package com.linzx.admin.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;
import com.linzx.admin.system.domain.UserPost;
import com.linzx.admin.system.mapper.UserPostMapper;
import com.linzx.admin.system.service.IUserPostService;
import com.linzx.core.framework.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 用户岗位关联 服务层实现
 * @author linzixiang
 * @date 2020-06-11 15:13:23
 */
@Service("userPostService")
public class UserPostService extends BaseService<Long, UserPost, UserPostMapper> implements IUserPostService {

    private UserPostMapper userPostMapper;

    public UserPostService(UserPostMapper userPostMapper) {
        this.userPostMapper = userPostMapper;
    }

    @Override
    protected UserPostMapper getMapper() {
        return this.userPostMapper;
    }

    @Override
    @Transactional
    public void saveUserPostBatch(Long userId, List<Long> postIds) {
        UserPost params = new UserPost();
        params.setUserId(userId);
        Set<Long> postIdSet = CollectionUtil.newHashSet(postIds); // 最终需要保存的岗位
        List<UserPost> userPostList = userPostMapper.selectList(params); // 已经保存的岗位
        List<UserPost> userPostAddList = new ArrayList<>();
        List<Long> userPostIdsDel = new ArrayList<>();
        for (UserPost userPost : userPostList) {
           if (postIdSet.remove(userPost.getPostId())) { // 从集合postIdSet中移除不需要处理的值
               // 数据库已保存的postId忽略
               continue;
           }
           if (!postIdSet.contains(userPost.getPostId())) {
               // 数据库中有，但是最终保存的没有，需要删除
               userPostIdsDel.add(userPost.getUserPostId());
               postIdSet.remove(userPost.getPostId()); // 从集合中移除需要删除的值
           }
        }
        // postIdSet剩余的值都是需要新增的
        Iterator<Long> postIdSetIterator = postIdSet.iterator();
        while (postIdSetIterator.hasNext()) {
            UserPost userPost = new UserPost();
            userPost.setPostId(postIdSetIterator.next());
            userPost.setUserId(userId);
            userPostAddList.add(userPost);
        }
        this.insertBatch(userPostAddList);
        // 删除
        Long[] ids = ArrayUtil.toArray(userPostIdsDel, Long.class);
        this.deleteByIds(ids);
    }
}

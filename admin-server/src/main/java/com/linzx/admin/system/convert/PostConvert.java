package com.linzx.admin.system.convert;

import com.linzx.admin.system.domain.Post;
import com.linzx.admin.system.dto.post.request.PostAddReq;
import com.linzx.admin.system.dto.post.request.PostEditReq;
import com.linzx.admin.system.dto.post.request.PostListReq;
import com.linzx.admin.system.dto.post.response.PostEditRes;
import com.linzx.admin.system.dto.post.response.PostListRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

/**
 * Post对象转换
 */
@Mapper(componentModel="spring")
public interface PostConvert {

    /**
     * 列表查询参数
     */
    @Mappings({})
    Post postListRes2Post(PostListReq postListReq);

    /**
     * 列表查询结果
     */
    @Mappings({})
    PostListRes post2PostListRes(Post post);

    /**
     * 新增保存
     */
    @Mappings({})
    Post postAddReq2Post(PostAddReq postAddReq);

    /**
     * 编辑查询
     */
    @Mappings({})
    PostEditRes post2PostEditRes(Post post);

    /**
     * 编辑保存
     */
    @Mappings({})
    Post postEditReq2Post(PostEditReq postEditReq);
    
}

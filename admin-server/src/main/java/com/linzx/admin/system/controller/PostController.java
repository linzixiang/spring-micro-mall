package com.linzx.admin.system.controller;

import com.github.pagehelper.Page;
import com.linzx.admin.system.convert.PostConvert;
import com.linzx.admin.system.domain.Post;
import com.linzx.admin.system.dto.post.request.PostAddReq;
import com.linzx.admin.system.dto.post.request.PostEditReq;
import com.linzx.admin.system.dto.post.request.PostListReq;
import com.linzx.admin.system.dto.post.response.PostListRes;
import com.linzx.admin.system.dto.post.response.PostEditRes;
import com.linzx.admin.system.service.IPostService;
import com.linzx.core.security.authz.RequiresPermissions;
import com.linzx.core.web.base.BaseController;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 岗位 
 * @author linzixiang
 * @date 2020-06-10 22:35:48
 */
@RestController
@RequestMapping("/system/post")
public class PostController extends BaseController {

    @Autowired
    private IPostService postService;
	
	@Autowired
    private PostConvert postConvert;

    /**
     * 岗位  列表
     */
    @RequiresPermissions("system:post:list")
    @RequestMapping("/list")
    public CommonAjaxResult list(PostListReq postListReq) {
        startPage();
		Post postParam = postConvert.postListRes2Post(postListReq);
        List<Post> postList = postService.selectList(postParam).get();
        Page page = (Page) postList;
        List<PostListRes> dataList = new ArrayList<>();
        for (Post post : postList) {
			PostListRes postListRes = postConvert.post2PostListRes(post);
            dataList.add(postListRes);
        }
        return CommonAjaxResult.ok().setListResult(dataList, page);
    }

    /**
     * 岗位  新增查询
     */
    @RequiresPermissions("system:post:add")
    @GetMapping("/preAdd")
    public CommonAjaxResult preAdd() {
        return CommonAjaxResult.ok();
    }

	/**
     * 岗位  新增保存
     */
    @RequiresPermissions("system:post:add")
    @PostMapping("/saveAdd")
    public CommonAjaxResult saveAdd(PostAddReq postAddReq) {
		Post post = postConvert.postAddReq2Post(postAddReq);
        postService.insert(post);
        return CommonAjaxResult.ok().setResult(post.getId());
    }

    /**
     * 岗位  修改查询
     */
    @RequiresPermissions("system:post:edit")
    @GetMapping("/preEdit/{postId}")
    public CommonAjaxResult preEdit(@PathVariable("postId") Long postId) {
        Post post = postService.getById(postId).get();
		PostEditRes postEditRes = postConvert.post2PostEditRes(post);
        return CommonAjaxResult.ok().setResult(postEditRes);
    }

	/**
     * 岗位  修改保存
     */
    @RequiresPermissions("system:post:edit")
    @PostMapping("/saveEdit")
    public CommonAjaxResult saveEdit(PostEditReq postEditReq) {
		Post post = postConvert.postEditReq2Post(postEditReq);
        postService.update(post);
        return CommonAjaxResult.ok();
    }

    /**
     * 岗位  删除，例如postId=1&postId=2
     */
    @RequiresPermissions("system:post:remove")
    @PostMapping("/remove")
    public CommonAjaxResult remove(Long[] postId) {
        postService.deleteByIds(postId);
        return CommonAjaxResult.ok();
    }
}

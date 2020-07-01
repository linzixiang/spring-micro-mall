package com.linzx.admin.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.IoUtil;
import com.github.pagehelper.Page;
import com.linzx.admin.system.convert.UserConvert;
import com.linzx.admin.system.domain.User;
import com.linzx.admin.system.dto.user.request.UserAddReq;
import com.linzx.admin.system.dto.user.request.UserEditReq;
import com.linzx.admin.system.dto.user.request.UserListReq;
import com.linzx.admin.system.dto.user.response.UserListRes;
import com.linzx.admin.system.dto.user.response.UserEditRes;
import com.linzx.admin.system.service.IUserPostService;
import com.linzx.admin.system.service.IUserRoleService;
import com.linzx.admin.system.service.IUserService;
import com.linzx.common.util.PasswordUtils;
import com.linzx.core.web.base.BaseController;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

/**
 * 用户 
 * @author linzixiang
 * @date 2020-06-10 15:01:23
 */
@RestController
@RequestMapping("/system/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
	
	@Autowired
    private UserConvert userConvert;

	@Autowired
	private IUserPostService userPostService;

	@Autowired
	private IUserRoleService userRoleService;

    /**
     * 用户 列表
     */
    @RequestMapping("/list")
    public CommonAjaxResult list(UserListReq userListReq) {
        startPage();
        List<User> userList = selectUserList(userListReq);
        Page page = (Page) userList;
        List<UserListRes> dataList = new ArrayList<>();
        for (User user : userList) {
			UserListRes userListRes = userConvert.user2UserListRes(user);
            dataList.add(userListRes);
        }
        return CommonAjaxResult.ok().setListResult(dataList, page);
    }

    /**
     * 导出或列表 数据查询
     */
    private List<User> selectUserList(UserListReq userListReq) {
        User userParam = userConvert.userListRes2User(userListReq);
        userParam.addParam("nickNameLike", userListReq.getNickNameLike());
        return userService.selectList(userParam).get();
    }

    /**
     * 用户  新增查询
     */
    @GetMapping("/preAdd")
    public CommonAjaxResult preAdd() {
        return CommonAjaxResult.ok();
    }

	/**
     * 用户  新增保存
     */
    @PostMapping("/saveAdd")
    @Transactional
    public CommonAjaxResult saveAdd(UserAddReq userAddReq) {
		User user = userConvert.userAddReq2User(userAddReq);
        user.setSalt(PasswordUtils.randomSalt());
        String password = PasswordUtils.encryptPassword(user.getAccount(), user.getPassword(), user.getSalt());
        user.setPassword(password);
        userService.insert(user);
        // 新增用户岗位关联
        userPostService.saveUserPostBatch(user.getId(), userAddReq.getPostId());
        // 新增用户角色关联
        userRoleService.saveUserRoleBatch(user.getId(), userAddReq.getRoleId());
        return CommonAjaxResult.ok().setResult(user.getId());
    }

    /**
     * 用户  修改查询
     */
    @GetMapping("/preEdit/{userId}")
    public CommonAjaxResult preEdit(@PathVariable("userId") Long userId) {
        User user = userService.getById(userId).get();
        // TODO 数据转换：User => UserEditRes
		UserEditRes userEditRes = userConvert.user2UserEditRes(user);
        return CommonAjaxResult.ok().setResult(userEditRes);
    }

	/**
     * 用户  修改保存
     */
    @PostMapping("/saveEdit")
    public CommonAjaxResult saveEdit(UserEditReq userEditReq) {
        // TODO 数据转换：UserEditReq => User
		User user = userConvert.userEditReq2User(userEditReq);
        userService.update(user);
        return CommonAjaxResult.ok();
    }

    /**
     * 用户  删除，例如userId=1&userId=2
     */
    @PostMapping("/remove")
    public CommonAjaxResult remove(Long[] userId) {
        userService.deleteByIds(userId);
        return CommonAjaxResult.ok();
    }

    @RequestMapping("/export")
    public void export(UserListReq userListReq) throws IOException {
        // 数据集准备
        List<User> userList = selectUserList(userListReq);
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (User user : userList) {
            UserListRes userListRes = userConvert.user2UserListRes(user);
            dataList.add(BeanUtil.beanToMap(userListRes));
        }
        exportExcel(dataList, UserListRes.class);
    }

}

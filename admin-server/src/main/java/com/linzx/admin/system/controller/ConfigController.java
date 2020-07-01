package com.linzx.admin.system.controller;

import com.github.pagehelper.Page;
import com.linzx.admin.system.convert.ConfigConvert;
import com.linzx.admin.system.domain.Config;
import com.linzx.admin.system.dto.config.request.ConfigAddReq;
import com.linzx.admin.system.dto.config.request.ConfigEditReq;
import com.linzx.admin.system.dto.config.request.ConfigListReq;
import com.linzx.admin.system.dto.config.response.ConfigListRes;
import com.linzx.admin.system.dto.config.response.ConfigEditRes;
import com.linzx.admin.system.service.IConfigService;
import com.linzx.core.security.authz.RequiresPermissions;
import com.linzx.core.web.base.BaseController;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数配置
 * @author linzixiang
 * @date 2020-06-21 12:54:38
 */
@RestController
@RequestMapping("/system/config")
public class ConfigController extends BaseController {

    @Autowired
    private IConfigService configService;
	
	@Autowired
    private ConfigConvert configConvert;

    /**
     * 参数配置 列表
     */
	@RequiresPermissions("system:config:list")
    @RequestMapping("/list")
    public CommonAjaxResult list(ConfigListReq configListReq) {
        startPage();
		Config configParam = configConvert.configListRes2Config(configListReq);
        List<Config> configList = configService.selectList(configParam).get();
        Page page = (Page) configList;
        List<ConfigListRes> dataList = new ArrayList<>();
        for (Config config : configList) {
			ConfigListRes configListRes = configConvert.config2ConfigListRes(config);
            dataList.add(configListRes);
        }
        return CommonAjaxResult.ok().setListResult(dataList, page);
    }

    /**
     * 参数配置 新增查询
     */
	@RequiresPermissions("system:config:add")
    @GetMapping("/preAdd")
    public CommonAjaxResult preAdd() {
        return CommonAjaxResult.ok();
    }

	/**
     * 参数配置 新增保存
     */
	@RequiresPermissions("system:config:add")
    @PostMapping("/saveAdd")
    public CommonAjaxResult saveAdd(@Validated ConfigAddReq configAddReq) {
        // TODO 数据转换：ConfigAddReq => Config
		Config config = configConvert.configAddReq2Config(configAddReq);
        configService.insert(config);
        return CommonAjaxResult.ok().setResult(config.getId());
    }

    /**
     * 参数配置 修改查询
     */
	@RequiresPermissions("system:config:edit")
    @GetMapping("/preEdit/{configId}")
    public CommonAjaxResult preEdit(@PathVariable("configId") Long configId) {
        Config config = configService.getById(configId).get();
        // TODO 数据转换：Config => ConfigEditRes
		ConfigEditRes configEditRes = configConvert.config2ConfigEditRes(config);
        return CommonAjaxResult.ok().setResult(configEditRes);
    }

	/**
     * 参数配置 修改保存
     */
	@RequiresPermissions("system:config:edit")
    @PostMapping("/saveEdit")
    public CommonAjaxResult saveEdit(@Validated ConfigEditReq configEditReq) {
        // TODO 数据转换：ConfigEditReq => Config
		Config config = configConvert.configEditReq2Config(configEditReq);
        configService.update(config);
        return CommonAjaxResult.ok();
    }

    /**
     * 参数配置 删除，例如configId=1&configId=2
     */
	@RequiresPermissions("system:config:remove")
    @PostMapping("/remove")
    public CommonAjaxResult remove(Long[] configId) {
        configService.deleteByIds(configId);
        return CommonAjaxResult.ok();
    }
}

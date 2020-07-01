package com.linzx.sso.common.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.linzx.core.common.constant.RedisKeyConstant;
import com.linzx.core.framework.redis.RedisCacheService;
import com.linzx.core.web.base.BaseController;
import com.linzx.core.web.base.vo.CommonAjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 验证码相关
 */
@Controller
@RequestMapping("/verify")
public class VerifyController extends BaseController {

    @Autowired
    public RedisCacheService redisCacheService;

    /**
     * 获取验证码
     * @return
     */
    @ResponseBody
    @GetMapping("/captchaImage")
    public CommonAjaxResult getCode() {
        String uuid = IdUtil.simpleUUID();
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(111, 36, 4, 10);
        String verifyCode = lineCaptcha.getCode(); // 随机数
        String verifyKey = RedisKeyConstant.CAPTCHA_CODE_KEY + uuid;
        redisCacheService.setCacheObject(verifyKey, verifyCode, RedisKeyConstant.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        Map<String, String> resultMap = MapUtil.builder("uuid", uuid).put("img", lineCaptcha.getImageBase64()).build();
        return CommonAjaxResult.ok().setResult(resultMap);
    }

}

package com.linzx.doc;

import com.power.common.enums.HttpCodeEnum;
import com.power.common.util.DateTimeUtil;
import com.power.doc.builder.ApiDocBuilder;
import com.power.doc.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SmartDocTest {

    @Test
    public void testBuilderControllersApi() {
        ApiConfig config = new ApiConfig();
        config.setServerUrl("http://localhost:8080");

        //设置为严格模式，Smart-doc将降至要求每个Controller暴露的接口写上标准文档注释
        config.setStrict(true);

        //当把AllInOne设置为true时，Smart-doc将会把所有接口生成到一个Markdown、HHTML或者AsciiDoc中
        config.setAllInOne(true);

        //Set the api document output path.
        config.setOutPath("C:\\Users\\Administrator.SC-201903222216\\Desktop");

        // 设置接口包扫描路径过滤，如果不配置则Smart-doc默认扫描所有的接口类
        // 配置多个报名有英文逗号隔开
        config.setPackageFilters("com.linzx.sso.controller");

        //since 1.7.9 新增是否显示接口作者 默认true
        config.setShowAuthor(false);
        //设置公共请求头.如果不需要请求头，则无需设置
        config.setRequestHeaders(
                ApiReqHeader.header().setName("access_token").setType("string")
                        .setDesc("Basic auth credentials").setRequired(true).setSince("v1.1.0"),
                ApiReqHeader.header().setName("user_uuid").setType("string").setDesc("User Uuid key")
        );

        //设置错误错列表，遍历自己的错误码设置给Smart-doc即可
        List<ApiErrorCode> errorCodeList = new ArrayList<>();
        for (HttpCodeEnum codeEnum : HttpCodeEnum.values()) {
            ApiErrorCode errorCode = new ApiErrorCode();
            errorCode.setValue(codeEnum.getCode()).setDesc(codeEnum.getMessage());
            errorCodeList.add(errorCode);
        }

        //不指定SourcePaths默认加载代码为项目src/main/java下的,如果项目的某一些实体来自外部代码可以一起加载
        config.setSourceCodePaths(
                //自1.7.0版本开始，在此处可以不设置本地代码路径，单独添加外部代码路径即可
                // SourceCodePath.path().setDesc("本项目代码").setPath("src/main/java"),
                SourceCodePath.path().setDesc("加载项目外代码").setPath("E:\\study_note\\branches\\spring-micro-mall\\sso-server\\src\\main\\java")
        );
        //不需要显示错误码,则可以不用设置错误码。
        config.setErrorCodes(errorCodeList);

        //1.7.9 优化了错误码处理，用于下面替代遍历枚举设置错误码
        //不需在文档中展示错误码则可以不设置。
        config.setErrorCodeDictionaries(
                ApiErrorCodeDictionary.dict().setEnumClass(HttpCodeEnum.class)
                        .setCodeField("code") //错误码值字段名
                        .setDescField("desc")//错误码描述
        );


        //设置文档变更记录，没有需要可以不设置
        config.setRevisionLogs(
                RevisionLog.getLog().setRevisionTime("2018/12/15").setAuthor("chen").setRemarks("test").setStatus("create").setVersion("V1.0"),
                RevisionLog.getLog().setRevisionTime("2018/12/16").setAuthor("chen2").setRemarks("test2").setStatus("update").setVersion("V2.0")
        );

        long start = System.currentTimeMillis();
        //生成Markdown文件
        //since 1.8.1版本开始入口方法有变更
        ApiDocBuilder.buildApiDoc(config);
        long end = System.currentTimeMillis();
        DateTimeUtil.printRunTime(end, start);
    }

}

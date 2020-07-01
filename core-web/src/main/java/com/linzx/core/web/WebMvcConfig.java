package com.linzx.core.web;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.linzx.core.web.exception.handler.GlobalExceptionHandler;
import com.linzx.core.web.interceptor.SessionTokenInterceptor;
import com.linzx.core.web.interceptor.SignVerifyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 添加FastJson转换器
        //1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //2:添加fastJson的配置信息;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteNullListAsEmpty, // List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null,输出为"",而非null
                SerializerFeature.DisableCircularReferenceDetect, // 消除对同一对象循环引用的问题
                SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null,输出为false
                SerializerFeature.WriteDateUseDateFormat
        );
//        SensitivePropSerialize propSerialize = new SensitivePropSerialize();
//        ValueToStrFilter valueToStrFilter = new ValueToStrFilter();
//        fastJsonConfig.setSerializeFilters(valueToStrFilter, propSerialize);
        //3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        //4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(0, fastJsonHttpMessageConverter); // 放在首位，优先级最高
        // 修改字符串编码
        for (HttpMessageConverter<?> messageConverter : converters) {
            if (messageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) messageConverter).setDefaultCharset(CharsetUtil.CHARSET_UTF_8);
            }
        }
        super.extendMessageConverters(converters);
    }

    /**
     * 拦截器
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SignVerifyInterceptor());
        registry.addInterceptor(new SessionTokenInterceptor());
    }

    /**
     * 统一异常处理
     * @param exceptionResolvers
     */
    @Override
    protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new GlobalExceptionHandler());
    }

    /**
     * 配置访问静态资源
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(!registry.hasMappingForPattern("/static/**")){
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
        super.addResourceHandlers(registry);
    }
    
}

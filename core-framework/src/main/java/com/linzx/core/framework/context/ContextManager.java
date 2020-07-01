package com.linzx.core.framework.context;

import cn.hutool.core.util.StrUtil;
import com.linzx.core.framework.support.xml.bean.CodeBean;
import com.linzx.core.framework.support.xml.bean.DictBean;
import org.springframework.beans.BeanUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 上下文管理
 */
public class ContextManager {

    /** 选项表字典配置文件缓存 */
    private static final Map<String, CodeBean> codeMap = new ConcurrentHashMap<>();
    /** 选项字典配置文件缓存 **/
    private static final Map<String, DictBean> dictMap = new ConcurrentHashMap<>();

    /**
     * 表字典
     */
    public static final void addCode(String keyCode, CodeBean codeBean) {
        if (StrUtil.isEmpty(keyCode)) {
            throw new RuntimeException("CodeBean属性" + CodeBean.UNIQUE_KEYNAME + "不允许为空");
        }
        if (codeMap.containsKey(keyCode)) {
            throw new RuntimeException("CodeBean属性" + CodeBean.UNIQUE_KEYNAME + "已存在：" + keyCode);
        }
        codeMap.put(keyCode, codeBean);
    }

    /**
     * 字典
     */
    public static final void addDict(String keyCode, DictBean dictBean) {
        if (StrUtil.isEmpty(keyCode)) {
            throw new RuntimeException("DictBean属性" + DictBean.UNIQUE_KEYNAME + "不允许为空");
        }
        if (dictMap.containsKey(keyCode)) {
            throw new RuntimeException("DictBean属性" + DictBean.UNIQUE_KEYNAME + "已存在：" + keyCode);
        }
        dictMap.put(keyCode, dictBean);
    }

    public static final CodeBean getCodeBeanCopy(String keyCode) {
        CodeBean codeBean = codeMap.get(keyCode);
        if (codeBean != null) {
            // 复制一份出来，允许设置传递参数
            CodeBean codeBeanCopy = new CodeBean();
            BeanUtils.copyProperties(codeBean, codeBeanCopy);
            return codeBeanCopy;
        }

        return codeBean;
    }

    public static final DictBean getDictBeanCopy(String keyCode) {
        DictBean codeBean = dictMap.get(keyCode);
        if (codeBean != null) {
            // 复制一份出来，允许设置传递参数
            DictBean dictBeanCopy = new DictBean();
            BeanUtils.copyProperties(codeBean, dictBeanCopy);
            return dictBeanCopy;
        }

        return codeBean;
    }

}

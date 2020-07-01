package com.linzx.generator.config;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.map.MapProxy;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.StrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 代码生成配置
 */
public class GenConfig {

    private static final ThreadLocal<MapProxy> mapProxyThreadLocal = new ThreadLocal<>();

    public static final String TEMPLATES_PATH = "templates";

    /**
     * 作者
     */
    public static final String AUTHOR = "author";

    /**
     * 包名称
     */
    public static final String PACKAGE_NAME = "basePackageName";

    /**
     * 项目名称
     */
    public static final String PROJECT_NAME = "projectName";

    /**
     * 模块名称
     */
    public static final String MODULE_NAME = "moduleName";

    /**
     * 需要移除的表前缀，如果为空，则不需要移除
     */
    public static final String TABLE_PREFIX = "tablePrefix";

    /**
     * 类型转换
     */
    public static final Map<String, String> javaTypeMap = new HashMap<String, String>();

    static {
        javaTypeMap.put("tinyint", "java.lang.Integer");
        javaTypeMap.put("smallint", "java.lang.Integer");
        javaTypeMap.put("mediumint", "java.lang.Integer");
        javaTypeMap.put("int", "java.lang.Integer");
        javaTypeMap.put("number", "java.lang.Integer");
        javaTypeMap.put("integer", "java.lang.Integer");
        javaTypeMap.put("bigint", "java.lang.Long");
        javaTypeMap.put("float", "java.lang.Float");
        javaTypeMap.put("double", "java.lang.Double");
        javaTypeMap.put("decimal", "java.math.BigDecimal");
        javaTypeMap.put("bit", "java.lang.Boolean");
        javaTypeMap.put("char", "java.lang.String");
        javaTypeMap.put("varchar", "java.lang.String");
        javaTypeMap.put("varchar2", "java.lang.String");
        javaTypeMap.put("tinytext", "java.lang.String");
        javaTypeMap.put("text", "java.lang.String");
        javaTypeMap.put("mediumtext", "java.lang.String");
        javaTypeMap.put("longtext", "java.lang.String");
        javaTypeMap.put("time", "java.util.Date");
        javaTypeMap.put("date", "java.util.Date");
        javaTypeMap.put("datetime", "java.util.Date");
        javaTypeMap.put("timestamp", "java.util.Date");
    }

    /**
     * 加载配置，放到线程变量
     */
    private static void loadConfig() throws IOException {
        // 加载配置
        InputStream inputStream = ResourceUtil.getStream("generator.properties");
        Properties prop = new Properties();
        prop.load(inputStream);
        Map<String, String> propMap = new HashMap<String, String>((Map) prop);
        MapProxy mapProxy = MapUtil.createProxy(propMap);
        mapProxyThreadLocal.set(mapProxy);
    }

    /**
     * 移除配置
     */
    public static void removeConfig() {
        mapProxyThreadLocal.remove();
    }

    /**
     * 读取配置
     */
    public static String getConfigStr(String key) {
        MapProxy mapProxy = mapProxyThreadLocal.get();
        if (mapProxy == null) {
            synchronized (GenConfig.class) {
                if (mapProxy == null) {
                    try {
                        loadConfig();
                        mapProxy = mapProxyThreadLocal.get();
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RuntimeException("配置加载错误");
                    }
                }
            }
        }
        return mapProxy.getStr(key);
    }

    /**
     * 生成的java文件存放路径
     * @return
     */
    public static String getJavaBasePath() {
        String packageName = getConfigStr(PACKAGE_NAME);
        String projectName = getConfigStr(PROJECT_NAME);
        String moduleName = getConfigStr(MODULE_NAME);
        StrBuilder strBuilder = StrBuilder.create("main/java/")
                .append(packageName.replace(".", "/"))
                .append("/" + projectName)
                .append("/" + moduleName)
                .append("/");
        return strBuilder.toString();
    }

    /**
     * 生成的Mybatis的xml文件存放的路径
     * @return
     */
    public static String getMybatisPath() {
        String projectName = getConfigStr(PROJECT_NAME);
        String moduleName = getConfigStr(MODULE_NAME);
        StrBuilder strBuilder = StrBuilder.create("main/resources/mapper")
                .append("/").append(projectName)
                .append("/").append(moduleName)
                .append("/");
        return strBuilder.toString();
    }

    /**
     * 生成的sql文件路径
     * @return
     */
    public static String getSqlPath() {
        return StrBuilder.create("main/sql/").toString();
    }

}

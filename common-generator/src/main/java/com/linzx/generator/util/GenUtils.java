package com.linzx.generator.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.linzx.generator.config.GenConfig;
import com.linzx.generator.domain.ColumnInfo;
import com.linzx.generator.domain.TableInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenUtils {

    /**
     * 表名转换成Java类名，驼峰命名且要求首字母大写
     */
    public static String tableToJava(String tableName) {
        String tablePrefix = GenConfig.getConfigStr(GenConfig.TABLE_PREFIX);
        if (StrUtil.isNotBlank(tablePrefix)) {
            tableName = tableName.replaceFirst(tablePrefix, "");
        }
        return StrUtil.upperFirst(StrUtil.toCamelCase(tableName));
    }

    /**
     * 设置列信息
     */
    public static List<ColumnInfo> transColums(List<ColumnInfo> columns) {
        // 列信息
        List<ColumnInfo> columsList = new ArrayList<>();
        for (ColumnInfo column : columns) {
            // 列名转换成Java属性名
            String attrName = StrUtil.toCamelCase(column.getColumnName());
            column.setAttrName(StrUtil.upperFirst(attrName));
            column.setAttrname(attrName);

            // 列的数据类型，转换成Java类型
            String attrType = GenConfig.javaTypeMap.get(column.getDataType());
            column.setAttrType(attrType);
            
            columsList.add(column);
        }
        return columsList;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, TableInfo table) {
        // 小写类名
        String classname = table.getClassname();
        // 大写类名
        String className = table.getClassName();

        String javaPath = GenConfig.getJavaBasePath();
        String mybatisPath = GenConfig.getMybatisPath();
        String sqlPath = GenConfig.getSqlPath();
        if (template.contains("Controller.java.ftl")) {
            return javaPath + "controller/" + className + "Controller.java";
        }
        if (template.contains("Domain.java.ftl")) {
            return javaPath + "domain/" + className + ".java";
        }
        if (template.contains("AddReq.java.ftl")) {
            return javaPath + "dto/" + StrUtil.lowerFirst(className) + "/request/" + className + "AddReq.java";
        }
        if (template.contains("EditReq.java.ftl")) {
            return javaPath + "dto/" + StrUtil.lowerFirst(className) + "/request/" + className + "EditReq.java";
        }
        if (template.contains("ListReq.java.ftl")) {
            return javaPath + "dto/" + StrUtil.lowerFirst(className) + "/request/" + className + "ListReq.java";
        }
        if (template.contains("ListRes.java.ftl")) {
            return javaPath + "dto/" + StrUtil.lowerFirst(className) + "/response/" + className + "ListRes.java";
        }
        if (template.contains("EditRes.java.ftl")) {
            return javaPath + "dto/" + StrUtil.lowerFirst(className) + "/response/" + className + "EditRes.java";
        }
        if (template.contains("Mapper.java.ftl")) {
            return javaPath + "mapper" + "/" + className + "Mapper.java";
        }
        if (template.contains("Service.java.ftl")) {
            return javaPath + "service/I" + className + "Service.java";
        }
        if (template.contains("ServiceImpl.java.ftl")) {
            return javaPath + "service/impl/" + className + "Service.java";
        }
        if (template.contains("Convert.java.ftl")) {
            return javaPath + "convert/" + className + "Convert.java";
        }
        if (template.contains("Mapper.xml.ftl")) {
            return mybatisPath + className + "Mapper.xml";
        }
        if (template.contains("exec.sql.ftl")) {
            return sqlPath + table.getTableName() + "_exec.sql";
        }
        throw new RuntimeException(GenConfig.TEMPLATES_PATH +"模板不存在：" + template);
    }

    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("java/AddReq.java.ftl");
        templates.add("java/Controller.java.ftl");
        templates.add("java/Domain.java.ftl");
        templates.add("java/EditReq.java.ftl");
        templates.add("java/ListReq.java.ftl");
        templates.add("java/ListRes.java.ftl");
        templates.add("java/Mapper.java.ftl");
        templates.add("java/Service.java.ftl");
        templates.add("java/ServiceImpl.java.ftl");
        templates.add("java/EditRes.java.ftl");
        templates.add("java/Convert.java.ftl");
        templates.add("xml/Mapper.xml.ftl");
        templates.add("sql/exec.sql.ftl");
        return templates;
    }


    public static String replaceKeyword(String keyword) {
        String keyName = keyword.replaceAll("(?:表|信息|管理)", "");
        return keyName;
    }

    public static String getBasePackage(String packageName) {
        int lastIndex = packageName.lastIndexOf(".");
        String basePackage = StrUtil.sub(packageName, 0, lastIndex);
        return basePackage;
    }

    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static Map<String, Object> getTemplateContext(TableInfo table) {
        // java对象数据传递到模板文件vm
        Map<String, Object> contextMap = new HashMap<>();
        String basePackageName = GenConfig.getConfigStr(GenConfig.PACKAGE_NAME);
        String author = GenConfig.getConfigStr(GenConfig.AUTHOR);
        String moduleName = GenConfig.getConfigStr(GenConfig.MODULE_NAME);
        String projectName = GenConfig.getConfigStr(GenConfig.PROJECT_NAME);
        contextMap.put("tableName", table.getTableName());
        contextMap.put("tableComment", replaceKeyword(table.getTableComment()));
        contextMap.put("primaryKey", table.getPrimaryKey());
        contextMap.put("className", table.getClassName());
        contextMap.put("classname", table.getClassname());
        contextMap.put("moduleName", moduleName);
        contextMap.put("columns", table.getColumns());
        contextMap.put("basePackageName", basePackageName);
        contextMap.put("projectName", projectName);
        contextMap.put("author", author);
        contextMap.put("datetime", DateUtil.now());
        return contextMap;
    }

}

package com.linzx.generator;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.alibaba.fastjson.JSONObject;
import com.linzx.generator.config.GenConfig;
import com.linzx.generator.domain.ColumnInfo;
import com.linzx.generator.domain.TableInfo;
import com.linzx.generator.util.GenUtils;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GenService {

    /**
     * 查询表信息
     * @param tableName 表名称
     * @return
     */
    public TableInfo queryDbTableInfo(String tableName) throws Exception {
        // 查询表信息
        String sqlQueryTableInfo = StrBuilder.create("select table_name, table_comment, create_time, update_time")
                .append(" from information_schema.tables")
                .append(" where table_comment <> '' and table_schema = (select database()) and table_name = ?")
                .toString();
        TableInfo tableInfo = Db.use().query(sqlQueryTableInfo, TableInfo.class, tableName).get(0);
        if (tableInfo == null) {
            throw new RuntimeException("表信息查询为空，请检查");
        }
        tableInfo.setClassName(GenUtils.tableToJava(tableInfo.getTableName()));
        tableInfo.setClassname(StrUtil.lowerFirst(tableInfo.getClassName()));
        // 查询列信息
        String sqlQueryColumnInfo = StrBuilder.create("select column_name as columnName, data_type as dataType, column_comment as columnComment, extra")
                .append(" from information_schema.columns")
                .append(" where table_name = ? and table_schema = (select database()) order by ordinal_position")
                .toString();
        List<ColumnInfo> columnInfoList = Db.use().query(sqlQueryColumnInfo, ColumnInfo.class, tableName);
        List<ColumnInfo> transColums = GenUtils.transColums(columnInfoList);
        // 列信息
        tableInfo.setColumns(transColums);
        // 设置主键
        tableInfo.setPrimaryKey(tableInfo.getColumnsLast());
        return tableInfo;
    }

    /**
     * 生成代码
     * @param outputStream 输入流
     * @param tableInfo 表信息
     */
    public void generatorCode(OutputStream outputStream, TableInfo tableInfo) {
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        try {
            TemplateConfig templateConfig = new TemplateConfig("templates", TemplateConfig.ResourceMode.CLASSPATH);
            TemplateEngine engine = TemplateUtil.createEngine(templateConfig);
            // 获取渲染数据
            Map<String, Object> contextData = GenUtils.getTemplateContext(tableInfo);
            // 获取模板列表
            List<String> templates = GenUtils.getTemplates();
            for (String template : templates) {
                // 渲染模板
                StringWriter sw = new StringWriter();
                Template tpl = engine.getTemplate(template);
                tpl.render(contextData, sw);
                zip.putNextEntry(new ZipEntry(GenUtils.getFileName(template, tableInfo)));
                IoUtil.write(zip, "UTF-8", false, sw.toString());
                IoUtil.close(sw);
                zip.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(zip);
        }
    }

}

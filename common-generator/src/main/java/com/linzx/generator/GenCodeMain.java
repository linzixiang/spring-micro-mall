package com.linzx.generator;

import cn.hutool.core.io.IoUtil;
import com.linzx.generator.config.GenConfig;
import com.linzx.generator.domain.TableInfo;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class GenCodeMain {

    public static final String path = "C:\\Users\\Administrator.SC-201903222216\\Desktop";

    public static final String tableName = "sys_config";

//    public static final String path = "C:\\Users\\LZX\\Desktop";

    public static void main(String[] args) {
        GenService genService = new GenService();
        OutputStream outputStream = null;
        try {
            TableInfo tableInfo = genService.queryDbTableInfo(tableName);
            outputStream = new FileOutputStream(path + "\\" + tableInfo.getTableName() + ".zip");
            genService.generatorCode(outputStream, tableInfo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(outputStream);
            GenConfig.removeConfig();
        }
    }

}

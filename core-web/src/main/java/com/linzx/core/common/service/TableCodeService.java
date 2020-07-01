package com.linzx.core.common.service;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import com.linzx.core.framework.support.xml.bean.CodeBean;
import com.linzx.core.web.base.vo.TableCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service("tableCodeService")
public class TableCodeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询表编码
     */
    public List<TableCode> queryTableCode(CodeBean codeBean) {
        StrBuilder builder = StrBuilder.create();
        builder.append("select " + codeBean.getNameField() + " as `name`, " + codeBean.getValueField() + " as `value`")
                .append(" from " + codeBean.getTableName());
        builder.append(" where 1 = 1");
        if (StrUtil.isNotBlank(codeBean.getWhereExt())) {
            builder.append(" and " + codeBean.getWhereExt());
        }
        String searchField = StrUtil.isNotBlank(codeBean.getSearchField()) ? codeBean.getSearchField() : codeBean.getNameField();
        if (StrUtil.isNotBlank(codeBean.getSearchKey())) {
            builder.append(" and " + searchField + " like '%" + codeBean.getSearchKey() + "%'");
        }
        if (StrUtil.isNotBlank(codeBean.getOrderBy())) {
            builder.append(" order by " + codeBean.getOrderBy());
        }
        if (codeBean.getPageEnable()) { // 是否需要分页
            if (codeBean.getPageNum() != null && codeBean.getPageSize() != null) {
                builder.append(" limit " + (codeBean.getPageNum() - 1) * codeBean.getPageSize()  + "," + codeBean.getPageSize());
            } else if (codeBean.getPageSize() != null) {
                builder.append(" limit " + codeBean.getPageSize());
            }
        }
        List<TableCode> tableCodeList = jdbcTemplate.query(builder.toString(), new RowMapper<TableCode>() {

            @Override
            public TableCode mapRow(ResultSet rs, int rowNum) throws SQLException {
                TableCode tableCode = new TableCode();
                tableCode.setName(rs.getString("name"));
                tableCode.setValue(rs.getString("value"));
                return tableCode;
            }
        });
        return tableCodeList;
    }

}

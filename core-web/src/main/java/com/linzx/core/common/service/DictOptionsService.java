package com.linzx.core.common.service;

import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import com.linzx.core.framework.base.BaseEntity;
import com.linzx.core.framework.support.xml.bean.DictBean;
import com.linzx.core.web.base.vo.DictOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service("dictOptionsService")
public class DictOptionsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询字典
     * @param dictBean
     * @return
     */
    public List<DictOption> queryDict(DictBean dictBean) {
        StrBuilder builder = StrBuilder.create();
        builder.append(" select dict_label as dictLabel, dict_value as dictValue, list_class as listStyle");
        builder.append(" from sys_dict_option");
        builder.append(" where dict_code = '" + dictBean.getDictCode() +"'");
        if (StrUtil.isNotBlank(dictBean.getWhereExt())) {
            builder.append(" " + dictBean.getWhereExt());
        }
        if (dictBean.getExcludeDelete()) {
            builder.append(" and `status` != " + BaseEntity.STATUS_DEL);
        }
        if (dictBean.getExcludeStop()) {
            builder.append(" and `status` != " + BaseEntity.STATUS_STOP);
        }
        if (StrUtil.isNotBlank(dictBean.getOrderBy())) {
            builder.append(" order by " + dictBean.getOrderBy());
        }
        List<DictOption> dictOptions = jdbcTemplate.query(builder.toString(), new RowMapper<DictOption>() {
            @Override
            public DictOption mapRow(ResultSet rs, int rowNum) throws SQLException {
                DictOption dictOption = new DictOption();
                dictOption.setDictLabel(rs.getString("dictLabel"));
                dictOption.setDictValue(rs.getString("dictValue"));
                dictOption.setListStyle(rs.getString("listStyle"));
                return dictOption;
            }
        });
        return dictOptions;
    }

}

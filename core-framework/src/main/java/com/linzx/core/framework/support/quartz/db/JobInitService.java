package com.linzx.core.framework.support.quartz.db;

import cn.hutool.core.text.StrBuilder;
import com.linzx.core.framework.support.quartz.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务初始化
 */
public class JobInitService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取所有的任务
     * @return
     */
    public List<Job> queryAllJobList() {
        StrBuilder builder = StrBuilder.create();
        builder.append(" select job_id as jobId, job_name as jobName, job_group as jobGroup, cron as cron, class_path as classPath, param_json as paramJson, status as status, remark as remark");
        List<Job> jobList = jdbcTemplate.query(builder.toString(), new RowMapper<Job>() {
            @Override
            public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
                Job job = new Job();
                job.setJobId(rs.getLong("jobId"));
                job.setJobName(rs.getString("jobName"));
                job.setJobGroup(rs.getString("jobGroup"));
                job.setCron(rs.getString("cron"));
                job.setClassPath(rs.getString("classPath"));
                job.setParamJson(rs.getString("paramJson"));
                job.setStatus(rs.getInt("status"));
                job.setRemark(rs.getString("remark"));
                return job;
            }
        });
        return jobList;
    }



}

package com.linzx.core.framework.base;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库实体几类
 * @param <PK>
 */
public abstract class BaseEntity<PK> implements Serializable, IPrimaryKey<PK>  {

    public static final Integer STATUS_NORMAL = 1; // 正常
    public static final Integer STATUS_STOP = 2; // 停用
    public static final Integer STATUS_DEL = 3; // 删除

    /** 状态 （1正常 2停用 3 删除） */
    private Integer status;
    /** 创建者 */
    private Long createBy;
    /** 创建时间 */
    private Date createTime;
    /** 更新者 */
    private Long updateBy;
    /** 更新时间 */
    private Date updateTime;
    /** 锁版本号 */
    private Integer reversion;
    /** 备注 */
    private String remark;
    ///////////////////////////////////////////////////查询条件参数////////////////////////////////////////////////////////////
    /** 创建起始时间，例如：2019-07-24 **/
    private String createTimeGte = "";
    /** 创建结束时间 **/
    private String createTimeLte = "";
    /** 更新起始时间 **/
    private String updateTimeGte = "";
    /** 更新结束时间 **/
    private String updateTimeLte = "";
    /** 备注模糊匹配 **/
    private String remarkLike = "";
    /** 参数 */
    private Map<String, Object> params;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getReversion() {
        return reversion;
    }

    public void setReversion(Integer reversion) {
        this.reversion = reversion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatedTimeGte() {
        if (StrUtil.isNotBlank(createTimeGte) && createTimeGte.length() == 10) {
            createTimeGte = createTimeGte + " 00:00:00";
        }
        return createTimeGte;
    }

    public void setCreatedTimeGte(String createdTimeGte) {
        this.createTimeGte = createdTimeGte;
    }

    public String getCreatedTimeLte() {
        if (StrUtil.isNotBlank(createTimeLte) && createTimeLte.length() == 10) {
            createTimeLte = createTimeLte + " 23:59:59";
        }
        return createTimeLte;
    }

    public void setCreatedTimeLte(String createdTimeLte) {
        this.createTimeLte = createdTimeLte;
    }

    public String getUpdatedTimeGte() {
        if (StrUtil.isNotBlank(updateTimeGte) && updateTimeGte.length() == 10) {
            updateTimeGte = updateTimeGte + " 00:00:00";
        }
        return updateTimeGte;
    }

    public void setUpdatedTimeGte(String updatedTimeGte) {
        this.updateTimeGte = updatedTimeGte;
    }

    public String getUpdatedTimeLte() {
        if (StrUtil.isNotBlank(updateTimeLte) && updateTimeLte.length() == 10) {
            updateTimeLte = updateTimeLte + " 23:59:59";
        }
        return updateTimeLte;
    }

    public void setUpdatedTimeLte(String updatedTimeLte) {
        this.updateTimeLte = updatedTimeLte;
    }

    public String getRemarkLike() {
        return remarkLike;
    }

    public void setRemarkLike(String remarkLike) {
        this.remarkLike = remarkLike;
    }

    public Map<String, Object> getParams() {
        if (this.params == null) {
            this.params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    /**
     * 添加参数
     */
    public void addParam(String key, Object value) {
        if (this.params == null) {
            this.params = new HashMap<>();
        }
        this.params.put(key, value);
    }

    /**
     * 增加查询条件
     */
    public void addOrderField(String orderField, boolean isAsc) {
        String orderBy = MapUtil.getStr(getParams(), "orderBy");
        if (StrUtil.isNotBlank(orderBy)) {
            String orderAppend = orderField + " " + (isAsc ? "asc" : "desc");
            orderBy = orderBy + "," + orderAppend;
        } else {
            orderBy = orderField + " " + (isAsc ? "asc" : "desc");
        }
        addParam("orderBy", orderBy);
    }

}

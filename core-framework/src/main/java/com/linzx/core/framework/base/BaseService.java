package com.linzx.core.framework.base;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.linzx.core.common.bean.UserLoginPrincipal;
import com.linzx.core.framework.base.annotation.Table;
import com.linzx.core.framework.context.ThreadLocalVariable;
import com.linzx.core.framework.tinyid.IdGeneratorFactory;
import com.linzx.core.framework.tinyid.IdUtils;
import com.xiaoju.uemc.tinyid.base.generator.IdGenerator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<PK,E extends BaseEntity<PK>, DAO extends BaseMapper<PK, E>> implements InitializingBean {

    protected DAO baseDAO;

    @Autowired
    IdGeneratorFactory idGeneratorFactory;

    /**
     * 根据id查询
     */
    public Optional<E> getById(PK id) {
        return Optional.ofNullable(baseDAO.getById(id));
    }

    /**
     * 根据id查询
     */
    public Optional<List<E>> selectByIds(PK[] ids) {
        return Optional.of(baseDAO.selectByIds(ids));
    }

    /**
     * 列表查询
     */
    public Optional<List<E>> selectList(E entity) {
        return Optional.of(baseDAO.selectList(entity));
    }

    /**
     * 插入单条记录
     */
    public Integer insert(E entity) {
        UserLoginPrincipal loginPrincipal = ThreadLocalVariable.getUserLoginPricipal();
        Date date = new Date();
        entity.setCreateTime(date);
        entity.setUpdateTime(date);
        entity.setCreateBy(Convert.toLong(loginPrincipal.getUserId()));
        if (entity.getId() == null) {
            entity.setId((PK) IdUtils.getDBSequenceNextId(entity));
        }
        return baseDAO.insert(entity);
    }

    /**
     * 批量插入
     */
    public Integer insertBatch(List<E> entityList) {
        if (CollectionUtil.isEmpty(entityList)) {
            return 0;
        }
        UserLoginPrincipal loginPrincipal = ThreadLocalVariable.getUserLoginPricipal();
        for(E entity : entityList) {
            if (loginPrincipal != null) {
                entity.setCreateBy(Convert.toLong(loginPrincipal.getUserId()));
                entity.setUpdateBy(Convert.toLong(loginPrincipal.getUserId()));
            }
            Date date = new Date();
            entity.setCreateTime(date);
            entity.setUpdateTime(date);
            if (entity.getId() == null) {
                entity.setId((PK) IdUtils.getDBSequenceNextId(entity));
            }
        }
        return baseDAO.insertBatch(entityList);
    }

    /**
     * 更新
     */
    public Integer update(E entity) {
        UserLoginPrincipal loginPrincipal = ThreadLocalVariable.getUserLoginPricipal();
        if (loginPrincipal != null) {
            entity.setUpdateBy(Convert.toLong(loginPrincipal.getUserId()));
        }
        entity.setUpdateTime(new Date());
        return baseDAO.update(entity);
    }

    /**
     * 单条记录删除
     */
    public Integer deleteById(PK id) {
        return baseDAO.deleteById(id);
    }

    /**
     * 数据批量删除
     */
    public Integer deleteByIds(PK[] ids) {
        if (ids == null || ids.length == 0) {
            return 0;
        }
        return baseDAO.deleteByIds(ids);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.baseDAO = this.getMapper();
    }

    protected abstract DAO getMapper();
}

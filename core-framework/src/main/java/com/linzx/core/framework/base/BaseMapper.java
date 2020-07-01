package com.linzx.core.framework.base;

import java.util.List;

/**
 * mybatis基础功能接口
 * @param <PK>
 * @param <E>
 */
public interface BaseMapper<PK, E extends BaseEntity<PK>>  {

    /**
     * 主键查询
     * @param id
     * @return
     */
    E getById(PK id);

    /**
     * 多个主键查询
     * @param entityIds
     * @return
     */
    List<E> selectByIds(PK[] entityIds);

    /**
     * 复杂查询列表
     * @param entity
     * @return
     */
    List<E> selectList(E entity);

    /**
     * 新增
     * @param entity
     * @return 成功的数量
     */
    Integer insert(E entity);

    /**
     * 批量新增
     * @param entity
     * @return 成功的数量
     */
    Integer insertBatch(List<E> entity);

    /**
     * 更新
     * @param entity
     * @return
     */
    Integer update(E entity);

    /**
     * 根据主键删除记录
     * @param entityId
     * @return
     */
    Integer deleteById(PK entityId);

    /**
     * 根据主键批量删除记录
     * @param entityIds
     * @return
     */
    Integer deleteByIds(PK[] entityIds);

}

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackageName}.${projectName}.${moduleName}.mapper.${className}Mapper">

    <select id="selectList" parameterType="${basePackageName}.${projectName}.${moduleName}.domain.${className}"
            resultType="${basePackageName}.${projectName}.${moduleName}.domain.${className}">
        <include refid="selectVo"/>
        <include refid="selectList_where"/>
		<if test="params.orderBy != null and params.orderBy != ''">
            ORDER BY <#noparse>${params.orderBy}</#noparse>
        </if>
    </select>

    <select id="getById" parameterType="java.lang.Long" resultType="${basePackageName}.${projectName}.${moduleName}.domain.${className}">
        <include refid="selectVo"/>
        where ${primaryKey.columnName} = <#noparse>#</#noparse>{${primaryKey.attrname}}
    </select>

    <select id="selectByIds" parameterType="java.lang.Long" resultType="${basePackageName}.${projectName}.${moduleName}.domain.${className}">
        <include refid="selectVo"/>
        where ${primaryKey.columnName} in
        <foreach item="${primaryKey.attrname}" collection="array" open="(" separator="," close=")">
            <#noparse>#</#noparse>{${primaryKey.attrname}}
        </foreach>
    </select>

    <insert id="insert" parameterType="${basePackageName}.${projectName}.${moduleName}.domain.${className}">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list columns as columnInfo>
            <if test="${columnInfo.attrname} != null">${columnInfo.columnName},</if>
            </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <#list columns as columnInfo>
            <if test="${columnInfo.attrname} != null"><#noparse>#</#noparse>{${columnInfo.attrname}},</if>
            </#list>
        </trim>
    </insert>
	
	<insert id="insertBatch" parameterType="${basePackageName}.${projectName}.${moduleName}.domain.${className}">
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <#list columns as columnInfo>
            <if test="list[0].${columnInfo.attrname} != null">${columnInfo.columnName},</if>
            </#list>
        </trim>
		<foreach item="${classname}" collection="list" open="values" separator=",">
            <trim prefix="(" suffix=")" suffixOverrides=",">
			<#list columns as columnInfo>
            <if test="list[0].${columnInfo.attrname} != null"><#noparse>#</#noparse>{${classname}.${columnInfo.attrname}},</if>
            </#list>
            </trim>
        </foreach>
    </insert>

    <update id="update" parameterType="${basePackageName}.${projectName}.${moduleName}.domain.${className}">
        update ${tableName}
        <trim prefix="SET" suffixOverrides=",">
            <#list columns as columnInfo>
            <if test="${columnInfo.attrname} != null">${columnInfo.columnName} = <#noparse>#</#noparse>{${columnInfo.attrname}},</if>
            </#list>
        </trim>
        where ${primaryKey.columnName} = <#noparse>#</#noparse>{${primaryKey.attrname}}
    </update>

    <delete id="deleteById" parameterType="java.lang.Long">
        delete from ${tableName} where ${primaryKey.columnName} = <#noparse>#</#noparse>{${primaryKey.attrname}}
    </delete>

    <delete id="deleteByIds" parameterType="java.lang.Long">
        delete from ${tableName} where ${primaryKey.columnName} in
        <foreach item="${primaryKey.attrname}" collection="array" open="(" separator="," close=")">
            <#noparse>#</#noparse>{${primaryKey.attrname}}
        </foreach>
    </delete>

    <sql id="selectVo">
        select <#list columns as columnInfo><#if columnInfo_has_next>${columnInfo.columnName} as ${columnInfo.attrname},<#else>${columnInfo.columnName} as ${columnInfo.attrname}</#if></#list> from ${tableName}
    </sql>

    <sql id="selectList_where">
        <where>
            <#list columns as columnInfo>
                <#if columnInfo.attrname != 'createTime' && columnInfo.attrname != 'updateTime' && columnInfo.attrname != 'remark'>
            <if test="${columnInfo.attrname} != null"> and ${columnInfo.columnName} = <#noparse>#</#noparse>{${columnInfo.attrname}}</if>
                </#if>
            </#list>
            <if test="createTimeGte != null and createTimeGte != ''"> and create_time &gt;= <#noparse>#</#noparse>{createTimeGte}</if>
            <if test="createTimeLte != null and createTimeLte != ''"> and create_time &lt;= <#noparse>#</#noparse>{createTimeLte}</if>
            <if test="updateTimeGte != null and updateTimeGte != ''"> and update_time &gt;= <#noparse>#</#noparse>{updateTimeGte}</if>
            <if test="updateTimeLte != null and updateTimeLte != ''"> and update_time &lt;= <#noparse>#</#noparse>{updateTimeLte}</if>
            <if test="remarkLike != null and remarkLike != ''"> and remark like <#noparse>'%${remarkLike}%'</#noparse></if>
        </where>
    </sql>
</mapper>
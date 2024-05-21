<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.model.entity.${entityName}">

    <resultMap id="BaseResultMap" type="cn.xzhang.boot.model.entity.${entityName}">
        <#list columns as column>
            <#if column.columnName == 'id'>
                <id property="id" column="id" jdbcType="BIGINT"/>
            <#else>
                <#if column.columnType == 'DATETIME'>
                    <result property="${column.columnName}" column="${column.columnName}" jdbcType="TIMESTAMP"/>
                <#elseif column.columnType == 'INT'>
                    <result property="${column.columnName}" column="${column.columnName}" jdbcType="INTEGER"/>
                <#elseif column.columnType == 'TEXT'>
                    <result property="${column.columnName}" column="${column.columnName}" jdbcType="CLOB"/>
                <#else >
                    <result property="${column.columnName}" column="${column.columnName}" jdbcType="${column.columnType}"/>
                </#if>
            </#if>
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <#list columns as column>
            ${column.columnName}<#if column_has_next>,</#if>
        </#list>
    </sql>
</mapper>

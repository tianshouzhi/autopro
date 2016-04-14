<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--对应数据库表${dbTable.tableName}-->
<mapper  namespace="${basePackage}.domain.${dbTable.entityName?cap_first}" >
  <!--查询结果集-->
  <resultMap id="${dbTable.entityName}Map" type="${dbTable.entityName?cap_first}" >
<#--迭代主键列表-->
<#list dbTable.PKColumnList as PKColumn>
	<!--主键-->
	<id property="${PKColumn.propertyName}" column="${PKColumn.columnName}"  javaType="${PKColumn.javaType}" jdbcType="${PKColumn.jdbcType}"/>
</#list>
<#--迭代普通列-->
    <!--普通属性-->
<#list dbTable.tableColumns as tableColumn>
	<result  property="${tableColumn.propertyName}" column="${tableColumn.columnName}" javaType="${tableColumn.javaType}" jdbcType="${tableColumn.jdbcType}"/>
</#list>
	
<#--迭代多对一关联关系-->
<#list dbTable.manyToOneList as relationTable>
	<!--多对一关联${relationTable.entityName?cap_first}-->
	<association property="${relationTable.entityName}" javaType="${relationTable.entityName?cap_first}">
	<#--迭代关联表主键列-->
	<#list relationTable.PKColumnList as relationPKColumn>
		<id property="${relationPKColumn.propertyName}" javaType="${relationPKColumn.javaType}" jdbcType="${relationPKColumn.jdbcType}" column="${relationPKColumn.columnName}" />
	</#list>
	<#--迭代关联表普通列-->
	<#list relationTable.tableColumns as relationTableColumn>
		<result property="${relationTableColumn.propertyName}" javaType="${relationTableColumn.javaType}" jdbcType="${relationTableColumn.jdbcType}" column="${relationTableColumn.columnName}" />
	</#list>
	</association>
</#list>

<#--迭代一对多关联关系-->
<#list dbTable.oneToManyList as relationTable>
   <!--一对多关联${relationTable.entityName?cap_first}-->
	<collection property="${relationTable.entityName}s" ofType="${relationTable.entityName?cap_first}">
	<#--迭代关联表主键列-->
	<#list relationTable.PKColumnList as relationPKColumn>
		<id property="${relationPKColumn.propertyName}" javaType="${relationPKColumn.javaType}" column="${relationPKColumn.columnName}" jdbcType="${relationPKColumn.jdbcType}"/>
	</#list>
	<#--迭代关联表普通列-->
	<#list relationTable.tableColumns as relationTableColumn>
		<result property="${relationTableColumn.propertyName}" column="${relationTableColumn.columnName}" javaType="${relationTableColumn.javaType}" jdbcType="${relationTableColumn.jdbcType}"/>
	</#list>
	</collection>
</#list>
<#--迭代多对多关联关系-->
<#list dbTable.manyToManyList as relationTable>
	 <!--多对多关联${relationTable.entityName?cap_first}-->
	<collection property="${relationTable.entityName}s" ofType="${relationTable.entityName?cap_first}">
	<#--迭代关联表主键列-->
	<#list relationTable.PKColumnList as relationPKColumn>
		<id property="${relationPKColumn.propertyName}" column="${relationPKColumn.columnName}" javaType="${relationPKColumn.javaType}" jdbcType="${relationPKColumn.jdbcType}"/>
	</#list>
	<#--迭代关联表普通列-->
	<#list relationTable.tableColumns as relationTableColumn>
		<result property="${relationTableColumn.propertyName}" column="${relationTableColumn.columnName}" javaType="${relationTableColumn.javaType}" jdbcType="${relationTableColumn.jdbcType}"/>
	</#list>
	</collection>
</#list>
  </resultMap>
  
  <!--插入-->
  <insert id="insert" parameterType="${dbTable.entityName?cap_first}">
	INSERT INTO ${dbTable.tableName} (${sqlSelectArgsString}) 
	VALUES(${insertValuesString});
  </insert>
 
 <!--根据主键进行查询-->
	<#list dbTable.PKColumnList as PKColumn>
   <select id="selectById" parameterType="${PKColumn.javaType}" resultMap="${dbTable.entityName}Map">
   	 SELECT <#list dbTable.PKColumnList as PKColumn>${PKColumn.columnName},</#list>${sqlSelectArgsString} FROM ${dbTable.tableName} 
   	 WHERE ${PKColumn.columnName}=${r"#{"}${PKColumn.propertyName}}
   </select>
   
 <!--查询所有-->
	<select id="findAll" resultMap="${dbTable.entityName}Map">
   	 SELECT <#list dbTable.PKColumnList as PKColumn>${PKColumn.columnName},</#list>${sqlSelectArgsString} FROM ${dbTable.tableName};
   </select>

 <!--根据主键删除-->   
   <delete id="deleteById" parameterType="${PKColumn.javaType}" >
     DELETE FROM ${dbTable.tableName} 
     WHERE ${PKColumn.columnName} =${r"#{"}${PKColumn.propertyName}}
   </delete>
 
 <!--根据主键批量删除-->  
   <delete id="batchDelete" parameterType="${PKColumn.javaSimpleName?lower_case}[]" >
     DELETE FROM ${dbTable.tableName} 
     WHERE ${PKColumn.columnName} IN  
     <foreach collection="array" item = "${PKColumn.propertyName}" open="(" separator="," close=")">${r"#{"}${PKColumn.propertyName}}</foreach>
   </delete>
 
 <!--更新-->  
	<update id="updateById" parameterType="${dbTable.entityName?cap_first}">
		UPDATE ${dbTable.tableName} 
		<set>
			${updateValuesString}
		</set>
		 WHERE ${PKColumn.columnName}=${r"#{"}${PKColumn.propertyName}}; 
	</update>
	</#list>
	
 <!--复杂查询：分页-->	
  <select id="getPageBeanByCondition" parameterType="${basePackage}.domain.searchvo.${dbTable.entityName?cap_first}SearchVo" resultMap="${dbTable.entityName}Map">
  	SELECT <#list dbTable.PKColumnList as PKColumn>${PKColumn.columnName},</#list> ${sqlSelectArgsString} FROM ${dbTable.tableName} 
  	<include refid="where_Clause"/>
  	<include refid="orderBy_Clause"/>
  	<include refid="limit_Clause"/>
  </select>
  
   <!--复杂查询：不分页-->	
  <select id="findList" parameterType="${basePackage}.domain.searchvo.${dbTable.entityName?cap_first}SearchVo" resultMap="${dbTable.entityName}Map">
  	SELECT <#list dbTable.PKColumnList as PKColumn>${PKColumn.columnName},</#list> ${sqlSelectArgsString} FROM ${dbTable.tableName} 
  	<include refid="where_Clause"/>
  	<include refid="orderBy_Clause"/>
  </select>
  
  <!--查询满足指定条件的记录数-->
  <select id="selectCountByCondition" parameterType="${basePackage}.domain.searchvo.${dbTable.entityName?cap_first}SearchVo" resultType="java.lang.Long">
  	SELECT count(1) FROM ${dbTable.tableName} 
  	<include refid="where_Clause"/>
  </select>
  
  <#--附加功能-->
  <#list dbTable.tableFunctions as tableFunction>
  	<#--登陆功能-->
  	<#if tableFunction.functionName == "Login_Function">
  	<!--根据登陆账号和密码进行查询-->
   <select id="selectBy${tableFunction.loginAccountColumn.propertyName?cap_first}And${tableFunction.loginPasswordColumn.propertyName?cap_first}" resultMap="${dbTable.entityName}Map"  parameterType="java.util.HashMap">
	SELECT <#list dbTable.PKColumnList as PKColumn>${PKColumn.columnName},</#list> ${sqlSelectArgsString} 
	FROM ${dbTable.tableName}
	WHERE ${tableFunction.loginAccountColumn.columnName} = ${r"#{"}${tableFunction.loginAccountColumn.propertyName}} AND ${tableFunction.loginPasswordColumn.columnName} = ${r"#{"}${tableFunction.loginPasswordColumn.propertyName}}
   </select>
   </#if>
  </#list>
  
  <!--where子句-->
  <sql id="where_Clause" >
	 <where>
	 	<#--迭代主键列-->
	 	<#list dbTable.PKColumnList as PKColumn>
		<if test="${PKColumn.propertyName} != null">
		 AND ${PKColumn.columnName}=${r"#{"}${PKColumn.propertyName},javaType=${PKColumn.javaType},jdbcType=${PKColumn.jdbcType}}
		</if>
		<if test="${PKColumn.propertyName}s != null">
		 AND ${PKColumn.columnName} in  
        <foreach collection="array" item = "${PKColumn.propertyName}" open="(" separator="," close=")">${r"#{"}${PKColumn.propertyName}}</foreach>
        </if>  
		</#list>
		<#--迭代普通列-->
		<#list dbTable.tableColumns as tableColumn>
		<#if tableColumn.javaType == 'java.lang.String'><#--如果是字符串类型，进行模糊查询-->
		<if test="${tableColumn.propertyName} != null">
		AND ${tableColumn.columnName} LIKE "%"${r"#{"}${tableColumn.propertyName},javaType=${tableColumn.javaType},jdbcType=${tableColumn.jdbcType}}"%"
		</if>
		<#--如果是日期类型或者数字类型，根据开始、等于、结束查询-->
		<#elseif tableColumn.javaType == 'java.util.Date'||tableColumn.javaSimpleName=="Integer"||tableColumn.javaSimpleName=="Long"||tableColumn.javaSimpleName=="Float"||tableColumn.javaSimpleName=="Double"||tableColumn.javaSimpleName=="BigDecimal">
		<if test="gt${tableColumn.propertyName?cap_first} != null">
		AND ${tableColumn.columnName}  <![CDATA[ >= ]]> ${r"#{"}gt${tableColumn.propertyName?cap_first},javaType=${tableColumn.javaType},jdbcType=${tableColumn.jdbcType}}
		</if>
		<if test="eq${tableColumn.propertyName?cap_first} != null">
		AND ${tableColumn.columnName} <![CDATA[ = ]]> ${r"#{"}eq${tableColumn.propertyName?cap_first},javaType=${tableColumn.javaType},jdbcType=${tableColumn.jdbcType}}
		</if>
		<if test="lt${tableColumn.propertyName?cap_first} != null">
		AND ${tableColumn.columnName} <![CDATA[ <= ]]> ${r"#{"}lt${tableColumn.propertyName?cap_first},javaType=${tableColumn.javaType},jdbcType=${tableColumn.jdbcType}}
		</if>
		<#else>
		<if test="${tableColumn.propertyName} != null">
		AND ${tableColumn.columnName}=${r"#{"}${tableColumn.propertyName},javaType=${tableColumn.javaType},jdbcType=${tableColumn.jdbcType}}
		</if>
		</#if>
		</#list>
		</where>
  </sql>
  
  <!--order by子句-->
  <sql id="orderBy_Clause">
  	<trim prefix="ORDER BY">
		<if test="sortName !=null">
		<#list dbTable.PKColumnList as PKColumn>
		<if test="sortName == '${PKColumn.propertyName}'">${PKColumn.columnName}</if>
		</#list>
		<#list dbTable.tableColumns as tableColumn>
		<if test="sortName == '${tableColumn.propertyName}'">${tableColumn.columnName}</if>
		</#list>
		</if>
		<if test="sortDirection !=null">
			<if test="sortDirection == 'desc'">DESC</if>
			<if test="sortDirection == 'asc'">ASC</if>
		</if>
	</trim>
  </sql>
  
  <!--limit字句-->
  <sql id="limit_Clause">
  	 <if test="startIndex !=null and pageSize!=null">
  	 	limit ${r"#{"}startIndex},${r"#{"}pageSize}
  	 </if>
  </sql>
</mapper>
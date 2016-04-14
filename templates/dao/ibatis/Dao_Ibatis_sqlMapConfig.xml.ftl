<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE sqlMapConfig      
    PUBLIC "-//ibatis.apache.org//DTD SQL Map Config 2.0//EN"      
    "http://ibatis.apache.org/dtd/sql-map-config-2.dtd"> 
<sqlMapConfig>
<!--
cacheModelsEnabled: is start the cache of the SqlMapClient's policy
enhancementEnabled optimize geter/seter ,upgrade the system
lazyLoadingEnabled is start lazy policy
-->
 
	<settings cacheModelsEnabled="true" 
		enhancementEnabled="true" 
		lazyLoadingEnabled="true" 
		errorTracingEnabled="true" 
		maxRequests="32" 
		maxSessions="10" 
		maxTransactions="5" 
		useStatementNamespaces="true" />	 
	<typeHandler jdbcType="CLOB" javaType="java.lang.String" callback="org.springframework.orm.ibatis.support.ClobStringTypeHandler"/>   
	<#list dbTables as dbTable><#if dbTable.generateCode>
	<typeAlias type="${basePackage}.domain.${dbTable.entityName?cap_first}" alias="${dbTable.entityName?cap_first}"/>
	</#if></#list>
	<#list dbTables as dbTable><#if dbTable.generateCode>
	<sqlMap resource="mapper/${dbTable.entityName?cap_first}Mapper.xml" />
	</#if></#list>
</sqlMapConfig>

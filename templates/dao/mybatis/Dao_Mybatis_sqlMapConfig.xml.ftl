<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<typeAliases >
		<#list dbTables as dbTable><#if dbTable.generateCode>
		<typeAlias type="${basePackage}.domain.${dbTable.entityName?cap_first}" alias="${dbTable.entityName?cap_first}"/>
		</#if></#list>
	</typeAliases>
	<mappers>
		<#list dbTables as dbTable><#if dbTable.generateCode>
		<mapper resource="mapper/${dbTable.entityName?cap_first}Mapper.xml" />
		<#--判断该实体是否需要生成报表-->
		<#if dbTable.tableFunctions??>
			<#list dbTable.tableFunctions as tableFunction>
				<#if tableFunction.functionName=="Report_Function">
		<mapper resource="mapper/report/${dbTable.entityName?cap_first}ReportMapper.xml" />			
				<#break>
				</#if>
			</#list>
		</#if>
		</#if></#list>
	</mappers>
</configuration>


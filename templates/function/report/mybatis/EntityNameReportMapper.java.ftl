<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--对应数据库表${dbTable.tableName}-->
<mapper  namespace="${basePackage}.domain.${dbTable.entityName?cap_first}" >
  <!--查询结果集-->
  <resultMap id="${dbTable.entityName}ReportMap" type="${basePackage}.domain.report.BaseReportVo" >
  </resultMap>
    <!--查询结果集-->
  <resultMap id="${dbTable.entityName}TimeReportMap" type="${basePackage}.domain.report.TimeReportVo" >
  </resultMap>
  <#--附加功能-->
  <#list dbTable.tableFunctions as tableFunction>
  	<#--报表功能-->
  	<#if tableFunction.functionName == "Report_Function">
  	<#if tableFunction.chartType.reportType == "pie">
  	<select id="getPieReportData" parameterType="${basePackage}.domain.report.ReportSearchVo" resultMap="${dbTable.entityName}ReportMap">
  	<#else>
  	<select id="getXYReportData" parameterType="${basePackage}.domain.report.TimeReportSearchVo" resultMap="${dbTable.entityName}TimeReportMap">
    </#if>
  	SELECT
  	<#--如果横轴是时间轴-->
  	<#if tableFunction.xaxis?? && tableFunction.xaxis.javaSimpleName == "Date">DATE_FORMAT(a.${tableFunction.xaxis.columnName},'%Y%m%d') days,</#if>
  	<#--如果分类-->
  	<#if tableFunction.categorizedColumn??>
  		<#--如果分类字段是外键-->
  		<#if tableFunction.categorizedColumn.isForeignKey>
  		<#--自定义函数getRelateTable获取关联表-->
  		(SELECT c.name FROM ${getRelateTable(dbTable.tableName,tableFunction.categorizedColumn.columnName)} c WHERE c.id=a.${tableFunction.categorizedColumn.columnName})
  		<#else>
  			${tableFunction.categorizedColumn.columnName}
  		</#if>
  		as categoryName,
  	</#if>
  	<#--是否是统计记录-->
  	<#if tableFunction.isCountRecord>
  		COUNT(1) counts
  		<#else>
  		SUM(${tableFunction.countValueColumn.columnName}) counts
  	</#if>
  	FROM dbTable.tableName a
  	<#--如果横轴是时间轴-->
  	<#if tableFunction.xaxis?? && tableFunction.xaxis.javaSimpleName == "Date">
  	WHERE ${tableFunction.xaxis.columnName} <![CDATA[ >= ]]> ${r"${"}startTime}  AND ${tableFunction.xaxis.columnName} <![CDATA[ <= ]]> ${r"${"}endTime}
  	</#if>
  	<#--如果分类-->
  	<#if tableFunction.categorizedColumn??>
  	GROUP BY ${tableFunction.categorizedColumn.columnName}
  	<#--如果横轴为日期-->
  	<#if tableFunction.xaxis?? && tableFunction.xaxis.javaSimpleName == "Date">,days</#if> 
  	</#if>
  	<#--如果横轴为日期-->
  	<#if tableFunction.xaxis?? && tableFunction.xaxis.javaSimpleName == "Date">
  	 ORDER BY days;
  	</#if>
  </select>
  	</#if>
  </#list>
</mapper>
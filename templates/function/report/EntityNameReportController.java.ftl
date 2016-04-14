package ${basePackage}.web.controller.report;
import  ${basePackage}.base.*;
import  ${basePackage}.util.*;
import  ${basePackage}.domain.searchvo.*;
import  ${basePackage}.service.report.*;
import  ${basePackage}.domain.report.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


@Controller
@RequestMapping("/${dbTable.entityName}/report")
public class ${dbTable.entityName?cap_first}ReportController extends BaseController{
	
	@Autowired
	private I${dbTable.entityName?cap_first}ReportService ${dbTable.entityName}ReportService;
	
	@RequestMapping("/reportUI")
	public String reportUI(){
		return "${dbTable.entityName}/report";
	}
	
	<#list dbTable.tableFunctions as tableFunction>
	<#if tableFunction.functionName="Report_Function">
	<#if tableFunction.chartType.reportType="pie">
	<#if tableFunction.isCountRecord>
	@RequestMapping(value="/recordnums/pieChart",produces = "application/json; charset=UTF-8")
	<#else>
	@RequestMapping(value="/${tableFunction.countValueColumn.propertyName}/pieChart",produces = "application/json; charset=UTF-8")
	</#if>
	@ResponseBody
	public String getPieChart(ReportSearchVo reportSearchVo){
		List<BaseReportVo> baseReportVos=${dbTable.entityName}ReportService.getBaseReportVos(reportSearchVo);
		String title="${dbTable.entityName?cap_first}统计";
		return ChartUtil.getPieChartJson(title,baseReportVos);
	}
	</#if>
	<#if tableFunction.chartType.reportType="column">
	<#if tableFunction.isCountRecord>
	@RequestMapping(value="/recordnums/singleColumnChart",produces = "application/json; charset=UTF-8")
	<#else>
	@RequestMapping(value="/${tableFunction.countValueColumn.propertyName}/singleColumnChart",produces = "application/json; charset=UTF-8")
	</#if>
	@ResponseBody
	public String getSingleColumnChart(ReportSearchVo reportSearchVo){
		
		return JsonUtil.toJson(data);
	}
	</#if>
	<#if tableFunction.chartType.reportType="multi_column">
	<#if tableFunction.isCountRecord>
	@RequestMapping(value="/recordnums/multiColumnChart",produces = "application/json; charset=UTF-8")
	<#else>
	@RequestMapping(value="/${tableFunction.countValueColumn.propertyName}/multiColumnChart",produces = "application/json; charset=UTF-8")
	</#if>
	@ResponseBody
	public String getMultiColumnChart(ReportSearchVo reportSearchVo){
	
	}
	</#if>
	<#if tableFunction.chartType.reportType="stack_column">
	<#if tableFunction.isCountRecord>
	@RequestMapping(value="/recordnums/stackColumnChart",produces = "application/json; charset=UTF-8")
	<#else>
	@RequestMapping(value="/${tableFunction.countValueColumn.propertyName}/stackColumnChart",produces = "application/json; charset=UTF-8")
	</#if>
	@ResponseBody
	public String getStackColumnChart(ReportSearchVo reportSearchVo){
	
	}
	</#if>
	<#if tableFunction.chartType.reportType="line">
	<#if tableFunction.isCountRecord>
	@RequestMapping(value="/recordnums/singleLineChart",produces = "application/json; charset=UTF-8")
	<#else>
	@RequestMapping(value="/${tableFunction.countValueColumn.propertyName}/singleLineChart",produces = "application/json; charset=UTF-8")
	</#if>
	@ResponseBody
	public String getSingleLineChart(ReportSearchVo reportSearchVo){
		
		return JsonUtil.toJson(data);
	}
	</#if>
	<#if tableFunction.chartType.reportType="multi_line">
	<#if tableFunction.isCountRecord>
	@RequestMapping(value="/recordnums/multiLineChart",produces = "application/json; charset=UTF-8")
	<#else>
	@RequestMapping(value="/${tableFunction.countValueColumn.propertyName}/multiLineChart",produces = "application/json; charset=UTF-8")
	</#if>
	@ResponseBody
	public String getMultiLineChart(ReportSearchVo reportSearchVo){
		
		return JsonUtil.toJson(data);
	}
	</#if>
	</#if>
	</#list>
}
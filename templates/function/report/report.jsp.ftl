<#assign reportFucntionNums=0>
<#list dbTable.tableFunctions as tableFunction>
	<#if tableFunction.functionName == "Report_Function">
		<#assign reportFucntionNums=reportFucntionNums+1>
	</#if>
</#list>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="<%=path%>/js/assets/plugins/font-awesome/css/font-awesome.min.css"  rel="stylesheet" type="text/css"/>
<link href="<%=path%>/js/assets/plugins/bootstrap/css/bootstrap.min.css"  rel="stylesheet" type="text/css"/>
<link href="<%=path%>/js/assets/plugins/uniform/css/uniform.default.css"  rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<#list dbTable.tableColumns as tableColumn>
<#if tableColumn.javaType=="java.util.Date">
<link rel="stylesheet" type="text/css" href="<%=path%>/js/assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
<#break>
</#if>
</#list>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="<%=path%>/js/assets/css/style-metronic.css"  rel="stylesheet" type="text/css"/>
<link href="<%=path%>/js/assets/css/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/js/assets/css/style-responsive.css"  rel="stylesheet" type="text/css"/>
<link href="<%=path%>/js/assets/css/plugins.css"  rel="stylesheet" type="text/css"/>
<link href="<%=path%>/js/assets/css/themes/default.css"  rel="stylesheet" type="text/css" id="style_color"/>
<link href="<%=path%>/js/assets/css/custom.css"  type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
<!-- BEGIN HEADER -->
<%@include file="/WEB-INF/jsp/public/header.jsp" %>
<!-- END HEADER -->
<div class="clearfix"> </div>
<!-- BEGIN CONTAINER -->
<div class="page-container"> 
  <%@include file="/WEB-INF/jsp/public/sidebar.jsp" %>
  <!-- BEGIN CONTENT -->
  <div class="page-content-wrapper">
    <div class="page-content"> 
      <!-- BEGIN PAGE CONTENT-->
     <div class="row">
     			<#--如果只有一个报表，报表占全部的宽度，同时在保险的下方用表格显示数据-->
     			<#if reportFucntionNums==1>
				<div class="col-md-12">
					<!-- BEGIN VALIDATION STATES-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>${dbTable.entityName}
							</div>
						</div>
						<div class="portlet-body">
							<div id="chart-1" style="height:400px"></div>
						</div>
					</div>
					<!-- END VALIDATION STATES-->
				</div>
				</#if>
				<#--如果不止一个报表，且报表的数量是2的倍数，每个报表占一半的宽度-->
				<#assign current=1>
				<#if (reportFucntionNums>1) && (reportFucntionNums%2)==0>
				<#list dbTable.tableFunctions as tableFunction>
				<#if tableFunction.functionName=="Report_Function">
				<div class="col-md-6">
					<!-- BEGIN VALIDATION STATES-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>${dbTable.entityName}
							</div>
						</div>
						<div class="portlet-body">
							<div id="chart-${current}" style="height:400px"></div>
						</div>
					</div>
					<!-- END VALIDATION STATES-->
				</div>
				<#assign current=current+1>
				</#if>
				</#list>
				</#if>
				<#--如果不止一个报表，且报表的数量不是2的倍数，前面每对报表各占一半大小空间，最后一个占全部-->
				<#if (reportFucntionNums>1) && (reportFucntionNums%2!=0)>
				<#assign current=1>
				<#list dbTable.tableFunctions as tableFunction>
				<#if tableFunction.functionName=="Report_Function">
				<#if current!=reportFucntionNums>
				<div class="col-md-6">
				<#else>
				<div class="col-md-12">
				</#if>
					<!-- BEGIN VALIDATION STATES-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>${dbTable.entityName}
							</div>
						</div>
						<div class="portlet-body">
							<div id="chart-${current}" style="height:400px"></div>
						</div>
					</div>
					<!-- END VALIDATION STATES-->
				</div>
				</#if>
				<#assign current=current+1>
				</#list>
				</#if>
			</div>
      <!-- END PAGE CONTENT--> 
    </div>
  </div>
  <!-- END CONTENT --> 
</div>
<!-- END CONTAINER --> 
<!-- BEGIN FOOTER -->
<div class="footer">
  <div class="footer-inner"> 2014 &copy; Metronic by keenthemes. </div>
  <div class="footer-tools"> <span class="go-top"> <i class="fa fa-angle-up"></i> </span> </div>
</div>
<!-- END FOOTER --> 
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) --> 
<!-- BEGIN CORE PLUGINS --> 
<!--[if lt IE 9]>
    <script src="<%=path%>/js/assets/plugins/respond.min.js"></script>
    <script src="<%=path%>/js/assets/plugins/excanvas.min.js"></script> 
    <![endif]--> 
<script src="<%=path%>/js/assets/plugins/jquery-1.10.2.min.js"  type="text/javascript"></script> 
<script src="<%=path%>/js/assets/plugins/jquery-migrate-1.2.1.min.js"  type="text/javascript"></script> 
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip --> 
<script src="<%=path%>/js/assets/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"  type="text/javascript"></script> 
<script src="<%=path%>/js/assets/plugins/bootstrap/js/bootstrap.min.js"  type="text/javascript"></script> 
<script src="<%=path%>/js/assets/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script> 
<script src="<%=path%>/js/assets/plugins/jquery.cokie.min.js" type="text/javascript"></script> 
<script src="<%=path%>/js/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script> 
<!-- END CORE PLUGINS --> 
<!-- BEGIN PAGE LEVEL PLUGINS --> 
<#list dbTable.tableColumns as tableColumn>
<#if tableColumn.javaType=="java.util.Date">
<script type="text/javascript" src="<%=path%>/js/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" ></script> 
<#break>
</#if>
</#list>
<!-- END PAGE LEVEL PLUGINS --> 
<script type="text/javascript" src="<%=path%>/js/assets/plugins/highcharts/highcharts.js"></script>
<script type="text/javascript" src="<%=path%>/js/assets/plugins/highcharts/modules/exporting.js"></script>
<!-- BEGIN PAGE LEVEL SCRIPTS --> 
<script src="<%=path%>/js/assets/scripts/core/app.js" ></script> 
<script src="<%=path%>/js/assets/scripts/core/components-pickers.js" ></script> 
<!-- END PAGE LEVEL SCRIPTS --> 
  <script>
    $(function () {
    	App.init();
         ComponentsPickers.init();
         <#assign current=1>
         <#list dbTable.tableFunctions as tableFunction>
         <#if tableFunction.functionName=="Report_Function">
         <#--1、组装报表的请求路径-->
		 <#if tableFunction.isCountRecord>
		 <#assign subPath="recordnums/">
		 <#else>
		  <#assign subPath="${tableFunction.countValueColumn.propertyName}/">
		 </#if>
         
         <#if tableFunction.chartType.reportType=="pie"><#assign subPath=subPath+"pieChart"></#if>
         <#if tableFunction.chartType.reportType=="column"><#assign subPath=subPath+"singleColumnChart"></#if>
         <#if tableFunction.chartType.reportType=="multi_column"><#assign subPath=subPath+"multiColumnChart"></#if>
         <#if tableFunction.chartType.reportType=="stack_column"><#assign subPath=subPath+"stackColumnChart"></#if>
         <#if tableFunction.chartType.reportType=="line"><#assign subPath=subPath+"singleLineChart"></#if>
         <#if tableFunction.chartType.reportType=="multi_line"><#assign subPath=subPath+"multiLineChart"></#if>
    	
    	<#--设置报表的请求路径-->
    	 $.getJSON('<%=path%>/${dbTable.entityName}/report/${subPath}.action', function (data) {
    	 	  $('#chart-${current}').highcharts(data);
    	 });
    	 <#assign current=current+1>
    	 </#if>
    	 </#list>
    });
  </script>
<!-- END JAVASCRIPTS --> 
</body>
<!-- END BODY -->
</html>
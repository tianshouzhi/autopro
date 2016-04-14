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
				<div class="col-md-12">
					<!-- BEGIN VALIDATION STATES-->
					<div class="portlet box green">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-reorder"></i>添加${dbTable.entityName}
							</div>
						</div>
						<div class="portlet-body form">
							<!-- BEGIN FORM-->
							<form action="<%=path%>/${dbTable.entityName}/${r"${"}${dbTable.entityName}==null?'add':'edit'}.action" id="form_${dbTable.entityName}" class="form-horizontal" method="post">
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										表单填写错误，请根据提示进行修改
									</div>
									<div class="alert alert-success display-hide">
										<button class="close" data-close="alert"></button>
										表单校验成功,正在提交!
									</div>
									<#list dbTable.tableColumns as tableColumn>
									<div class="form-group">
										<label class="control-label col-md-3">${tableColumn.columnComment}
										<#if tableColumn.notNull><span class="required">*</span></#if><#--判断是否为空-->
										</label>
										<div class="col-md-4">
										<#if tableColumn.javaType=="java.util.Date"><#--判断是否是日期类型-->
											<#--同时有"日期 时间"<#if tableColumn.jdbcType=="DATE">
											<#else>
											<div class="input-group datetime datetime-picker" data-date-format="yyyy-mm-dd hh:ii:ss">
											</#if>
											--><#--判断是否只有"日期"-->
											<div class="input-group date date-picker" data-date-format="yyyy-mm-dd">
					                          <input type="text" class="form-control" readonly name="${tableColumn.propertyName}" placeholder="选择日期" value="${r"${"}${dbTable.entityName}.${tableColumn.propertyName}}">
					                          <span class="input-group-btn">
					                          <button class="btn btn-sm default" type="button"><i class="fa fa-calendar"></i></button>
					                          </span>
					                        </div>
					                    <#elseif tableColumn.javaType=="java.lang.Boolean"><#--判断是否是布尔值-->
					                        <div class="radio-list">
												<label>
												<input type="radio" name="${tableColumn.propertyName}"  value="true" ${r"${"}${dbTable.entityName}.${tableColumn.propertyName}?'checked':""} > True</label>
												<label>
												<input type="radio" name="${tableColumn.propertyName}"  value="false" ${r"${"}${dbTable.entityName}.${tableColumn.propertyName}?'checked':""}> False </label>
											</div>
										<#elseif tableColumn.javaType=="java.lang.String"><#--判断是否是字符串-->
												 <#if tableColumn.jdbcType=="LONGVARCHAR"><#--大文本-->
												 	<#--判断是否使用富文本编辑器-->
												 	<#if useHtmlEditor(dbTable.tableName,tableColumn.columnName)>
												 	<script id="htmleditor_${tableColumn.propertyName}" name="${tableColumn.propertyName}" type="text/plain"></script>
												 	<#else><#--不使用富文本编辑器-->
													<textarea class="form-control" rows="4" name="${tableColumn.propertyName}" >${r"${"}${dbTable.entityName}.${tableColumn.propertyName}}</textarea>
													</#if>
												<#else><#--普通字符串-->
												<input  class="form-control" type="text" name="${tableColumn.propertyName}" value="${r"${"}${dbTable.entityName}.${tableColumn.propertyName}}"/>
												</#if>
										<#else><#--其他类型的字段-->
											<input  class="form-control" type="text" name="${tableColumn.propertyName}" value="${r"${"}${dbTable.entityName}.${tableColumn.propertyName}}"/>
										</#if>
										</div>
									</div>
									</#list>
								</div>
								<div class="form-actions fluid">
									<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn green">提交</button>
										<button type="button" class="btn default">取消</button>
									</div>
								</div>
							</form>
							<!-- END FORM-->
						</div>
					</div>
					<!-- END VALIDATION STATES-->
				</div>
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
<#if useHtmlEditor(dbTable.tableName)>
<!-- Ueditor配置文件 -->
<script type="text/javascript" src="<%=path%>/js/assets/plugins/ueditor/ueditor.config.js"></script>
<!-- Ueditor编辑器源码文件 -->
<script type="text/javascript" src="<%=path%>/js/assets/plugins/ueditor/ueditor.all.min.js"></script>	
</#if>
<script type="text/javascript" src="<%=path%>/js/assets/plugins/jquery-validation/dist/jquery.validate.min.js"></script> 
<script type="text/javascript" src="<%=path%>/js/assets/plugins/jquery-validation/dist/additional-methods.min.js"></script> 
<script type="text/javascript" src="<%=path%>/js/assets/plugins/jquery-validation/dist/zh_cn.js"></script> 
<!-- END PAGE LEVEL PLUGINS --> 
<!-- BEGIN PAGE LEVEL SCRIPTS --> 
<script src="<%=path%>/js/assets/scripts/core/app.js" ></script> 
<script src="<%=path%>/js/assets/scripts/core/components-pickers.js" ></script> 
<!-- END PAGE LEVEL SCRIPTS --> 

<script>
        jQuery(document).ready(function() {    
           App.init();
           ComponentsPickers.init();
           
           <#list dbTable.tableColumns as tableColumn>
           <#if tableColumn.jdbcType == "LONGVARCHAR">
            var ue_${tableColumn.propertyName} = UE.getEditor('htmleditor_${tableColumn.propertyName}');
            </#if>
            </#list>
			var form1 = $('#form_${dbTable.entityName}');
            var error1 = $('.alert-danger', form1);
            var success1 = $('.alert-success', form1);

            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-block', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                    <#list dbTable.tableColumns as tableColumn>
                    ${tableColumn.propertyName}: {
                    	<#if tableColumn.jdbcType=="FLOAT" || tableColumn.jdbcType=="DOUBLE" || tableColumn.jdbcType=="NUMERIC" || tableColumn.jdbcType=="DECIMAL" || tableColumn.jdbcType=="REAL"><#--判断字段值是否为数字-->
                    	number: true,
                    	</#if>
                    	<#if tableColumn.jdbcType=="TINYINT" || tableColumn.jdbcType=="SMALLINT" || tableColumn.jdbcType=="INTEGER" || tableColumn.jdbcType=="BIGINT"><#--判断字段值是否为整数-->
                    	digits: true,
                    	</#if>
                    	<#if tableColumn.jdbcType=="DATE"><#--判断字段值是否为日期-->
                    	date: true,
                    	</#if>
                    	<#--判断字段值是否为日期<#if tableColumn.jdbcType=="DATETIME" || tableColumn.jdbcType=="TIMESTAMP" >
                    	datetime: true,
                    	</#if>-->
                    	<#if tableColumn.maxLength??><#--判断字段的最大长度-->
                    	maxlength: ${tableColumn.maxLength},
                    	</#if>
                    	<#if tableColumn.notNull><#--判断字段值是否为空-->
                    	 required: true
                    	 <#else>
                    	 required: false
                    	</#if>
                    } <#if tableColumn_has_next>,</#if>
                    </#list>
                   
                },

                invalidHandler: function (event, validator) { //display error alert on form submit              
                    success1.hide();
                    error1.show();
                    App.scrollTo(error1, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change done by hightlight
                    $(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
                },

                success: function (label) {
                    label.closest('.form-group').removeClass('has-error'); // set success class to the control group
                },

                submitHandler: function (form) {
                    success1.show();
                    error1.hide();
                    form.submit();
                }
            });
        });
    </script> 
<!-- END JAVASCRIPTS --> 
</body>
<!-- END BODY -->
</html>
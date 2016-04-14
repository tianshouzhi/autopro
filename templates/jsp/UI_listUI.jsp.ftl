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
<link rel="stylesheet" href="<%=path%>/js/assets/plugins/data-tables/DT_bootstrap.css" />
<#list dbTable.tableColumns as tableColumn>
<#if tableColumn.javaType=="java.util.Date">
<link rel="stylesheet" type="text/css" href="<%=path%>/js/assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />
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
          <!-- Begin: life time stats -->
          <div class="portlet">
            <div class="portlet-title">
              <div class="caption"> <i class="fa fa-list"></i>${dbTable.entityName}管理</div>
              <div class="tools"><a class="expand" href="javascript:;"></a></div>
            </div>
            <div class="portlet-body">
            	<#--搜索条件开始-->
                <div class="portlet search_form" style="display:none">
		            <div class="portlet-body">
		            	<div class="row">
		            	<!-- BEGIN search-box-->
									<#list dbTable.tableColumns as tableColumn>
									<div class="form-group col-md-4">
										<#if tableColumn.javaType=="java.util.Date"><#--判断是否是日期类型-->
					                        <#--为了布局好看，日期类型什么也不做，在最后处理-->
					                    <#elseif tableColumn.javaType=="java.lang.Boolean"><#--判断是否是布尔值-->
					                       <label class="control-label col-md-4">${tableColumn.columnComment}</label>
					                        <div class="radio-list col-md-8">
					                        	<select name="${tableColumn.propertyName}" class="form-filter form-control">
					                        		<option value="">全部</option>
					                        		<option value="true">是</option>
					                        		<option value="false">否</option>
					                        	</select>
											</div>
										<#elseif tableColumn.javaType=="java.lang.String"><#--判断是否是字符串-->
												<label class="control-label col-md-4">${tableColumn.columnComment}</label>
												<div class="col-md-8">
												<input  class="form-control" type="text" class="form-filter" name="${tableColumn.propertyName}"/>
												</div>
										<#else><#--其他类型的字段-->
										<label class="control-label col-md-4">${tableColumn.columnComment}</label>
										<div class="col-md-8">
											<input  class="form-control" type="text" class="form-filter" name="${tableColumn.propertyName}"/>
											</div>
										</#if>
									</div>
									</#list>
								<#list dbTable.tableColumns as tableColumn>
										<#if tableColumn.javaType=="java.util.Date"><#--判断是否是日期类型-->
										<div class="form-group col-md-6">
										<label class="control-label col-md-3">${tableColumn.columnComment}</label>
										<div class="input-group input-large date-picker input-daterange col-md-9"  data-date-format="yyyy-mm-dd">
												<input type="text" class="form-control" class="form-filter" name="gt${tableColumn.propertyName?cap_first}">
												<span class="input-group-addon">to</span>
												<input type="text" class="form-control" class="form-filter" name="lt${tableColumn.propertyName?cap_first}">
											</div>
											</div>
										</#if>
								</#list>
								<div class="col-md-4">
		                            <button class="btn btn-sm yellow filter-submit margin-bottom"><i class="fa fa-search"></i> 搜索</button>
		                        	<button class="btn btn-sm red filter-cancel"><i class="fa fa-times"></i> 重置</button>
		                        </div>
							<!-- END search-box-->
							</div>
		            </div>
             	</div>
             	<#--搜索条件结束-->
              <div class="table-container">
                <div class="table-actions-wrapper"> <span> </span>
                  <div class="actions"> 
                  <a href="${r"${"}pageContext.request.contextPath}/${dbTable.entityName}/addUI.action" class="btn default yellow-stripe"> <i class="fa fa-plus"></i> <span class="hidden-480">新建</span> </a>
                  <a href="${r"${"}pageContext.request.contextPath}/${dbTable.entityName}/batchDelete.action" class="btn default batch-delete yellow-stripe"> <i class="fa fa-trash-o"></i> <span class="hidden-480">批量删除</span> </a>
                <div class="btn-group"> <a class="btn default yellow-stripe" href="#" data-toggle="dropdown"> <i class="fa fa-wrench"></i> <span class="hidden-480">其他</span> <i class="fa fa-angle-down"></i> </a>
                  <ul class="dropdown-menu pull-right">
                  	<#if exportExcel>
                    <li> <a href="<%=path%>/${dbTable.entityName}/export_excel.action">导出  Excel</a> </li>
                    <li> <a href="#">导入 Excel</a> </li>
                    </#if>
                    <li class="divider"> </li>
                    <li> <a href="#">打印</a> </li>
                  </ul>
                </div>
              </div>
                </div>
                <table class="table table-striped table-bordered table-hover" id="datatable_ajax">
                  <thead>
                    <tr role="row" class="heading">
                      <th><input type="checkbox" class="group-checkable"></th>
					  <#list dbTable.tableColumns as tableColumn>
					  <th>${tableColumn.columnComment}</th>
					  </#list>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <!-- End: life time stats-->  
        </div>
      </div>
      <!-- END PAGE CONTENT--> 
    </div>
  </div>
  <!-- END CONTENT --> 
</div>
<!-- END CONTAINER -->
<!--删除确认提示框开始-->
	<div id="delete_confirm_modal" class="modal fade" tabindex="-1" data-backdrop="delete_confirm_modal" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
					<h4 class="modal-title">删除确认</h4>
				</div>
				<div class="modal-body">
					<p>
						  你确定要删除选中的记录吗？
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn default delete_N">取消</button>
					<button type="button" data-dismiss="modal" class="btn green delete_Y">确定</button>
				</div>
			</div>
		</div>
	</div> 
<!--删除确认提示框结束-->
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
<script type="text/javascript" src="<%=path%>/js/assets/plugins/data-tables/jquery.dataTables.fix.js" ></script> 
<script type="text/javascript" src="<%=path%>/js/assets/plugins/data-tables/DT_bootstrap.js" ></script> 
<#list dbTable.tableColumns as tableColumn>
<#if tableColumn.javaType=="java.util.Date">
<script type="text/javascript" src="<%=path%>/js/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" ></script>
<script type="text/javascript" src="<%=path%>/js/assets/plugins/bootstrap-daterangepicker/moment.min.js" > </script>
<script type="text/javascript" src="<%=path%>/js/assets/plugins/bootstrap-daterangepicker/daterangepicker.js" > </script>
<script src="<%=path%>/js/assets/scripts/core/components-pickers.js" ></script> 
<#break>
</#if>
</#list>
<!-- END PAGE LEVEL PLUGINS --> 
<!-- BEGIN PAGE LEVEL SCRIPTS --> 
<script src="<%=path%>/js/assets/scripts/core/app.js" ></script> 
<script src="<%=path%>/js/assets/scripts/core/datatable.js" ></script> 
<!-- END PAGE LEVEL SCRIPTS --> 

<script>
var TableAjax = function () {
    var handleRecords = function() {
        var grid = new Datatable();
            grid.init({
                src: $("#datatable_ajax"),
                onSuccess: function(grid) {
                    // execute some code after table records loaded
                },
                onError: function(grid) {
                    // execute some code on network or other general error  
                },
                dataTable: {  // here you can define a typical datatable settings from http://datatables.net/usage/options 
                    //'sAjaxDataProp': 'aData',
                    'aoColumns': [
                    			<#--首列复选框-->
                    			<#list dbTable.PKColumnList as PKColumn>
                                 { 'mDataProp': '${PKColumn.propertyName}', "mRender": function ( data, type, row ) {
                                     return "<input type='checkbox' name='ids' class='checkboxes' value='"+data+"'/>";
                                 }},
                                 </#list>
                                 <#--数据库表对应的字段列：如果字段是字符串类型切长度大于255，只显示前20个字符-->
                                  <#list dbTable.tableColumns as tableColumn>
                                  <#if (tableColumn.javaType=="java.lang.String") && (tableColumn.maxLength>255)>
                                  { 'mDataProp': '${tableColumn.propertyName}','bVisible':true,'bSortable': true,"mRender":function ( data, type, row ) {
      									if(data!=null&&data.length>255){
      										data=data.substring(0,20)+"...";
      									}
      									return data;
      								}},
                                  <#else>
                                  { 'mDataProp': '${tableColumn.propertyName}','bVisible':true,'bSortable': true},
                                  </#if>
                                  </#list>
                                   <#--操作列-->
                                  <#list dbTable.PKColumnList as PKColumn>
      							{ 'mDataProp': '${PKColumn.propertyName}', 'bSortable': false,
      								"mRender":function ( data, type, row ) {
      									return '<a class="btn default btn-xs green-stripe" href="#">查看</a>'
      									+' <a class="btn default btn-xs purple" href="${r"${"}pageContext.request.contextPath}/${dbTable.entityName}/editUI.action?<#list dbTable.PKColumnList as PKColumn>${PKColumn.propertyName}='+data+'</#list>"><i class="fa fa-edit"></i> 修改</a>'
      									+' <a class="btn default btn-xs black delete-confirm" href="${r"${"}pageContext.request.contextPath}/${dbTable.entityName}/delete.action?<#list dbTable.PKColumnList as PKColumn>${PKColumn.propertyName}='+data+'</#list>"><i class="fa fa-trash-o"></i> 删除</a>';
      								}
      							}
      							 </#list>
      						],
                    "iDisplayLength": 10, // default record count per page
                    "bServerSide": true, // server side processing
                    //如果不想左右滑动，请打开此注释
                    //"sScrollY": "100%",
                    "sAjaxSource": "search.action", // ajax source
                    //"aaSorting": [[ 1, "asc" ]], // set first column as a default sort by asc
               		 fnServerParams:function ( aoData ) {//自定义请求参数
               		 //demo
                     //aoData.push({"name":"requestname","value":"requestvalue"});
                    //迭代搜索框请求参数
               		 	$(".search_form input,.search_form select,.search_form textarea").each(function(){
               		   		if($(this).val()!=null&&$(this).val()!=undefined&&$(this).val().trim()!=""){
               		   			aoData.push({"name":$(this).attr("name"),"value":$(this).val()});
               		   		}
               		 	  }); 
               		 
                    }
                },
              
            });
    }
    return {
        //main function to initiate the module
        init: function () {
            handleRecords();
        }
    };
}();
        jQuery(document).ready(function() {    
           App.init();
           TableAjax.init();
<#list dbTable.tableColumns as tableColumn>
<#if tableColumn.javaType=="java.util.Date">
           ComponentsPickers.init();
           <#break>
</#if>
</#list>
        });
    </script> 
<!-- END JAVASCRIPTS -->
 
</body>
<!-- END BODY -->
</html>
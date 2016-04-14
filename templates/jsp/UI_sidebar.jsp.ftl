<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- BEGIN SIDEBAR -->
  <div class="page-sidebar-wrapper">
    <div class="page-sidebar navbar-collapse collapse"> 
      <!-- BEGIN SIDEBAR MENU -->
      <ul class="page-sidebar-menu" data-auto-scroll="true" data-slide-speed="200">
        <li class="sidebar-toggler-wrapper"> 
          <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
          <div class="sidebar-toggler hidden-phone"> </div>
          <!-- BEGIN SIDEBAR TOGGLER BUTTON --> 
        </li>
        <li class="sidebar-search-wrapper"> 
          <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
          <form class="sidebar-search" action="" method="POST">
            <div class="form-container">
              <div class="input-box"> <a href="javascript:;" class="remove"> </a>
                <input type="text" placeholder="Search..."/>
                <input type="button" class="submit" value=" "/>
              </div>
            </div>
          </form>
          <!-- END RESPONSIVE QUICK SEARCH FORM --> 
        </li>
        <#--首页-->
        <li class="start "> <a href="index.html"> <i class="fa fa-home"></i> <span class="title"> 首页 </span> </a> </li>
       	<#--crud-->
        <li class="active "> <a href="javascript:;"> <i class="fa fa-th"></i> <span class="title"> 菜单列表</span> <span class="selected"> </span> <span class="arrow open"> </span> </a>
          <ul class="sub-menu">
          <#list dbTables as dbTable><#if dbTable.generateCode>
            <li> <a href="${r"${"}pageContext.request.contextPath}/${dbTable.entityName}/list.action" >${dbTable.entityName}管理</a> </li>
            </#if>
          </#list>
          </ul>
        </li>
        <#--报表-->
        <li class="last "> <a href="charts.html"> <i class="fa fa-bar-chart-o"></i> <span class="title"> Visual Charts </span> </a> 
        <ul class="sub-menu">
          <#list dbTables as dbTable><#if dbTable.generateCode>
          <#assign reportFunctionNums=0>
          	<#list dbTable.tableFunctions as tableFunction>
          		<#if tableFunction.functionName=="Report_Function">
          			<#assign reportFunctionNums=reportFunctionNums+1>
          		</#if>
          	</#list>
          	<#if reportFunctionNums!=0>
            <li> <a href="${r"${"}pageContext.request.contextPath}/${dbTable.entityName}/report/reportUI.action" >${dbTable.entityName}统计报表</a> </li>
            </#if>
            </#if>
          </#list>
          </ul>
        </li>
      </ul>
      <!-- END SIDEBAR MENU --> 
    </div>
  </div>
  <!-- END SIDEBAR --> 
package ${package};

import ${basePackage}.base.*;
import ${basePackage}.domain.*;
<#list dbTable.tableFunctions as tableFunction>
			<#if tableFunction.functionName=="Login_Function">
				<#assign username=tableFunction.loginAccountColumn.propertyName>
				<#assign password=tableFunction.loginPasswordColumn.propertyName>
				<#break>
			</#if>
</#list>
/**
 *@Description ${dbTable.entityName?cap_first}实体业务层操作接口
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
public interface I${dbTable.entityName?cap_first}Service extends IBaseService<${dbTable.entityName?cap_first}>{
	<#list dbTable.tableFunctions as tableFunction>
		<#--登陆功能-->
		<#if tableFunction.functionName=="Login_Function">
		/**根据用户名和密码进行查询*/
		public ${dbTable.entityName?cap_first} findBy${username?cap_first}And${password?cap_first}(String ${username},String ${password});
		</#if>
	</#list>
}

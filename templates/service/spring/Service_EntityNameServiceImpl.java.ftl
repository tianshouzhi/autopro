package ${package};
<#list dbTable.tableFunctions as tableFunction>
			<#if tableFunction.functionName=="Login_Function">
				<#assign username=tableFunction.loginAccountColumn.propertyName>
				<#assign password=tableFunction.loginPasswordColumn.propertyName>
				<#break>
			</#if>
</#list>
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ${basePackage}.dao.*;
import ${basePackage}.domain.*;
import ${basePackage}.base.*;
import ${basePackage}.service.*;

/**
 *@Description ${dbTable.entityName?cap_first}实体业务层操作实现类
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
@SuppressWarnings("restriction")
@Service
public class ${dbTable.entityName?cap_first}ServiceImpl extends BaseServiceImpl<${dbTable.entityName?cap_first}> implements I${dbTable.entityName?cap_first}Service{

	@Resource
	private I${dbTable.entityName?cap_first}Dao ${dbTable.entityName}Dao;
	
	@Override
	@PostConstruct
	protected void initDaoInstance() {
		this.baseDao=${dbTable.entityName}Dao;
	}
	
	<#list dbTable.tableFunctions as tableFunction>
		<#--登陆功能-->
		<#if tableFunction.functionName=="Login_Function">
	/**根据用户名和密码进行查询*/
	public ${dbTable.entityName?cap_first} findBy${username?cap_first}And${password?cap_first}(String ${username},String ${password}){
		return ${dbTable.entityName}Dao.findBy${username?cap_first}And${password?cap_first}(${username},${password});
	}
		</#if>
	</#list>
}
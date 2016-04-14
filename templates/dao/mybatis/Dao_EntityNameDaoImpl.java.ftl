package ${package};

<#list dbTable.tableFunctions as tableFunction>
	<#if tableFunction.functionName=="Login_Function">
		<#assign username=tableFunction.loginAccountColumn.propertyName>
		<#assign password=tableFunction.loginPasswordColumn.propertyName>
import java.util.*;
		<#break>
	</#if>
</#list>

import org.springframework.stereotype.Repository;
import ${basePackage}.base.*;
import ${basePackage}.domain.*;
import ${basePackage}.dao.*;
/**
 *@Description ${dbTable.entityName?cap_first}实体数据库操作接口Mybatis实现
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
@Repository
public class ${dbTable.entityName?cap_first}DaoImpl extends BaseDaoMybatisImpl<${dbTable.entityName?cap_first}> implements I${dbTable.entityName?cap_first}Dao {
	<#list dbTable.tableFunctions as tableFunction>
		<#if tableFunction.functionName=="Login_Function">
		/**根据用户名和密码进行查询*/
		public ${dbTable.entityName?cap_first} findBy${username?cap_first}And${password?cap_first}(String ${username},String ${password}){
			Map<String,String> params=new HashMap<String,String>();
			params.put("${username}",${username});
			params.put("${password}",${password});
			return  this.getSqlSession().selectOne(clazz.getName()+".selectBy${tableFunction.loginAccountColumn.propertyName?cap_first}And${tableFunction.loginPasswordColumn.propertyName?cap_first}", params);
		}
		</#if>
	</#list>	
}
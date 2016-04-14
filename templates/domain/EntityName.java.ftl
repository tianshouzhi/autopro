package ${package};

<#--判断需要导入的包-->
<#list dbTable.tableColumns as tableColumn>
<#if tableColumn.javaType="java.util.Date">
import java.util.Date;
<#break>
</#if>
</#list>
<#--有关联关系就导入-->
<#assign imported=0>
<#list dbTable.oneToManyList as tableRelation>
import java.util.Set;
<#assign imported=1>
<#break>
</#list>
<#if imported == 0>
<#list dbTable.manyToManyList as tableRelation>
import java.util.Set;
<#break>
</#list>
</#if>
import org.hibernate.validator.constraints.*;
import java.io.Serializable;

/**
 *@Description ${dbTable.tableComment}
 *@Table 对应的数据库表: ${dbTable.tableName}
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
public class ${dbTable.entityName?cap_first} implements Serializable{
	
	private static final long serialVersionUID = 1L;
	<#--迭代主键列-->
	<#list dbTable.PKColumnList as PKColumn>
	/**${PKColumn.columnComment},对应的数据库列:${PKColumn.columnName}*/
	private ${PKColumn.javaSimpleName} ${PKColumn.propertyName};
	</#list>
	
	<#--迭代普通列-->
	<#list dbTable.tableColumns as tableColumn>
	<#list tableColumn.columnAnnotations as validateAnnotation>
	${validateAnnotation}
	</#list>
	/**${tableColumn.columnComment},对应的数据库列:${tableColumn.columnName}*/
	private ${tableColumn.javaSimpleName} ${tableColumn.propertyName};
	
	</#list>
	<#--判断是够自关联-->
	<#if dbTable.isSelfRelation>
	/**父${dbTable.entityName?cap_first}*/
	private ${dbTable.entityName?cap_first} parent;
	
	/**子${dbTable.entityName?cap_first}*/
	private Set<${dbTable.entityName?cap_first}> children;	
	</#if>
	
	<#--迭代一对多-->
	<#list dbTable.oneToManyList as relationTable>
	/**一对多关联${relationTable.entityName?cap_first}*/
	private Set<${relationTable.entityName?cap_first}> ${relationTable.entityName}s; 
	
	</#list>
	<#--迭代多对一-->
	<#list dbTable.manyToOneList as relationTable>
	/**多对一关联${relationTable.entityName?cap_first}*/
	private ${relationTable.entityName?cap_first} ${relationTable.entityName}; 
	</#list>
	<#--迭代多对多-->
	<#list dbTable.manyToManyList as relationTable>
	/**多对多关联${relationTable.entityName?cap_first}*/
	private Set<${relationTable.entityName?cap_first}> ${relationTable.entityName}s; 
	</#list>
	
	<#--生成构造方法-->
	public ${dbTable.entityName?cap_first}(){}
	<#--主键不自动增长的情况下,构造方法中加入主键-->
	public ${dbTable.entityName?cap_first}(${constructArgsString}){
		<#list dbTable.PKColumnList as PKColumn>
		<#if !PKColumn.isAutoIncrement>
		this.${PKColumn.propertyName}=${PKColumn.propertyName};
		</#if>
		</#list>
		<#list dbTable.tableColumns as tableColumn>
		this.${tableColumn.propertyName}=${tableColumn.propertyName};
		</#list>
	}
	
	<#--生成getter、setter-->
	<#list dbTable.PKColumnList as PKColumn>
	public void set${PKColumn.propertyName?cap_first}(${PKColumn.javaSimpleName} ${PKColumn.propertyName}){
		this.${PKColumn.propertyName}=${PKColumn.propertyName};
	}
	
	public ${PKColumn.javaSimpleName} get${PKColumn.propertyName?cap_first}(){
		return this.${PKColumn.propertyName};
	}
	</#list>
	<#list dbTable.tableColumns as tableColumn>
	public void set${tableColumn.propertyName?cap_first}(${tableColumn.javaSimpleName} ${tableColumn.propertyName}){
		this.${tableColumn.propertyName}=${tableColumn.propertyName};
	}
	
	public ${tableColumn.javaSimpleName} get${tableColumn.propertyName?cap_first}(){
		return this.${tableColumn.propertyName};
	}
	</#list>
	<#if dbTable.isSelfRelation>
	public void setParent(${dbTable.entityName?cap_first} parent){
		this.parent=parent;
	}
	
	public ${dbTable.entityName?cap_first} getParent(){
		return this.parent;
	}
	
	public void setChildren(Set<${dbTable.entityName?cap_first}> children){
		this.children=children;
	}
	public Set<${dbTable.entityName?cap_first}> getChildren(){
		return this.children;
	}
	</#if>
	<#list dbTable.oneToManyList as relationTable>
	public void set${relationTable.entityName?cap_first}s(Set<${relationTable.entityName?cap_first}> ${relationTable.entityName}s){
		this.${relationTable.entityName}s=${relationTable.entityName}s;
	}
	
	public Set<${relationTable.entityName?cap_first}> get${relationTable.entityName?cap_first}s(){
		return this.${relationTable.entityName}s;
	}
	</#list>
	<#list dbTable.manyToOneList as relationTable>
	public void set${relationTable.entityName?cap_first}(${relationTable.entityName?cap_first} ${relationTable.entityName}){
		this.${relationTable.entityName}=${relationTable.entityName};
	}
	
	public ${relationTable.entityName?cap_first} get${relationTable.entityName?cap_first}(){
		return this.${relationTable.entityName};
	}
	</#list>
	<#list dbTable.manyToManyList as relationTable>
	public void set${relationTable.entityName?cap_first}s(Set<${relationTable.entityName?cap_first}> ${relationTable.entityName}s){
		this.${relationTable.entityName}s=${relationTable.entityName}s;
	}
	
	public Set<${relationTable.entityName?cap_first}> get${relationTable.entityName?cap_first}s(){
		return this.${relationTable.entityName}s;
	}
	</#list>
}	
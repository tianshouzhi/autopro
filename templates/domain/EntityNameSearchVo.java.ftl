package ${package};

<#--判断需要导入的包-->
<#list dbTable.tableColumns as tableColumn>
<#if tableColumn.javaType="java.util.Date">
import java.util.Date;
<#break>
</#if>
</#list>
import ${basePackage}.base.BaseSearchVo;
/**
 *@Description ${dbTable.entityName?cap_first}搜索VO
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
public class ${dbTable.entityName?cap_first}SearchVo extends BaseSearchVo{
	
	private static final long serialVersionUID = 1L;
	<#--迭代主键列-->
	<#list dbTable.PKColumnList as PKColumn>
	/**主键*/
	private ${PKColumn.javaSimpleName} ${PKColumn.propertyName};
	/**主键${PKColumn.propertyName}集合*/
	private ${PKColumn.javaSimpleName}[] ${PKColumn.propertyName}s;
	</#list>
	<#--迭代普通列-->
	<#list dbTable.tableColumns as tableColumn>
	<#--如果是当前字段是日期或者数字类型-->
	<#if tableColumn.javaType=="java.util.Date" || tableColumn.javaSimpleName=="Integer"||tableColumn.javaSimpleName=="Long"||tableColumn.javaSimpleName=="Float"||tableColumn.javaSimpleName=="Double"||tableColumn.javaSimpleName=="BigDecimal">
	/**大于${tableColumn.propertyName}*/
	private ${tableColumn.javaSimpleName} gt${tableColumn.propertyName?cap_first};
	/**等于${tableColumn.propertyName}*/
	private ${tableColumn.javaSimpleName} eq${tableColumn.propertyName?cap_first};
	/**小于${tableColumn.propertyName}*/
	private ${tableColumn.javaSimpleName} lt${tableColumn.propertyName?cap_first};
	<#--其他列-->
	<#else>
	private ${tableColumn.javaSimpleName} ${tableColumn.propertyName};
	</#if>
	</#list>
	<#--迭代外键列-->
	<#list dbTable.foreinkeyList as foreinkeyColumn>
	/**外键${foreinkeyColumn.columnName}*/
	private ${foreinkeyColumn.javaSimpleName} ${foreinkeyColumn.columnName};
	</#list>
	
	<#--===========================getters and setters==================================-->
	<#--迭代主键列-->
	<#list dbTable.PKColumnList as PKColumn>
	public void set${PKColumn.propertyName?cap_first}(${PKColumn.javaSimpleName} ${PKColumn.propertyName}){
		this.${PKColumn.propertyName}=${PKColumn.propertyName};
	}
	public ${PKColumn.javaSimpleName} get${PKColumn.propertyName?cap_first}(){
		return this.${PKColumn.propertyName};
	}
	public void set${PKColumn.propertyName?cap_first}s(${PKColumn.javaSimpleName}[] ${PKColumn.propertyName}s){
		this.${PKColumn.propertyName}s=${PKColumn.propertyName}s;
	}
	public ${PKColumn.javaSimpleName}[] get${PKColumn.propertyName?cap_first}s(){
		return this.${PKColumn.propertyName}s;
	}
	</#list>
	<#--迭代普通列-->
	<#list dbTable.tableColumns as tableColumn>
	<#--如果是当前字段是日期或者数字类型-->
	<#if tableColumn.javaType=="java.util.Date"||tableColumn.javaSimpleName=="Integer"||tableColumn.javaSimpleName=="Long"||tableColumn.javaSimpleName=="Float"||tableColumn.javaSimpleName=="Double"||tableColumn.javaSimpleName=="BigDecimal">
	public void setGt${tableColumn.propertyName?cap_first}(${tableColumn.javaSimpleName} gt${tableColumn.propertyName?cap_first}){
		this.gt${tableColumn.propertyName?cap_first}=gt${tableColumn.propertyName?cap_first};
	}
	public ${tableColumn.javaSimpleName} getGt${tableColumn.propertyName?cap_first}(){
		return this.gt${tableColumn.propertyName?cap_first};
	}
	public void setEq${tableColumn.propertyName?cap_first}(${tableColumn.javaSimpleName} eq${tableColumn.propertyName?cap_first}){
		this.eq${tableColumn.propertyName?cap_first}=eq${tableColumn.propertyName?cap_first};
	}
	public ${tableColumn.javaSimpleName} getEq${tableColumn.propertyName?cap_first}(){
		return this.eq${tableColumn.propertyName?cap_first};
	}
	public void setLt${tableColumn.propertyName?cap_first}(${tableColumn.javaSimpleName} lt${tableColumn.propertyName?cap_first}){
		this.lt${tableColumn.propertyName?cap_first}=lt${tableColumn.propertyName?cap_first};
	}
	public ${tableColumn.javaSimpleName} getLt${tableColumn.propertyName?cap_first}(){
		return this.lt${tableColumn.propertyName?cap_first};
	}
	<#--其他列-->
	<#else>
	public void set${tableColumn.propertyName?cap_first}(${tableColumn.javaSimpleName} ${tableColumn.propertyName}){
		this.${tableColumn.propertyName}=${tableColumn.propertyName};
	}
	public ${tableColumn.javaSimpleName} get${tableColumn.propertyName?cap_first}(){
		return this.${tableColumn.propertyName};
	}
	</#if>
	</#list>
	<#--迭代外键列-->
	<#list dbTable.foreinkeyList as foreinkeyColumn>
	public void set${foreinkeyColumn.columnName?cap_first}(${foreinkeyColumn.javaSimpleName} ${foreinkeyColumn.columnName}){
		this.${foreinkeyColumn.columnName}=${foreinkeyColumn.columnName};
	}
	public ${foreinkeyColumn.javaSimpleName} get${foreinkeyColumn.columnName?cap_first}(){
		return this.${foreinkeyColumn.columnName};
	}
	</#list>
	
}
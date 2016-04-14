package ${package};

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ${basePackage}.base.*;
import ${basePackage}.domain.*;
import ${basePackage}.domain.searchvo.*;
import ${basePackage}.service.*;
import ${basePackage}.util.*;

/**
 *@Description ${dbTable.entityName?cap_first}实体web层操作接口,基于SpringMvc
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
@Controller
@RequestMapping("/${dbTable.entityName}")
public class ${dbTable.entityName?cap_first}Controller extends BaseController {

	@Autowired
	private I${dbTable.entityName?cap_first}Service ${dbTable.entityName}Service;

	/**
	 * 跳转到添加${dbTable.entityName}添加页面
	 * @return
	 */
	@RequestMapping("/addUI")
	public String addUI() {
		return "${dbTable.entityName}/saveUI";
	}
	
	/**
	 * 添加${dbTable.entityName}
	 * @return
	 */
	@RequestMapping("/add")
	public String add(${dbTable.entityName?cap_first} entity) {

		${dbTable.entityName}Service.save(entity);

		return "redirect:list.action";
	}
	
	/**
	 * 跳转到${dbTable.entityName}修改界面
	 * @return
	 */
	@RequestMapping("/editUI")
	public String editUI(<#list dbTable.PKColumnList as PKColumn>${PKColumn.javaSimpleName} ${PKColumn.propertyName}</#list>) {
		// 准备回显数据
		${dbTable.entityName?cap_first} ${dbTable.entityName} = ${dbTable.entityName}Service.findById(<#list dbTable.PKColumnList as PKColumn>${PKColumn.propertyName}</#list>);
		request.setAttribute("${dbTable.entityName}", ${dbTable.entityName});
		return "${dbTable.entityName}/saveUI";
	}

	/**
	 * 修改${dbTable.entityName}
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(${dbTable.entityName?cap_first} ${dbTable.entityName}) {
		${dbTable.entityName}Service.update( ${dbTable.entityName});
		return "success";
	}
	
	/**
	 * 删除${dbTable.entityName}
	 * @return
	 */
	@RequestMapping(value="delete",produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String delete(<#list dbTable.PKColumnList as PKColumn>${PKColumn.javaSimpleName} ${PKColumn.propertyName}</#list>) {
		String result="error";
		if(<#list dbTable.PKColumnList as PKColumn>${PKColumn.propertyName}</#list>!=null){
			${dbTable.entityName}Service.delete(<#list dbTable.PKColumnList as PKColumn>${PKColumn.propertyName}</#list>);
			result="success";
		}
		return JsonUtil.toJson(result);
	}
	
	/**
	 * 批量删除${dbTable.entityName}
	 *@param <#list dbTable.PKColumnList as PKColumn>${PKColumn.propertyName}s</#list> 主键集合 
	 * @return
	 */
	@RequestMapping(value="/batchDelete",produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String batchDelete(@RequestParam(value="<#list dbTable.PKColumnList as PKColumn>${PKColumn.propertyName}s</#list>")<#list dbTable.PKColumnList as PKColumn>${PKColumn.javaSimpleName}[] ${PKColumn.propertyName}s</#list>) {
		String result="error";
		if(<#list dbTable.PKColumnList as PKColumn>${PKColumn.propertyName}s</#list>!=null&&<#list dbTable.PKColumnList as PKColumn>${PKColumn.propertyName}s</#list>.length>0){
			${dbTable.entityName}Service.batchDelete(<#list dbTable.PKColumnList as PKColumn>${PKColumn.propertyName}s</#list>);
			result="success";
		}
		return JsonUtil.toJson(result);
	}
	
	/**
	 * 跳转到${dbTable.entityName}列表页面
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return "${dbTable.entityName}/listUI";
	}
	
	/**
	 * 搜索${dbTable.entityName}
	 * @return 返回json格式的数据
	 */
	@RequestMapping(value="/search",produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String search(${dbTable.entityName?cap_first}SearchVo ${dbTable.entityName}SearchVo) {
    	PageBean<${dbTable.entityName?cap_first}> pageBean=${dbTable.entityName}Service.getPageBean(${dbTable.entityName}SearchVo);
        return JsonUtil.toJson(pageBean);
	}
	<#if exportExcel>
	/**
	 * 导出Excel，将满足查询条件的所有数据都导出
	 */
	@RequestMapping(value="/export_excel")
	@ResponseBody
	public String export(${dbTable.entityName?cap_first}SearchVo ${dbTable.entityName}SearchVo) {
    	String workBookName="${dbTable.entityName}.xls";
		List<${dbTable.entityName?cap_first}> datas=${dbTable.entityName}Service.findList(${dbTable.entityName}SearchVo);
		new ApachePOIExcelUtil().createWorkBook(workBookName)
    	.addSheet(getHeader_propertyName_pairs(), datas)
    	.wrtieToBrowser(request, response);
        return null;
	}
	
	/**
	 * 返回导出的Excel的表头和实体属性名的对应关系
	 * key值为属性名，value为对应的在Excel中显示的表头
	 * @return
	 */
	private Map<String,String> getHeader_propertyName_pairs(){
		Map<String,String> map=new HashMap<String, String>();
		<#list dbTable.PKColumnList as pkColumn>
		map.put("${pkColumn.propertyName}","${pkColumn.columnComment}");
		</#list>
		<#list dbTable.tableColumns as tableColumn>
		map.put("${tableColumn.propertyName}","${tableColumn.columnComment}");
		</#list>
		return map;
	}
	</#if>
}

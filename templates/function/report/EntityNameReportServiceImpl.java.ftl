package ${basePackage}.service.report.impl;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basePackage}.base.report.BaseReportServiceImpl;
import ${basePackage}.dao.report.IArticleReportDao;
import ${basePackage}.domain.Article;
import ${basePackage}.service.report.IArticleReportService;
/**
 *@Description ${dbTable.entityName?cap_first}Report实体报表业务层操作实现类
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
 
@Service
@SuppressWarnings("restriction")
public class ${dbTable.entityName?cap_first}ReportServiceImpl extends BaseReportServiceImpl<${dbTable.entityName?cap_first}> implements I${dbTable.entityName?cap_first}ReportService{

	@Autowired
	private I${dbTable.entityName?cap_first}ReportDao ${dbTable.entityName}ReportDao;
	
	@Override
	@PostConstruct
	protected void initReportDaoInstance() {
		this.baseReportDao=${dbTable.entityName}ReportDao;
	}
}
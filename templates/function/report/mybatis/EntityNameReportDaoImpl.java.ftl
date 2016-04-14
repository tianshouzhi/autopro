package ${basePackage}.dao.report.impl;

import org.springframework.stereotype.Repository;
import ${basePackage}.base.report.*;
import ${basePackage}.domain.Article;
import ${basePackage}.dao.report.*;
/**
 *@Description ${dbTable.entityName?cap_first}实体数据库操作接口Mybatis实现
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
@Repository
public class ${dbTable.entityName?cap_first}ReportDaoImpl extends BaseReportDaoMybatisImpl<${dbTable.entityName?cap_first}> implements I${dbTable.entityName?cap_first}ReportDao {

}
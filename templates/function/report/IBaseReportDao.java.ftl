
package ${basePackage}.base.report;

import java.util.List;

import ${basePackage}.domain.report.*;

public interface IBaseReportDao<T>{
	
	public List<BaseReportVo> getBaseReportVos(ReportSearchVo reportSearchVo);
	
	public List<TimeReportVo> getTimeReportVos(ReportSearchVo reportSearchVo);

}
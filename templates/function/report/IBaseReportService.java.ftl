package ${basePackage}.base.report;
import java.util.List;

import ${basePackage}.domain.report.*;

public interface IBaseReportService<T>{
	public List<BaseReportVo> getBaseReportVos(ReportSearchVo searchvo);
	public List<TimeReportVo> getTimeReportVos(ReportSearchVo searchvo);
}
package ${basePackage}.domain.report;

import java.util.Date;

/**
 * 按时间段统计数据VO
 */
public class TimeReportVo extends BaseReportVo{

	/**
	 * 统计时间
	 */
	private Date statisticsTime;

	public Date getStatisticsTime() {
		return statisticsTime;
	}
	public void setStatisticsTime(Date statisticsTime) {
		this.statisticsTime = statisticsTime;
	}
	
	public TimeReportVo(Long counts, Date statisticsTime, String categoryName) {
		super();
		this.counts = counts;
		this.statisticsTime = statisticsTime;
		this.categoryName = categoryName;
	}
	public TimeReportVo() {
		super();
	}
	
}

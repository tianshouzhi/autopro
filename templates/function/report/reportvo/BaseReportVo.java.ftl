package ${basePackage}.domain.report;

/**
 * 统计数据基类
 */
public class BaseReportVo {
	/**
	 * 统计时间段内的数量
	 */
	protected Long counts;
	/***
	 * 类别名
	 */
	protected String categoryName;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public BaseReportVo() {
		super();
	}
	public Long getCounts() {
		return counts;
	}
	public void setCounts(Long counts) {
		this.counts = counts;
	}
	public BaseReportVo( String categoryName,Long counts) {
		super();
		this.counts = counts;
		this.categoryName = categoryName;
	}
}
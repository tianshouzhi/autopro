package ${basePackage}.domain.report;
import java.util.*;

public class ReportSearchVo{
	private Date startTime;
	private Date endTime;
	private String reportTimeType;
	
	public void setStartTime(Date startTime){
		this.startTime=startTime;
	}
	
	public Date getStartTime(){
		return this.startTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}
	
	public Date getEndTime(){
		return this.endTime;
	}
	
	public void setReportTimeType(String reportTimeType){
		this.reportTimeType=reportTimeType;
	}
	
	public String getReportTimeType(){
		return this.reportTimeType;
	}
}
package ${basePackage}.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.autopro.highcharts.charts.PieChart;
import com.autopro.highcharts.charts.XYChart;
import com.autopro.highcharts.common.ChartItem;
import com.autopro.highcharts.common.ChartMetaInfo;
import com.autopro.highcharts.common.Title;
import com.autopro.highcharts.common.XAxis;
import com.autopro.highcharts.common.YAxis;
import com.autopro.highcharts.datamodel.DataModel;
import com.autopro.highcharts.datamodel.PieChartDataModel;
import com.autopro.highcharts.datamodel.YDataModel;
import ${basePackage}.domain.report.*;
import ${basePackage}.util.DateUtil;
import ${basePackage}.util.JsonUtil;

/**
 * 在分类统计的情况下，如果类别数过多，尽量不要显示所有类别，否则生成的报表会很乱
 * @author tianshouzhi
 *
 */
public class ChartUtil{
	
	public final static String REPORT_DAY_DATA="day";
	public final static String REPORT_WEEK_DATA="week";
	public final static String REPORT_MONTH_DATA="month";
	public final static String REPORT_QUARTER_DATA="quarter";
	public final static String REPORT_YEAR_DATA="year";
	
	/**
	 * 获取折线图，对统计数据进行累加，横轴为时间，纵轴单位为数字，会对数据进行累加
	 * @param title 图标的标题
	 * @param yAxisTitle 纵轴的标题
	 * @param statisticsVos 统计数据
	 * @param stratTime 统计开始时间
	 * @param endTime 统计结束时间
	 * @param reportTimeType 统计时间类型：日、周、月、年
	 * @return
	 */
	public static  String getLineChartJson(String title,
			String yAxisTitle,
			List<TimeReportVo> statisticsVos,
			Date stratTime,
			Date endTime,String reportTimeType){
		
		return getXYChartDayViewJson(ChartMetaInfo.LINE,title, 
				null, yAxisTitle,statisticsVos, 
				stratTime,endTime,true,reportTimeType);
	}
	
	/**
	 * 获取柱状图 ，统计每个时间段内数据，不累加，或者统计某个类别的数量有多少，类别为横轴
	 * @param title
	 * @param subtitle
	 * @param yAxisTitle
	 * @param statisticsVos
	 * @param stratTime
	 * @param endTime
	 * @param accumulate
	 * @param reportTimeType
	 * @return
	 */
	public static String getColumnChartJson(String title,
			String yAxisTitle,
			List<TimeReportVo> statisticsVos,
			Date stratTime,
			Date endTime,String reportTimeType){
		return getXYChartDayViewJson(ChartMetaInfo.COLUMN,title, 
				null, yAxisTitle,statisticsVos, 
				stratTime,endTime,true,reportTimeType);
	}
	
	/***
	 * 获取波浪图日视图对象、以日期作为横轴日期坐标
	 * @return
	 */
	public static String getSplineChartJson(String title,
			String subtitle,
			String yAxisTitle,
			List<TimeReportVo> statisticsVos,
			Date stratTime,
			Date endTime,
			boolean accumulate,String reportTimeType){
		return getXYChartDayViewJson(ChartMetaInfo.SPLINE,title, 
				subtitle, yAxisTitle,statisticsVos, 
				stratTime,endTime,accumulate,reportTimeType);
	}
	
	/***
	 * 获取柱状图日视图对象、以日期作为横轴日期坐标
	 * @return
	 */
	/*public static Chart getBarChartDayView(String title,String subtitle,String yAxisTitle,List<StatisticsVo> statisticsVos,Date stratTime,Date endTime,boolean accumulate){
		return getXYChartDayView(ChartMetaInfo.BAR, title, subtitle, yAxisTitle, statisticsVos, stratTime, endTime, accumulate);
	}*/
	
	public static String getPieChartJson(String title, List<BaseReportVo> statisticsVos) {
//		String subtitle=DateUtil.format(date, DateUtil.PATTERN_YYYY_MM_DD)+"至"+DateUtil.format(date2, DateUtil.PATTERN_YYYY_MM_DD);
		List<ChartItem<DataModel>> chartItems=new ArrayList<ChartItem<DataModel>>();
		ChartItem<DataModel> chartItem=new ChartItem<DataModel>();
		List<DataModel> dataModel=new ArrayList<DataModel>();
		for (BaseReportVo baseStatisticsVo : statisticsVos) {
			dataModel.add(new PieChartDataModel(baseStatisticsVo.getCategoryName(), baseStatisticsVo.getCounts()));
		}
		chartItem.setData(dataModel);
		chartItems.add(chartItem);
		PieChart pieChart = new PieChart(new Title(title), chartItems);
		Map<String,String> replacedPropertyNameMapping=new HashMap<String, String>();
		replacedPropertyNameMapping.put("chartType", "pie");
		return JsonUtil.toJson(pieChart, false,replacedPropertyNameMapping);
	}
	
	/***
	 * @param chartType 从ChartMetaInfo的常量中获取
	 * @param title 主标题
	 * @param subtitle 次标题
	 * @param yAxisTitle Y轴标题
	 * @param statisticsVos 统计数据
	 * @param stratTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	private static String getXYChartDayViewJson(String chartType,String title,String subtitle,String yAxisTitle,List<TimeReportVo> statisticsVos,Date stratTime,Date endTime,boolean accumulate,String reportTimeType){
		
		if(stratTime==null||endTime==null){
			throw new RuntimeException("没有指定报表的开始或结束日期");
		}

		if(statisticsVos!=null && !statisticsVos.isEmpty()){
			/***
			 *得到统计的类别数量 
			 */
			List<String> categoryNames = getNameCategories(statisticsVos);
			//组装X轴
			XAxis xAxis = null;
			List<ChartItem<DataModel>> chartItems=null;
			if(ChartMetaInfo.BAR.equals(chartType)){//如果是bar，使用categoriNames作为纵坐标
				/*xAxis=new XAxis(getNameCategories(statisticsVos));
				*//***
				 *得到统计的日期范围，如果中间有断开的时间，则当天的数据数据设置为0
				 *//*
				chartItems = getDateChartItems(statisticsVos,stratTime, daysCounts, categoryNames,accumulate);*/
			}else{//其他类型的图表以时间作为横轴
				xAxis=new XAxis(getDateCategories(stratTime, endTime,reportTimeType));
				chartItems = getNameChartItems(statisticsVos,stratTime, endTime, categoryNames,accumulate);
			}
			Map<String,String> replacedPropertyNameMapping=new HashMap<String, String>();
			replacedPropertyNameMapping.put("chartType", "line");
			XYChart xyChart = new XYChart(chartType,new Title(title),new Title(subtitle),xAxis,new YAxis(new Title(yAxisTitle)),chartItems);
			return JsonUtil.toJson(xyChart, false,replacedPropertyNameMapping);
		}else{
			return null;
		}
	}
	

	
	private static List<ChartItem<DataModel>> getNameChartItems(
			List<TimeReportVo> statisticsVos, Date stratTime, Date endTime,
			List<String> categoryNames,boolean accumulate) {
		List<ChartItem<DataModel>> chartItems=new ArrayList<ChartItem<DataModel>>();
		Date currentDate=stratTime;
		int daysCounts = DateUtil.getDaysCountInclude(stratTime, endTime);
	   //迭代获取每一个列别的数据
	     for (String categoryName : categoryNames) {
			 ChartItem<DataModel> chartItem=new ChartItem<DataModel>(categoryName);
			 List<DataModel> data=new ArrayList<DataModel>();
			 
			 for(int i=0;i<daysCounts;i++){
				//迭代当前类别下每一天的数据
				 boolean find=false;//当前天的记录是否存在，主要用于防止统计日期之间不是连续的
				 Long beforeDayCount=0L; 
				 for (TimeReportVo statisticsVo : statisticsVos) {
					 if(categoryName.equals(statisticsVo.getCategoryName())){
						 Date statisticsTime = statisticsVo.getStatisticsTime();
						 if(DateUtil.isSameDay(currentDate,statisticsTime)){
							 data.add(new YDataModel(statisticsVo.getCounts()));
							 find=true;
							 break;
						 }
					 }
					}
				 if(!find){
					 List<DataModel> beforeData = chartItem.getData();
					 if(beforeData!=null&&beforeData.size()>1){
						 data.add((YDataModel) chartItem.getData().get(beforeData.size()-1)); 
					 }else{
						 data.add(new YDataModel(beforeDayCount)); 
					 }
				 }
				 currentDate=DateUtil.getAfterDay(currentDate);
			 }
			 //判断是否需要累加每天的数据
			 if(accumulate){
					data=setAccumulate(data); 
				}
			 chartItem.setData(data);
			 
			 chartItems.add(chartItem); 
			 currentDate=stratTime;
		}
	return chartItems;
	}

	private static List<DataModel> setAccumulate(List<DataModel> data) {
		Number accumulateCount=0L;
		for (int i=0;i< data.size();i++) {
			YDataModel dataModel =(YDataModel) data.get(i);
			if(i==0){
				accumulateCount=dataModel.getY();
			}else{
				accumulateCount=dataModel.getY().longValue()+accumulateCount.longValue();
				data.set(i, new YDataModel(accumulateCount));
			}
			
		}
		return data;
	}

	private static List<String> getDateCategories(Date stratTime, Date endTime,String reportTimeType) {
		List<String> categories=new ArrayList<String>();
		int daysCounts = DateUtil.getDaysCountInclude(stratTime, endTime);
		Date curr=stratTime;
		for(int i=0;i<daysCounts;i++){
			if(reportTimeType.equals(REPORT_DAY_DATA)){
				categories.add(DateUtil.format(curr, DateUtil.PATTERN_YYYY_MM_DD));
				 curr=DateUtil.getAfterDay(curr);
			}
			if(reportTimeType.equals(REPORT_MONTH_DATA)){
				categories.add(DateUtil.format(curr, DateUtil.PATTERN_YYYY_MM));
				 curr=DateUtil.getAfterMonth(curr);
			}
			if(reportTimeType.equals(REPORT_YEAR_DATA)){
				categories.add(DateUtil.format(curr, DateUtil.PATTERN_YYYY));
				curr=DateUtil.getAfterYear(curr);
			}
			 
		 }
		return categories;
	}
	
	private static List<String> getNameCategories(
			List<TimeReportVo> statisticsVos) {
		List<String> categoryNames=new ArrayList<String>();
		for (TimeReportVo statisticsVo : statisticsVos) {
			String categoryName = statisticsVo.getCategoryName();
			if(!categoryNames.contains(categoryName)){
				categoryNames.add(categoryName);
			}
		}
		return categoryNames;
	}
}
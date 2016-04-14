package ${basePackage}.base.report;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ${basePackage}.domain.report.*;

public abstract class BaseReportServiceImpl<T> implements IBaseReportService<T>{
	
	@Autowired
	protected IBaseReportDao<T> baseReportDao;
	
	/**
	 * 将dao实例赋值给BaseDao
	 * 如：
	 * 
	 * <pre>
	 * UserReportDao userDao;
	 * .....
	 * protected  void initReportDaoInstance(){
	 * 		this.baseReportDao=userReportDao;
	 * }
	 * </pre>
	 */
	protected abstract void initReportDaoInstance();

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<BaseReportVo> getBaseReportVos(ReportSearchVo reportSearchVo){
		return baseReportDao.getBaseReportVos(reportSearchVo);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<TimeReportVo> getTimeReportVos(ReportSearchVo reportSearchVo){
		return baseReportDao.getTimeReportVos(reportSearchVo);
	}
}
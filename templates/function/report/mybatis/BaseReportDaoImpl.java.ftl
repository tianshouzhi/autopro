package ${basePackage}.base.report;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import ${basePackage}.domain.report.*;

public abstract class BaseReportDaoMybatisImpl<T> extends SqlSessionDaoSupport implements IBaseReportDao<T>{
	
	@Autowired
	protected SqlSessionFactory sqlSessionFactory;
	
	protected Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseReportDaoMybatisImpl() {
		// 所有声明了泛型的类必然会实现ParameterizedType接口，这是由JDK自动帮我们完成的
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	public List<BaseReportVo> getBaseReportVos(ReportSearchVo searchvo){
		return this.getSqlSession().selectList(clazz.getName()+".getBaseReportVos",searchvo);
	}
	
	public List<TimeReportVo> getTimeReportVos(ReportSearchVo searchvo){
		return this.getSqlSession().selectList(clazz.getName()+".getTimeReportVos",searchvo);
	}
}
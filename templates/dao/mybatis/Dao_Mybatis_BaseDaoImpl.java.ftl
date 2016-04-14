package ${package};
import java.io.*;
import ${basePackage}.util.*;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 *@Description 数据库操作接口Mybatis基础实现
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
@SuppressWarnings({ "unchecked", "restriction" })
public abstract class BaseDaoMybatisImpl<T> extends SqlSessionDaoSupport implements IBaseDao<T>{
	
	@Resource
	protected SqlSessionFactory sqlSessionFactory;
	
	protected Class<T> clazz;
	
	public BaseDaoMybatisImpl() {
		// 所有声明了泛型的类必然会实现ParameterizedType接口，这是由JDK自动帮我们完成的
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	public void save(T entity){
		this.getSqlSession().insert(clazz.getName()+".insert",entity);
	}
	
	public void delete(Serializable id){
		this.getSqlSession().delete(clazz.getName()+".deleteById", id);
	}
	
	public void batchDelete(Serializable[] ids){
		this.getSqlSession().delete(clazz.getName()+".batchDelete", ids);
	}
	
	public void update(T entity){
		this.getSqlSession().update(clazz.getName()+".updateById", entity);
	}
	
	public T findById(Serializable id){
		return  (T)this.getSqlSession().selectOne(clazz.getName()+".selectById", id);
	}
	
	public List<T> findByIds(Serializable[] ids){
		
		return null;
	}
	
	public PageBean<T> getPageBean(BaseSearchVo searchVo){
	    List<T> recordList=this.getSqlSession().selectList(clazz.getName()+".getPageBeanByCondition",searchVo);
		Long count=selectCountByCondition(searchVo);
		return new PageBean<T>(count,count,recordList);
	}
	
	public Long selectCountByCondition(BaseSearchVo searchVo){
		return this.getSqlSession().selectOne(clazz.getName()+".selectCountByCondition",searchVo);
	}
	
	public List<T> findList(BaseSearchVo searchVo){
		return this.getSqlSession().selectList(clazz.getName()+".findList",searchVo);
	}
	
	
	public List<T> findAll(){
		return this.getSqlSession().selectList(clazz.getName()+".findAll");
	}
}
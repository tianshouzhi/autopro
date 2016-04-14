package ${package};

import java.io.Serializable;
import java.util.*;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ${basePackage}.util.PageBean;

@Transactional
/**
 *@Description 业务层基础实现类
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

	protected Class<T> clazz; // 泛型的字节码对象

	protected IBaseDao<T> baseDao;
	
	/**
	 * 将dao实例赋值给BaseDao
	 * 如：
	 * 
	 * <pre>
	 * UserDao userDao;
	 * .....
	 * protected  void initDaoInstance(){
	 * 		this.baseDao=userDao;
	 * }
	 * </pre>
	 */
	protected abstract void initDaoInstance();
	
	public void save(T entity) {
		baseDao.save(entity);
	}

	public void delete(Serializable id) {
		baseDao.delete(id);
	}
	public void batchDelete(Serializable[] ids){
		baseDao.batchDelete(ids);
	}
	public void update(T entity) {
		baseDao.update(entity);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public T findById(Serializable id) {
		return (T)baseDao.findById(id);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<T> findByIds(Serializable[] ids) {
		return baseDao.findByIds(ids);
	}
	
	/*@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<T> findAll() {
		return baseDao.findAll();
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Long getCount() {
		return baseDao.getCount();
	}*/
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<T> findList(BaseSearchVo searchVo){
		return baseDao.findList(searchVo);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public PageBean<T> getPageBean(BaseSearchVo searchVo) {
		return baseDao.getPageBean(searchVo);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Long selectCountByCondition(BaseSearchVo searchVo){
    	return baseDao.selectCountByCondition(searchVo);
    }
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public List<T> findAll(){
		return baseDao.findAll();
	}
}

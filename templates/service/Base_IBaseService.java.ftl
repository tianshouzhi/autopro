package ${package};

import java.util.*;
import ${basePackage}.util.*;
import java.io.*;

/**
 *@Description 业务层操作基础接口
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
public interface IBaseService<T> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 根据主键删除实体
	 * 
	 * @param id
	 */
	void delete(Serializable id);

	/**
	 * 批量删除
	 * 
	 * @param ids 主键集合
	 */
	void batchDelete(Serializable[] ids);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 查询实体，如果id为null，则返回null，并不会抛异常。
	 * 
	 * @param id
	 * @return
	 */
	T findById(Serializable id);

	/**
	 * 查询实体
	 * 
	 * @param ids
	 * @return
	 */
	List<T> findByIds(Serializable[] ids);

	/**
	 * 查询所有
	 * 
	 * @return
	List<T> findAll();
	
	/**
	 * 查询所有满足条件的所有记录，不分页
	 * @return
	*/
	List<T> findList(BaseSearchVo searchVo);
	
	/**
	 * 公共的查询分页信息的方法
	 * 
	 * @param pageNum
	 * @param hqlHelper
	 *            查询条件（HQL语句与参数列表）
	 * @return
	 */
	 
	/**
	 * 查询分页数据
	 * @param params 查询参数
	 * @return
	 */
	PageBean<T> getPageBean(BaseSearchVo searchVo);
	
	/*
	*查询满足指定条件的记录数
	*/
	Long selectCountByCondition(BaseSearchVo searchVo);
	
	/**
	*查找所有记录，如果记录数过多的话，不建议使用此方法
	*/
	List<T> findAll();
}

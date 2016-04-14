package ${package};

import java.util.*;
import ${basePackage}.util.*;
import java.io.*;

/**
 *@Description 数据库操作基础接口
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
public interface IBaseDao<T> {

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
	 * @param id 主键
	 * @return
	 */
	T findById(Serializable id);

	/**
	 * 根据指定主键进行查询
	 * 
	 * @param ids
	 * @return
	 */
	List<T> findByIds(Serializable[] ids);
	
	/**
	*查询满足条件的所有数据，不分页
	*/
	List<T> findList(BaseSearchVo searchVo);

	/**
	 * 查询分页数据
	 * @param searchVo 查询参数
	 * @return
	 */
	PageBean<T> getPageBean(BaseSearchVo searchVo);
	
	/**
	*查询满足指定条件的记录数
	*/
	Long selectCountByCondition(BaseSearchVo searchVo);
	
	/**
	*查找所有记录，如果记录数过多的话，不建议使用此方法
	*/
	List<T> findAll();
}

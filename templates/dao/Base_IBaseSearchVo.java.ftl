package ${package};
import java.io.*;
/**
 *@Description 此接口为标识接口，所有的搜索Vo都要实现此接口
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
public abstract class BaseSearchVo implements Serializable{

	private static final long serialVersionUID = 1L;

	/**排序字段的名称*/
	private String sortName;
	/**排序方式:升序或者降序*/
	private String sortDirection;
	/**开始记录*/
	private Integer startIndex;
	/**页面大小*/
	private Integer pageSize;
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
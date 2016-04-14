package ${package};

import java.util.List;

/**
 *@Description 封装分页数据的对象
 *@Notice 注意该对象是与jquery.dataTables联合使用的，字段的名称建议不要修改,否则生成的JSP将无法显示数据
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
public class PageBean<T> {
    //总记录数
    private Long iTotalRecords;
    
    //符合查询条件的总记录
    private Long iTotalDisplayRecords;

    //查询到的数据集合
    private List<T> aaData;

    public PageBean(){
        super();
    }

    public PageBean(Long iTotalRecords, Long iTotalDisplayRecords, List<T> aaData){
        super();
        this.iTotalRecords = iTotalRecords;
        this.iTotalDisplayRecords = iTotalDisplayRecords;
        this.aaData = aaData;
    }

    public Long getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(Long iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public Long getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(Long iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<T> getAaData() {
        return aaData;
    }

    public void setAaData(List<T> aaData) {
        this.aaData = aaData;
    }

}

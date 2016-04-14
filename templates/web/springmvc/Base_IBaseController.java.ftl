package ${package};

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *@Description BaseController，所有的Controler的父类
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
public class BaseController {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	@ModelAttribute//每次有新的请求的时候，都会执行这个方法
	public void setReqAndRep(HttpServletRequest servletRequest,HttpServletResponse servletResponse){
		this.request=servletRequest;
		this.response=servletResponse;
	}
}
package ${package};

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ${basePackage}.domain.*;
/**
 * 检查权限的拦截器
 * @author tianshouzhi
 * @CreateTime 2014-9-17上午9:07:32
 * @description
 */
public class CheckPrivilegeInteceptor extends HandlerInterceptorAdapter  {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CheckPrivilegeInteceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	
		/*//1、从请求路径中截取出符合权限路径格式的字符串。截取的部分为:Controller的RequestMapping/方法的RequestMapping
		String url=request.getRequestURL().toString();
		int beginIndex=url.indexOf(request.getContextPath())+request.getContextPath().length()+1;
		int endIndex=url.indexOf(".action");
		String actionName=url.substring(beginIndex, endIndex);
		*/
		/*//2、获取到权限列表
		@SuppressWarnings("unchecked")
		List<String> privilegeUrlList=(List<String>) request.getSession().getServletContext().getAttribute("privilegeUrlList");
		
		//3、判断权限列表中是否包含这个权限，如果不包含，说明不需要进行权限拦截，直接放行
		if(!privilegeUrlList.contains(actionName)){
			return true;
		}*/
		
		//4、权限列表中包含这个权限，需要进行拦截
		//4.1 先判断用户没有登录，如果没有，跳转到登录页面
		if(request.getSession().getAttribute("user")==null){
			//没有登录的情况下，判断是否正要去登录或者是后去登陆验证码，如果是，放行
			String requestURI = request.getRequestURI();
			if(requestURI.equalsIgnoreCase(request.getContextPath()+"/loginController/login.action")
					||requestURI.contains(request.getContextPath()+"/loginController/securityCode.action")){
				return true;
			}
			request.setAttribute("message", "您还没登录，请先登陆！");
			request.getRequestDispatcher("/WEB-INF/jsp/login/loginUI.jsp").forward(request, response);
			return false;
			}
		
	
		//4.2 用户已经登录，判断用户是否具有权限
		return true;
		/*if(manager.hasPrivilegeByUrl(actionName)){//直接放行
				return true;
		}else{//没有权限，跳转到没有权限的界面
			request.getRequestDispatcher("/jsp/public/noPrivilege.jsp").forward(request, response);
			return false;
		}*/
			
	}
	

}

<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
	http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">
	<display-name></display-name>
	<!-- ==================Spring相关配置开始=====================-->
	 <!--配置字符过滤器 -->
	  <filter>  
	   <filter-name>CharacterEncodingFilter</filter-name>  
	   <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>  
	   <init-param>  
	       <param-name>encoding</param-name>  
	       <param-value>UTF-8</param-value>  
	   </init-param>  
	   <init-param>
			<param-name>forceEncoding</param-name>
			<param-value>true</param-value>
		</init-param>
	</filter>  
	 
	<filter-mapping>  
	   <filter-name>CharacterEncodingFilter</filter-name>  
	   <url-pattern>*.action</url-pattern>  
	</filter-mapping>
	
	<!-- spring3监听器 -->
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>classpath:applicationContext.xml</param-value>
	</context-param>

	 <!-- SpringMvc配置 -->
  <servlet>
  	<servlet-name>springmvc</servlet-name>
  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  		<init-param>
				<param-name>contextConfigLocation</param-name>
				<param-value>classpath:spring-mvc.xml</param-value>
		</init-param>
		<load-on-startup>2</load-on-startup>
  </servlet>
  
  <servlet-mapping>
  	<servlet-name>springmvc</servlet-name>
  	<url-pattern>*.action</url-pattern>
  </servlet-mapping>	
  
	<welcome-file-list>
		<welcome-file>/WEB-INF/jsp/login/loginUI.jsp</welcome-file>
	</welcome-file-list>
	<!-- ==================Spring相关配置开始结束=====================-->
	
	<!-- ===============配置错误跳转页面 注意错误页面大小不能超过1KB========================--> 
 	<!-- 对于服务器传递给我们的9大隐式对象，其他八大对象都可以在JSP翻译成的servlet中找到，
 		唯有这个异常隐式对象需要isErrorPage属性置为true的时候才会传递,
 	 因此要获取这个异常信息，必须要将page指令的isErrorPage属性置为true-->
  	<!-- 404 :未找到,访问的资源不存在-->
 	 <error-page>
		<error-code>404</error-code>
		<location>/jsp/public/error/404.jsp</location>
	</error-page> 
	<!--  500 :服务器内部错误 -->
 	<error-page>
		<error-code>500</error-code>
		<location>/jsp/public/error/500.jsp</location>
	</error-page>
	<!-- 服务器内部错误，空指针异常 -->
	<error-page>
		<exception-type>java.lang.NullPointerException</exception-type>
		<location>/jsp/public/error/nullPointer.jsp</location>
	</error-page>
	<!-- 其他异常，捕获所有为配置的异常 -->
	<error-page>
		<exception-type>java.lang.Exception</exception-type>
		<location>/jsp/public/error/error.jsp</location>
	</error-page> 

</web-app>

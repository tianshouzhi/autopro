<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
		http://www.springframework.org/schema/beans/spring-beans-3.0.xsd 
		http://www.springframework.org/schema/mvc 
		http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd 
		http://www.springframework.org/schema/context 
		http://www.springframework.org/schema/context/spring-context-3.0.xsd ">
	
	 <!-- 基于注解开发:首先配置注解驱动 -->
	<mvc:annotation-driven/> 
	<!-- 注解扫描：经测试spring注解开发，注解驱动可以不配置，只需要配置组件扫描即可 -->
	<context:component-scan base-package="${basePackage}.web.controller"></context:component-scan>
	
	<!-- 拦截器配置 -->
	<mvc:interceptors>
		<!-- 对所有请求进行拦截 -->
		<mvc:interceptor>
		<mvc:mapping path="/**"/>
		<bean class="${basePackage}.web.inteceptor.CheckPrivilegeInteceptor"></bean>
		</mvc:interceptor>
	   <mvc:interceptor>
		<!-- 设置拦截的路径-->		
		<mvc:mapping path="/**"/>
		<bean class="${basePackage}.web.inteceptor.MethodValidateInteceptor"/>
		</mvc:interceptor> 
	</mvc:interceptors>
	<!--配置数据校验-->	
	<bean id="validator"   
	class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean">  
	        <property name="providerClass"  value="org.hibernate.validator.HibernateValidator"/>  
	        <!-- 如果不加默认到 使用classpath下的 ValidationMessages.properties -->  
	        <property name="validationMessageSource" ref="messageSource"/>  
	</bean>

	<!-- 配置視圖解析器:内部资源视图解析器 InternalResourceViewResolver -->
	<bean
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<!-- 前缀 :指向jsp页面位于的目录 -->
		<property name="prefix" value="/WEB-INF/jsp/"></property>

		<!-- 后缀 -->
		<property name="suffix" value=".jsp"></property>
	</bean>
	<!--日期类型自动转换-->
	<bean class="org.springframework.format.support.FormattingConversionServiceFactoryBean"></bean>
</beans>

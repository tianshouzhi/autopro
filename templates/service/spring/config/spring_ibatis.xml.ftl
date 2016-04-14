<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
	xmlns:p="http://www.springframework.org/schema/p"
	xmlns:context="http://www.springframework.org/schema/context" 
	xmlns:task="http://www.springframework.org/schema/task" 
	xmlns:aop="http://www.springframework.org/schema/aop" 
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
        http://www.springframework.org/schema/task   
		http://www.springframework.org/schema/task/spring-task-3.0.xsd   
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context-3.0.xsd
        http://www.springframework.org/schema/aop 
        http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
        http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd">
	<context:component-scan base-package="${basePackage}.*" />
	<tx:annotation-driven />
	<context:annotation-config />
	<context:property-placeholder location="classpath:jdbc.properties" />
	<bean class="org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping" />
	<!-- 下面为ibatis配置 -->
	<aop:aspectj-autoproxy />
	<bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator" />
	<!-- configure DataSource -->
	<!--jndi方式 -->
	<!--<bean id="dataSource" class="org.springframework.jndi.JndiObjectFactoryBean">
		<property name="jndiName">
			<value>java:comp/env/jdbc/xxxxxxxx</value>
		</property>
	</bean>-->
	<!--c3p0方式 -->
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<property name="driverClass" value="${r"${"}jdbc.driver}"></property>
		<property name="jdbcUrl" value="${r"${"}jdbc.url}"></property>
		<property name="user" value="${r"${"}jdbc.username}"></property>
		<property name="password" value="${r"${"}jdbc.password}"></property>
		<!-- 初始化时，获取的数据库连接数，应该在minPoolSize和maxPoolSize之间，默认是3 -->
		<property name="initialPoolSize" value="1"></property>
		<!-- 数据库连接池中保持的最小连接数 -->
		<property name="minPoolSize" value="1"></property>
		<!-- 数据库连接池中保持的最大连接数 ，默认是15-->
		<property name="maxPoolSize" value="30"></property>
		<!-- 当数据库连接池中连接数小于配置的最低的连接数时，一次获取的连接数，默认是3 -->
		<property name="acquireIncrement" value="1"></property>
		<!-- 最大空闲时间，当connection总数最大容量时，超过的collection多少秒没人使用时，销毁 -->
		<property name="maxIdleTime" value="60"></property>
		<!-- 每个60秒检查一次数据库连接池中空闲连接 -->
		<property name="idleConnectionTestPeriod" value="60"></property>
	</bean>
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource">
			<ref bean="dataSource" />
		</property>
	</bean>

	<!-- oracle clob -->
	<bean id="nativeJdbcExtractor" 
	class="org.springframework.jdbc.support.nativejdbc.CommonsDbcpNativeJdbcExtractor" 	lazy-init="true" />
	<bean id="sqlMapClient" class="org.springframework.orm.ibatis.SqlMapClientFactoryBean">
		<property name="configLocation">
			<value>classpath:sqlMapConfig.xml</value>
		</property>
		<property name="dataSource" ref="dataSource" />
	</bean>
	<!-- jdbctemplate -->
	<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource"><ref bean="dataSource"/></property>
    </bean>
</beans>
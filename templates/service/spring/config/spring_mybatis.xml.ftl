<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans.xsd
	http://www.springframework.org/schema/context
	http://www.springframework.org/schema/context/spring-context.xsd">
	<!-- 配置freeMarker的模板路径 -->

	<!-- 导入外部属性文件 -->
	<context:property-placeholder location="classpath:jdbc.properties" />

	<!-- 数据库连接池 -->
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

	<!-- 配置sqlSessionFctory -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<!-- 将数据源交给sessionFactory -->
		<property name="dataSource" ref="dataSource"></property>
		<!-- 指定mybatis核心配置文件的位置 -->
		<property name="configLocation" value="classpath:sqlMapConfig.xml"></property>
	</bean>
</beans>
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans">
	<!-- 配置freeMarker的模板路径 -->

<bean id="freemarkerConfig" class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
	<property name="templateLoaderPath" value="/templates/" />
	<property name="defaultEncoding" value="UTF-8" />
	<property name="freemarkerSettings">
		<props>
			<prop key="template_update_delay">5</prop>
			<prop key="defaultEncoding">UTF-8</prop>
			<prop key="url_escaping_charset">UTF-8</prop>
			<prop key="locale">zh_CN</prop>
			<prop key="boolean_format">true,false</prop>
			<prop key="datetime_format">yyyy-MM-dd HH:mm:ss</prop>
			<prop key="date_format">yyyy-MM-dd</prop>
			<prop key="time_format">HH:mm:ss</prop>
			<prop key="number_format">0.######</prop>
			<prop key="whitespace_stripping">true</prop>
		</props>
	</property>
</beans>

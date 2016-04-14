<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>${groupId}</groupId>
	<artifactId>${artifactId}</artifactId>
	<packaging>war</packaging>
	<version>0.0.1-SNAPSHOT</version>
	<name>${projectName}</name>
	<url>http://maven.apache.org</url>
	<dependencies>
		<#list mavenDependencies as mavenDependency>
		<dependency>
			<groupId>${mavenDependency.groupId}</groupId>
			<artifactId>${mavenDependency.artifactId}</artifactId>
			<version>${mavenDependency.version}</version>
			<scope>${mavenDependency.scope}</scope>
		</dependency>
		</#list>
	</dependencies>
	<build>
		<finalName>${projectName}</finalName>
	</build>
</project>
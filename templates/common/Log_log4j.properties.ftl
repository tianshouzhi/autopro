log4j.rootLogger=INFO,dev

#产品环境的配置
log4j.appender.productenv=org.apache.log4j.DailyRollingFileAppender
log4j.appender.productenv.File=${r"${"}project}WEB-INF/logs/error.log
log4j.appender.productenv.DatePattern=.yyyy-MM-dd
log4j.appender.productenv.layout=org.apache.log4j.PatternLayout
log4j.appender.productenv.layout.conversionPattern=%d %c %-4r [%t] %-5p %c %x - %m%n

#开发环境的配置
log4j.appender.dev=org.apache.log4j.ConsoleAppender
log4j.appender.dev.Target=System.out
log4j.appender.dev.layout=org.apache.log4j.PatternLayout
log4j.appender.dev.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}\:%L -%m%n

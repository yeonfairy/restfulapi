package com.example.test.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan(basePackages="com.example.test.dao")
@EnableTransactionManagement
@EnableWebMvc
public class DatabaseConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/**")
	 .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
	.allowedOrigins("http://127.0.0.1:8081")
	.allowedOrigins("http://localhost:8081");
	}
	
	 @Bean
	 public  SqlSessionFactory  sqlSessionFactory(DataSource dataSource )  throws Exception {
	  SqlSessionFactoryBean sqlSessionFactory = new  SqlSessionFactoryBean();
	  sqlSessionFactory.setDataSource(dataSource);
	  
	  PathMatchingResourcePatternResolver resolver = new  PathMatchingResourcePatternResolver();
	  sqlSessionFactory.setMapperLocations(resolver.getResource("classpath:mapper/member-Mapper.xml")); ;
	  return sqlSessionFactory.getObject();
	   
	 }
	 
	 
	 @Bean
	 public  SqlSessionTemplate  sqlSessionTemplate(SqlSessionFactory  sqlSessionFactory) throws Exception{
	  
	  final  SqlSessionTemplate  sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
	  return sqlSessionTemplate;
	  
	 }
}

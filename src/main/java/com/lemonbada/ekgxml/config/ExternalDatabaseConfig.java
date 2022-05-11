package com.lemonbada.ekgxml.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.lemonbada.ekgxml.mapper.external",
    sqlSessionTemplateRef = "externalDataSourceSessionTemplate",
    sqlSessionFactoryRef = "externalDataSourceSessionFactory")
public class ExternalDatabaseConfig {

    @Bean(name = "externalDataSource")
    @ConfigurationProperties("ekgxml.datasource.external")
    public DataSource dataSource(){
        return new BasicDataSource();
    }

    @Bean(name = "externalDataSourceSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }
    @Bean(name = "externalDataSourceSessionTemplate")
    public SqlSessionTemplate sessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}


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
@MapperScan(basePackages = "com.lemonbada.ekgxml.mapper",
    sqlSessionTemplateRef = "sqlServerDataSourceSessionTemplate",
    sqlSessionFactoryRef = "sqlServerDataSourceSessionFactory")
public class SQLServerConfig {

    @Bean(name = "sqlServerDataSource")
    @ConfigurationProperties("ekgxml.collector.datasource")
    public DataSource dataSource(){
        return new BasicDataSource();
    }

    @Bean(name = "sqlServerDataSourceSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }
    @Bean(name = "sqlServerDataSourceSessionTemplate")
    public SqlSessionTemplate sessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}


package com.lemonbada.ekgxml.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.lemonbada.ekgxml.mapper.transformation",
    sqlSessionTemplateRef = "transformationDataSourceSessionTemplate",
    sqlSessionFactoryRef = "transformationDataSourceSessionFactory")
public class TransformationDatabaseConfig {

    @Bean(name = "transformationDataSourceProperties")
    @ConfigurationProperties("ekgxml.collector.datasource.transformation")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }
    @Bean(name = "transformationDataSource")
    @ConfigurationProperties("ekgxml.collector.datasource.transformation")
    public DataSource dataSource(){
        return dataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "transformationDataSourceSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        HikariDataSource x =  (HikariDataSource) dataSource();
        System.out.println(x.getJdbcUrl());
        System.out.println(x.getMaximumPoolSize());

        return sqlSessionFactoryBean.getObject();
    }
    @Bean(name = "transformationDataSourceSessionTemplate")
    public SqlSessionTemplate sessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}


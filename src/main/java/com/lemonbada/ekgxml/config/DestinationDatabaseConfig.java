package com.lemonbada.ekgxml.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
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
@MapperScan(basePackages = "com.lemonbada.ekgxml.mapper.destination",
    sqlSessionTemplateRef = "destinationDataSourceSessionTemplate",
    sqlSessionFactoryRef = "destinationDataSourceSessionFactory")
public class DestinationDatabaseConfig {

    @Bean(name = "destinationDataSourceProperties")
    @ConfigurationProperties("ekgxml.collector.datasource.destination")
    public DataSourceProperties dataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean(name = "destinationDataSource")
    @ConfigurationProperties("ekgxml.collector.datasource.destination")
    public DataSource dataSource(){
        return dataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "destinationDataSourceSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }
    @Bean(name = "destinationDataSourceSessionTemplate")
    public SqlSessionTemplate sessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}


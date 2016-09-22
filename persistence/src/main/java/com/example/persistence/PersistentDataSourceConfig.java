package com.example.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@Profile("production")
@PropertySource("classpath:jpa-production.properties")
class PersistentDataSourceConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(environment.getProperty("javax.persistence.jdbc.driver"));
        dataSource.setUrl(environment.getProperty("javax.persistence.jdbc.url"));
        dataSource.setUsername(environment.getProperty("javax.persistence.jdbc.user"));
        dataSource.setPassword(environment.getProperty("javax.persistence.jdbc.password"));

        dataSource.setValidationQuery("select 1 as dbcp_connection_test");

        return dataSource;
    }
}

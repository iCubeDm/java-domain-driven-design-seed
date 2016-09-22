package com.example.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.example.persistence.springdata")
@EnableTransactionManagement
@EnableJpaAuditing
class JpaConfig {

    @Autowired
    private Environment environment;

    @Autowired
    private DataSource dataSource;

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        final AbstractJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.example.persistence.entity");
        factory.setDataSource(dataSource);
        factory.setJpaProperties(getJpaProperties());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

    private Properties getJpaProperties() {

        final Properties properties = new Properties();

        properties.put("hibernate.dialect", environment.getProperty("jpa.vendor.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));

        properties.put("javax.persistence.schema-generation.create-database-schemas", environment.getProperty("javax.persistence.schema-generation.create-database-schemas", "false"));

        properties.put("javax.persistence.schema-generation.database.action", environment.getProperty("javax.persistence.schema-generation.database.action", "none"));
        properties.put("javax.persistence.schema-generation.create-source", environment.getProperty("javax.persistence.schema-generation.create-source", "metadata"));
        properties.put("javax.persistence.schema-generation.create-script-source", environment.getProperty("javax.persistence.schema-generation.create-script-source", ""));

        properties.put("javax.persistence.schema-generation.scripts.action", environment.getProperty("javax.persistence.schema-generation.scripts.action", "none"));
        properties.put("javax.persistence.schema-generation.scripts.create-target", environment.getProperty("javax.persistence.schema-generation.scripts.create-target", ""));
        properties.put("javax.persistence.schema-generation.scripts.drop-target", environment.getProperty("javax.persistence.schema-generation.scripts.drop-target", ""));

        return properties;
    }
}

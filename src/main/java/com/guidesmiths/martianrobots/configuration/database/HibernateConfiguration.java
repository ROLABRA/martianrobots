package com.guidesmiths.martianrobots.configuration.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfiguration {
    @Value("${hibernate.show.sql}")
    private String showHibernateSql;

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @DependsOn("dataSource")
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setDataSource(dataSource());
        bean.setPackagesToScan("com.guidesmiths.martianrobots");

        Properties additionalProperties = new Properties();
        additionalProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        additionalProperties.put("hibernate.show_sql", showHibernateSql);
        additionalProperties.put("hibernate.hbm2ddl.auto", "validate");
        additionalProperties.put("hibernate.hbm2ddl.jdbc_metadata_extraction_strategy", "individually");

        bean.setJpaProperties(additionalProperties);
        return bean;
    }

    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setDriverClassName("org.h2.Driver");
        driverManagerDataSource.setUrl("jdbc:h2:file:C:/data/sample;INIT=RUNSCRIPT FROM 'classpath:db-schema.sql'");
        driverManagerDataSource.setUsername("sa");
        driverManagerDataSource.setPassword("");

        return driverManagerDataSource;
    }
}

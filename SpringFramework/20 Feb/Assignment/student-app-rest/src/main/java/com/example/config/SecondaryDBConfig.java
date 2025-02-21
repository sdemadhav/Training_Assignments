package com.example.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

//Not prefferred configuration as we are harcoding every url password and secrets keys for database in java itself so we must pick if from the properties file
//@Configuration
@PropertySource({"classpath:application.properties"})
@EntityScan(basePackages = {"com.example.model"})  
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.repos.p2",
        entityManagerFactoryRef = "postgresEntityManagerFactory2",
        transactionManagerRef = "postgresTransactionManager2"
)
public class SecondaryDBConfig {

    @Bean(name = "postgresDataSource2")
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/secondarydb")
                .driverClassName("org.postgresql.Driver")
                .username("postgres")
                .password("tiger")
                .build();
    }

    @Bean(name = "postgresEntityManagerFactory2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postgresDataSource2") DataSource dataSource) {
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");

        return builder
                .dataSource(dataSource)
                .packages("com.example.model")  
                .persistenceUnit("postgres")
                .build();
    }

    @Bean(name = "postgresTransactionManager2")
    public PlatformTransactionManager transactionManager(
            @Qualifier("postgresEntityManagerFactory2") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}

package com.example.spring_boot_mysql_pgadmin.configuration;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.spring_boot_mysql_pgadmin.repository.postgresql",
        entityManagerFactoryRef = "postgresqlEntityManagerFactory",
        transactionManagerRef = "postgresqlTransactionManager"
)
public class PostgreSQLConfig {

    @Bean(name = "postgresqlDataSource")
    @ConfigurationProperties("spring.datasource.postgresql")
    public DataSource dataSource() {
        return new DriverManagerDataSource();
    }

    @Bean(name = "postgresqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postgresqlDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.spring_boot_mysql_pgadmin.entity") // Package containing JPA entities
                .persistenceUnit("postgresql")
                .properties(hibernateProperties())
                .build();
    }

    @Bean(name = "postgresqlTransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private Map<String, Object> hibernateProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return properties;
    }
}

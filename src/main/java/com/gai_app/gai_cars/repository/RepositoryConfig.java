package com.gai_app.gai_cars.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class RepositoryConfig {

    @Value("${repository.type}")
    private String repositoryType;

    @Autowired
    private CarJpaRepository carJpaRepository;
    @Autowired
    private CarJdbcRepository carJdbcRepository;

    @Bean
    @Primary
    public CarRepository carRepository() {
        if ("jdbc".equalsIgnoreCase(repositoryType)) {
            System.out.println("JDBC Car Repository selected.");
            return carJdbcRepository;
        } else {
            System.out.println("JPA Car Repository selected.");
            return carJpaRepository;
        }
    }
}
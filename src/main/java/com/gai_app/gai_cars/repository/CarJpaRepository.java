package com.gai_app.gai_cars.repository;

import com.gai_app.gai_cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarJpaRepository extends CarRepository, JpaRepository<Car, Long> {

    List<Car> findAll();

    Optional<Car> findById(Long id);

    Car save(Car car);

    void deleteById(Long id);
}

package com.gai_app.gai_cars.repository;

import com.gai_app.gai_cars.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

    List<Car> findAll();

    Optional<Car> findById(Long id);

    Car save(Car car);

    void deleteById(Long id);
}
package com.gai_app.gai_cars.controller;

import com.gai_app.gai_cars.DTO.CarDto;
import java.util.List;


public interface CarController {


    List<CarDto> getAllCars();

    CarDto getCarById(Long id);


    CarDto createCar(CarDto carDto);


    CarDto updateCar(Long id, CarDto carDto);


    void deleteCar(Long id);
}

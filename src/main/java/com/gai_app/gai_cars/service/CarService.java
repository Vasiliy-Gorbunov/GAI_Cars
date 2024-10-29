package com.gai_app.gai_cars.service;

import com.gai_app.gai_cars.model.CarModel;

import java.util.List;

public interface CarService {
    List<CarModel> getAllCars();
    CarModel getCarById(Long id);
    CarModel createCar(CarModel carModel);
    CarModel updateCar(Long id, CarModel updatedCar);
    void deleteCar(Long id);



}

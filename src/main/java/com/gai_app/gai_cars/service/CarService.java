package com.gai_app.gai_cars.service;

import com.gai_app.gai_cars.model.CarModel;

import java.util.List;

public interface CarService {
    public List<CarModel> getAllCars();
    public CarModel getCarById(Long id);
    public CarModel createCar(CarModel carModel);
    public CarModel updateCar(Long id, CarModel updatedCar);
    public void deleteCar(Long id);



}

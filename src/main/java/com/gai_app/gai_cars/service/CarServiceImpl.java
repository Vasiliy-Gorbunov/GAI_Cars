package com.gai_app.gai_cars.service;

import com.gai_app.gai_cars.entity.Car;
import com.gai_app.gai_cars.exception.ResourceNotFoundException;
import com.gai_app.gai_cars.mapper.MappingUtils;
import com.gai_app.gai_cars.model.CarModel;
import com.gai_app.gai_cars.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final MappingUtils mappingUtils;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, MappingUtils mappingUtils) {
        this.carRepository = carRepository;
        this.mappingUtils = mappingUtils;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarModel> getAllCars() {
        return carRepository.findAll().stream().map(mappingUtils::mapToCarModelFromEntity).collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public CarModel getCarById(Long id) {
        return mappingUtils.mapToCarModelFromEntity(carRepository.findById(id)
                .orElseThrow(() -> ThrowableMessage("Car", id)));
    }


    @Override
    @Transactional
    public CarModel createCar(CarModel carModel) {
        return mappingUtils.mapToCarModelFromEntity(carRepository.save(mappingUtils.mapToCar(carModel)));
    }


    @Override
    @Transactional
    public CarModel updateCar(Long id, CarModel updatedCar) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> ThrowableMessage("Car", id));

        Car updatingCar = mappingUtils.mapToCar(updatedCar);

        existingCar.setMake(updatingCar.getMake());
        existingCar.setModel(updatingCar.getModel());
        existingCar.setNumberPlate(updatingCar.getNumberPlate());
        existingCar.setAge(updatingCar.getAge());

        return mappingUtils.mapToCarModelFromEntity(carRepository.save(existingCar));
    }


    @Override
    @Transactional
    public void deleteCar(Long id) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> ThrowableMessage("Car", id));
        carRepository.deleteById(existingCar.getId());
    }


    private ResourceNotFoundException ThrowableMessage(String obj, Long id) {
        return new ResourceNotFoundException(obj + " with id " + id + " not found");
    }
}
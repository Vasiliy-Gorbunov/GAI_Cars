package com.gai_app.gai_cars.service;

import com.gai_app.gai_cars.entity.Car;
import com.gai_app.gai_cars.exception.ResourceNotFoundException;
import com.gai_app.gai_cars.mapper.MappingUtils;
import com.gai_app.gai_cars.model.CarModel;
import com.gai_app.gai_cars.repository.CarRepository;
import com.gai_app.gai_cars.service.kafka.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final MappingUtils mappingUtils;
    private final NotificationService notificationService;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, MappingUtils mappingUtils, NotificationService notificationService) {
        this.carRepository = carRepository;
        this.mappingUtils = mappingUtils;
        this.notificationService = notificationService;
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
                .orElseThrow(() -> ThrowableMessage(id)));
    }


    @Override
    @Transactional
    public CarModel createCar(CarModel carModel) {
        CarModel savedCar = mappingUtils.mapToCarModelFromEntity(carRepository
                .save(mappingUtils.mapToCar(carModel)));
        notificationService.getModelCreateMessageAndSend(savedCar, "created");
        return savedCar;
    }


    @Override
    @Transactional
    public CarModel updateCar(Long id, CarModel updatedCar) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> ThrowableMessage(id));

        Car updatingCar = mappingUtils.mapToCar(updatedCar);

        existingCar.setMake(updatingCar.getMake());
        existingCar.setModel(updatingCar.getModel());
        existingCar.setNumberPlate(updatingCar.getNumberPlate());
        existingCar.setDor(updatingCar.getDor());

        updatedCar = mappingUtils.mapToCarModelFromEntity(carRepository.save(existingCar));
        notificationService.getModelCreateMessageAndSend(updatedCar, "updated");
        return updatedCar;
    }


    @Override
    @Transactional
    public void deleteCar(Long id) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> ThrowableMessage(id));
        carRepository.deleteById(existingCar.getId());
        notificationService.getModelCreateMessageAndSend(mappingUtils
                .mapToCarModelFromEntity(existingCar), "deleted");
    }


    private ResourceNotFoundException ThrowableMessage(Long id) {
        return new ResourceNotFoundException("Car with id " + id + " not found");
    }
}
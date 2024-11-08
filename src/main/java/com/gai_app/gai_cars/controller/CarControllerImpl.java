package com.gai_app.gai_cars.controller;

import com.gai_app.gai_cars.DTO.CarDto;
import com.gai_app.gai_cars.mapper.MappingUtils;
import com.gai_app.gai_cars.model.CarModel;
import com.gai_app.gai_cars.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/cars")
public class CarControllerImpl implements CarController {

    private final CarService carService;
    private final MappingUtils mappingUtils;

    @Autowired
    public CarControllerImpl(CarService carService, MappingUtils mappingUtils) {
        this.carService = carService;
        this.mappingUtils = mappingUtils;
    }


    @GetMapping
    public List<CarDto> getAllCars() {
        return carService.getAllCars().stream().map(mappingUtils::mapToCarDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        return new ResponseEntity<>(mappingUtils.mapToCarDto
                (carService.getCarById(id)), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto createCar(@Valid @RequestBody CarDto carDto) {
        CarModel carModel = mappingUtils.mapToCarModelFromDto(carDto);
        return mappingUtils.mapToCarDto(carService.createCar(carModel));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CarDto updateCar(@PathVariable Long id, @Valid @RequestBody CarDto carDto) {
        CarModel updatedCar = mappingUtils.mapToCarModelFromDto(carDto);
        return mappingUtils.mapToCarDto(carService.updateCar(id, updatedCar));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

}
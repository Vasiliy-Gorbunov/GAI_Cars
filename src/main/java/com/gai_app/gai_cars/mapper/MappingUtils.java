package com.gai_app.gai_cars.mapper;

import com.gai_app.gai_cars.DTO.CarDto;
import com.gai_app.gai_cars.entity.Car;
import com.gai_app.gai_cars.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {

    @Autowired
    private CarMapper carMapper;

    public CarDto mapToCarDto(CarModel model) {
        return carMapper.toCarDto(model);
    }

    public CarModel mapToCarModelFromDto(CarDto dto) {
        return carMapper.toCarModelFromDto(dto);
    }

    public CarModel mapToCarModelFromEntity(Car car) {
        return carMapper.toCarModelFromEntity(car);
    }

    public Car mapToCar(CarModel model) {
        return carMapper.toCar(model);
    }
}

package com.gai_app.gai_cars.mapper;

import com.gai_app.gai_cars.DTO.CarDto;
import com.gai_app.gai_cars.entity.Car;
import com.gai_app.gai_cars.model.CarModel;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDto toCarDto(CarModel model);

    CarModel toCarModelFromDto(CarDto dto);

    CarModel toCarModelFromEntity(Car car);

    Car toCar(CarModel model);

    // Маппинг списка машин
    List<CarDto> toCarDtoList(List<CarModel> carModels);

    List<CarModel> toCarModelListFromDto(List<CarDto> carsDto);

    List<CarModel> toCarModelListFromEntity(List<Car> cars);

    List<Car> toCarList(List<CarModel> carModels);
}

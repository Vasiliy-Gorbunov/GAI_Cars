package com.gai_app.gai_cars.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
public class CarModel {

    private Long id;
    private String make;
    private String model;
    private String numberPlate;
    private Integer age;
}

package com.gai_app.gai_cars.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Getter
@Setter
@Component
public class CarModel {

    private Long id;
    private String make;
    private String model;
    private String numberPlate;
    private LocalDate dor;
}

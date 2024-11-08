package com.gai_app.gai_cars.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
@Data
public class CarModel {

    private Long id;
    private String make;
    private String model;
    private String numberPlate;
    private LocalDate dor;
}

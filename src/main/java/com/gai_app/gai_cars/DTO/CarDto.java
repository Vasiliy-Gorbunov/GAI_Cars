package com.gai_app.gai_cars.DTO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Getter
@Setter
@Component
public class CarDto {

    private Long id;

    @NotBlank(message = "Make cannot be null or blank")
    private String make;

    @NotBlank(message = "Model cannot be null or blank")
    private String model;

    @NotBlank(message = "Number plate cannot be null or blank")
    @Pattern(regexp = "^[a-zA-Z0-9. ]+$",
            message = "Name must contain only latin letters, numbers, points and spaces")
    @Size(min = 3, max = 20, message = "Number plate must be between 3 and 20 characters")
    private String numberPlate;

    @NotNull(message = "Date of release cannot be null")
    @Past(message = "Date of release must be in the past")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dor;


}

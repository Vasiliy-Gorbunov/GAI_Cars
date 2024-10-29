package com.gai_app.gai_cars.mapper;

import com.gai_app.gai_cars.entity.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {

    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        Car car = new Car();
        car.setId(rs.getLong("id"));
        car.setMake(rs.getString("make"));
        car.setModel(rs.getString("model"));
        car.setNumberPlate(rs.getString("number_plate"));
        car.setAge(rs.getInt("age"));

        return car;
    }
}

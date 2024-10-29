package com.gai_app.gai_cars.repository;

import com.gai_app.gai_cars.entity.Car;
import com.gai_app.gai_cars.mapper.CarRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CarJdbcRepository implements CarRepository {

    private final JdbcTemplate jdbcTemplate;

    public CarJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> findAll() {
        String sql = "SELECT * FROM car";
        return jdbcTemplate.query(sql, new CarRowMapper());
    }

    @Override
    public Optional<Car> findById(Long id) {
        String sql = "SELECT * FROM car WHERE id = ?";
        List<Car> cars = jdbcTemplate.query(sql, new Object[]{id}, new CarRowMapper());
        return cars.stream().findFirst();
    }

    @Override
    public Car save(Car car) {
        if (car.getId() == null) {
            String sql = "INSERT INTO car (make, model, number_plate, age) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql,
                    car.getMake(),
                    car.getModel(),
                    car.getNumberPlate(),
                    car.getAge());
        } else {
            String sql = "UPDATE car SET make = ?, model = ?, number_plate = ?, age = ? WHERE id = ?";
            jdbcTemplate.update(sql,
                    car.getMake(),
                    car.getModel(),
                    car.getNumberPlate(),
                    car.getAge(),
                    car.getId());
        }
        return car;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM car WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

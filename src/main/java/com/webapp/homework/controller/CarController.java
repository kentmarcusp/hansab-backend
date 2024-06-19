package com.webapp.homework.controller;

import com.webapp.homework.exception.DataNotFoundException;
import com.webapp.homework.model.Car;
import com.webapp.homework.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/hansab/cars")
public class CarController {

    @Autowired
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping({"/{carId}"})
    @ResponseBody
    public Optional<Car> getCarByCarId(@PathVariable long carId) {
        return Optional.ofNullable(carService.getCarByCarId(carId).orElseThrow(() -> new DataNotFoundException("Car with id: " + carId + " not found!")));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Car>> getCarDisplayOutput(
            @RequestParam(value = "find", required = false) String phrase,
            @RequestParam(value = "sort", required = false) String sort) {
        try {
            List<Car> carList = carService.fetchCarDisplayOutput(phrase, sort);
            return ResponseEntity.ok(carList);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

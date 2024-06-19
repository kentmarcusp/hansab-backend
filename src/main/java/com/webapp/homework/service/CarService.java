package com.webapp.homework.service;

import com.webapp.homework.exception.DataNotFoundException;
import com.webapp.homework.model.Car;
import com.webapp.homework.repository.CarRepository;
import com.webapp.homework.util.SortUtils;
import com.webapp.homework.util.StringInputHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private final CarRepository carRepository;
    private final StringInputHandler stringInputHandler;
    private final SortUtils sortUtils;

    public CarService(CarRepository carRepository, StringInputHandler stringInputHandler, SortUtils sortUtils) {
        this.carRepository = carRepository;
        this.stringInputHandler = stringInputHandler;
        this.sortUtils = sortUtils;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarByCarId(Long carId) throws DataNotFoundException {
        return Optional.ofNullable(carRepository.findById(carId)
                .orElseThrow(() -> new DataNotFoundException("Car with id: " + carId + " not found!")));
    }

    public List<Car> fetchCarDisplayOutput(String phrase, String sortingType) {
        List<Car> carList;
        phrase = stringInputHandler.decodeUrlParams(phrase);

        if (stringInputHandler.checkInputExistence(phrase)) {
            phrase = stringInputHandler.processPhraseInput(phrase);
            carList = findMatchingCarsByMakeOrModel(phrase);
        } else {
            carList = getAllCars();
        }

        handleCarColumnHeaderSort(sortingType, carList);
        return carList;
    }

    public void handleCarColumnHeaderSort(String sortingType, List<Car> carList) {
        if (stringInputHandler.checkInputExistence(sortingType)) {

            String columnHeader = stringInputHandler.getColumnHeader(sortingType);
            String sortPattern = stringInputHandler.getSortPattern(sortingType);

            switch (columnHeader) {
                case "model":
                    sortUtils.sortOutputOrderByFieldsAndSortPattern(columnHeader, sortPattern, carList, Car::getModel);
                    break;
                case "make":
                    sortUtils.sortOutputOrderByFieldsAndSortPattern(columnHeader, sortPattern, carList, Car::getMake);
                    break;
                case "numberplate":
                    sortUtils.sortOutputOrderByFieldsAndSortPattern(columnHeader, sortPattern, carList, Car::getNumberPlate);
                    break;
                default:
                    break;
            }
        }
    }

    public List<Car> findMatchingCarsByMakeOrModel(String phrase) {
        return carRepository.findByMakeContainingIgnoreCaseOrModelContainingIgnoreCase(phrase, phrase)
                .orElseThrow(() -> new DataNotFoundException("Error finding matching cars."));
    }

}

package com.webapp.homework;

import com.webapp.homework.model.Car;
import com.webapp.homework.model.UserAccount;
import com.webapp.homework.repository.CarRepository;
import com.webapp.homework.service.CarService;
import com.webapp.homework.util.SortUtils;
import com.webapp.homework.util.StringInputHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private StringInputHandler stringInputHandler;

    @Mock
    private CarRepository carRepository;

    @Mock
    private SortUtils sortUtils;

    @Test
    public void shouldFetchCorrectOrder_whenValidInputsPresent() {
        String phrase = "Honda";
        String sortingType = "make:asc";
        List<Car> mockCars = new ArrayList<>();
        mockCars.add(new Car("Honda", "Civic","XXT226", new UserAccount()));
        mockCars.add(new Car("Bonda", "NotCivic","XXD226", new UserAccount()));

        List<Car> sortedCars = new ArrayList<>();
        sortedCars.add(new Car(1L, "Bonda", "NotCivic","XXD226", new UserAccount()));
        sortedCars.add(new Car(2L, "Honda", "Civic","XXT226", new UserAccount()));

        when(stringInputHandler.checkInputExistence(phrase)).thenReturn(true);
        when(stringInputHandler.decodeUrlParams(phrase)).thenReturn(phrase);
        when(stringInputHandler.processPhraseInput(phrase)).thenReturn(phrase);

        when(carRepository.findByMakeContainingIgnoreCaseOrModelContainingIgnoreCase(phrase, phrase)).thenReturn(Optional.of(sortedCars));

        List<Car> result = carService.fetchCarDisplayOutput(phrase, sortingType);

        assertEquals(2, result.size());
        assertEquals("Bonda", result.get(0).getMake());
        assertEquals("NotCivic", result.get(0).getModel());
        assertEquals("XXD226", result.get(0).getNumberPlate());
        assertNotEquals(mockCars, sortedCars);
    }

    @Test
    public void shouldFetchAllCars_whenInvalidSortData() {
        String phrase = "honda";
        String sortingType = "random:xxx";
        List<Car> mockCars = new ArrayList<>();
        mockCars.add(new Car(1L, "Honda", "Civic","XXT226", new UserAccount()));
        mockCars.add(new Car(2L, "Bonda", "NotCivic","ddddd22", new UserAccount()));

        when(carService.getAllCars()).thenReturn(mockCars);

        List<Car> result = carService.fetchCarDisplayOutput(phrase, sortingType);

        assertEquals(2, result.size());
        assertEquals("Honda", result.get(0).getMake());
        assertEquals("Civic", result.get(0).getModel());
        assertEquals("XXT226", result.get(0).getNumberPlate());

        verify(sortUtils, never()).sortOutputOrderByFieldsAndSortPattern(any(), any(), any(), any());

        assertEquals(mockCars, result);
    }
}

package com.webapp.homework.repository;

import com.webapp.homework.model.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {

    //Optional<Car> findcarByCar_Id(Long carId);

    Optional<List<Car>> findByMakeContainingIgnoreCaseOrModelContainingIgnoreCase(String makePhrase, String ModelPhrase);
}

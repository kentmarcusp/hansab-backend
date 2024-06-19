package com.webapp.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private long carId;

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "number_plate", nullable = false)
    private String numberPlate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"cars", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user_account;

    public Car(String make, String model, String numberPlate, UserAccount userAccount) {
        this.make = make;
        this.model = model;
        this.numberPlate = numberPlate;
        this.user_account = userAccount;
    }
}
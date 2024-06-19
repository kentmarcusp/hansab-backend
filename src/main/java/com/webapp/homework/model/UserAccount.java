package com.webapp.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="user_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @JsonIgnoreProperties(value = {"user_account", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    @OneToMany(mappedBy = "user_account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars;

}

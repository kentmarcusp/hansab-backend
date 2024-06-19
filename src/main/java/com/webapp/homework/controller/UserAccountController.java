package com.webapp.homework.controller;

import com.webapp.homework.model.Car;
import com.webapp.homework.model.UserAccount;
import com.webapp.homework.service.UserAccountService;
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
@RequestMapping("/hansab/users")
public class UserAccountController {

    @Autowired
    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }


    @GetMapping
    @ResponseBody
    public ResponseEntity<List<UserAccount>> getUserDataOutput(
            @RequestParam(value = "find", required = false) String phrase,
            @RequestParam(value = "sort", required = false) String sort) {

        try {
            List<UserAccount> userAccounts = userAccountService.fetchUserDisplayOutput(phrase, sort);
            return ResponseEntity.ok(userAccounts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{userId}")
    @ResponseBody
    public Optional<UserAccount> getUserByUserId(@PathVariable Long userId) {
        return userAccountService.getUserByUserId(userId);
    }

    @GetMapping("/{userId}/cars")
    @ResponseBody
    public List<Car> getCarsByUserId(@PathVariable Long userId) {
        return userAccountService.getCarsByUserId(userId);
    }
}

package com.webapp.homework.service;

import com.webapp.homework.exception.DataNotFoundException;

import com.webapp.homework.model.Car;
import com.webapp.homework.model.UserAccount;
import com.webapp.homework.repository.UserAccountRepository;
import com.webapp.homework.util.SortUtils;
import com.webapp.homework.util.StringInputHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {

    @Autowired
    private final UserAccountRepository userAccountRepository;
    private final StringInputHandler stringInputHandler;
    private final SortUtils sortUtils;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository, StringInputHandler stringInputHandler, SortUtils sortUtils) {
        this.userAccountRepository = userAccountRepository;
        this.stringInputHandler = stringInputHandler;
        this.sortUtils = sortUtils;
    }

    public Optional<UserAccount> getUserByUserId(Long userId) {
        return Optional.ofNullable(userAccountRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User with id: " + userId + " not found!")));

    }

    public List<Car> getCarsByUserId(Long userId) {
        UserAccount user = userAccountRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User with id: " + userId + " not found!"));
        return user.getCars();
    }

    public List<UserAccount> fetchUserDisplayOutput(String phrase, String sortingType) {

        List<UserAccount> userAccountList;
        phrase = stringInputHandler.decodeUrlParams(phrase);

        if (stringInputHandler.checkInputExistence(phrase)) {
            phrase = stringInputHandler.processPhraseInput(phrase);
            userAccountList = findMatchingUsers(phrase);
        } else {
            userAccountList = getAllUsers();
        }

        if (stringInputHandler.checkInputExistence(sortingType)) {
            String columnHeader = stringInputHandler.getColumnHeader(sortingType);
            String sortPattern = stringInputHandler.getSortPattern(sortingType);
            sortUtils.sortOutputOrderByFieldsAndSortPattern(columnHeader, sortPattern, userAccountList, UserAccount::getUsername);
        }
        return userAccountList;
    }

    public List<UserAccount> findMatchingUsers(String phrase) {
        return userAccountRepository.findByUsernameContainingIgnoreCase(phrase);
    }

    public List<UserAccount> getAllUsers() {
        return userAccountRepository.findAll();
    }

}

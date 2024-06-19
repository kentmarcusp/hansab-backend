package com.webapp.homework;

import com.webapp.homework.model.UserAccount;
import com.webapp.homework.repository.UserAccountRepository;
import com.webapp.homework.service.UserAccountService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAccountServiceTest {

    @InjectMocks
    private UserAccountService userAccountService;

    @Mock
    private StringInputHandler stringInputHandler;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private SortUtils sortUtils;

    private final String testUserName = "hansab_testUser";

    @Test
    public void shouldFindUsernameByUserId_whenUserExists() {
        UserAccount userAccount = mockUpUserAccountData().get(1);
        when(userAccountRepository.findById(2L)).thenReturn(Optional.of(userAccount));

        Optional<UserAccount> foundUser = userAccountService.getUserByUserId(2L);

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo(testUserName);
        verify(userAccountRepository, times(1)).findById(2L);
    }

    @Test
    public void shouldFetchOnlySortedUserDisplayOutput_whenPhrase_andSortingTypeExist() {
        String phrase = "hansab%23";
        String sortingType = "name:AsC";
        String decodedUrlPhrase = "hansab#";

        when(stringInputHandler.decodeUrlParams(phrase)).thenReturn(decodedUrlPhrase);
        when(stringInputHandler.checkInputExistence(decodedUrlPhrase)).thenReturn(true);
        when(stringInputHandler.processPhraseInput(decodedUrlPhrase)).thenReturn(decodedUrlPhrase);

        when(stringInputHandler.checkInputExistence(sortingType)).thenReturn(true);
        when(stringInputHandler.getColumnHeader(sortingType)).thenReturn("name");
        when(stringInputHandler.getSortPattern(sortingType)).thenReturn("asc");

        List<UserAccount> mockUserAccounts = mockUpUserAccountData();
        List<UserAccount> postHandlingList = new ArrayList<>();

        postHandlingList.add(mockUserAccounts.get(1));

        when(userAccountService.findMatchingUsers(decodedUrlPhrase)).thenReturn(postHandlingList);
        doNothing().when(sortUtils).sortOutputOrderByFieldsAndSortPattern(anyString(), anyString(), anyList(), any());

        List<UserAccount> result = userAccountService.fetchUserDisplayOutput(phrase, sortingType);

        assertEquals(1, result.size());
        assertEquals(postHandlingList.get(0).getUsername(), result.get(0).getUsername());

        verifyNoMoreInteractions(stringInputHandler, userAccountRepository);
    }

    private List<UserAccount> mockUpUserAccountData() {
        List<UserAccount> userAccountList = new ArrayList<>();

        UserAccount userAccountRandom = new UserAccount();
        userAccountRandom.setUserId(1L);
        String testUserNameExotic = "testUser%23";
        userAccountRandom.setUsername(testUserNameExotic);
        userAccountRandom.setCars(new ArrayList<>());

        UserAccount userAccountHansab = new UserAccount();
        userAccountHansab.setUserId(2L);
        userAccountHansab.setUsername(testUserName);
        userAccountHansab.setCars(new ArrayList<>());

        userAccountList.add(userAccountRandom);
        userAccountList.add(userAccountHansab);
        return userAccountList;
    }
}

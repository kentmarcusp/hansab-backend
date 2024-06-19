package com.webapp.homework.repository;

import com.webapp.homework.model.UserAccount;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    List<UserAccount> findByUsernameContainingIgnoreCase(String phrase);
}

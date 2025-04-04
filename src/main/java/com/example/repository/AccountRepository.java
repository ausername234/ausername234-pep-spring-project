package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT a.accountId FROM Account a WHERE a.username = ?1 AND a.password = ?2")
    Optional<Integer> login(String username, String password);

    boolean existsByUsername(String username);

}

package com.example.service;

import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;


@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerRequest(Account a) throws Exception {
        String username = a.getUsername();
        String password = a.getPassword();

        if(username.isBlank() || password.length() < 4) {
            throw new Exception();
        }

        return accountRepository.save(a);
    }


    public void loginRequest(Account a) throws Exception {
        Integer accountId = accountRepository.login(a.getUsername(),a.getPassword()).orElseThrow(() -> new Exception());
        a.setAccountId(accountId);
    }


    
}

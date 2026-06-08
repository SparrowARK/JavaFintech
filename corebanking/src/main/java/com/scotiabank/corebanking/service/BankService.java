package com.scotiabank.corebanking.service;

import com.scotiabank.corebanking.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    private final BankAccountRepository accountRepository;

    public BankService(BankAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}


package com.scotiabank.corebanking.service;

import com.scotiabank.corebanking.model.BankAccount;
import com.scotiabank.corebanking.repository.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    // 🔒 @Transactional guarantees atomicity. If it fails halfway, it rolls back entirely!
    @Transactional
    public String transferFunds(Long fromId, Long toId, Double amount) {
        // 1. Find both accounts
        BankAccount sender = repository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Sender account not found!"));
        BankAccount receiver = repository.findById(toId)
                .orElseThrow(() -> new RuntimeException("Receiver account not found!"));

        // 2. Check the balance
        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds!");
        }

        // 3. Move the money
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        // 4. Save the updated states to the database
        repository.save(sender);
        repository.save(receiver);

        return "Successfully transferred $" + amount + " to " + receiver.getOwnerName();
    }
}
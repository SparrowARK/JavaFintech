package com.scotiabank.corebanking.service;
import com.scotiabank.corebanking.model.BankAccount;
import jakarta.transaction.Transactional;
import com.scotiabank.corebanking.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    private final BankAccountRepository accountRepository;

    public BankService(BankAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(Long fromAccountNumber, Long toAccountNumber, double amount){
        BankAccount sender = accountRepository.findById(fromAccountNumber).orElseThrow(() -> new RuntimeException("Sender account not found"));

        BankAccount receiver = accountRepository.findById(toAccountNumber).orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if(sender.getBalance()<amount){
            throw new RuntimeException("Insufficient funds transfer cancelled");
        }

        sender.setBalance(sender.getBalance()-amount);
        receiver.setBalance(receiver.getBalance()+amount);

        accountRepository.save(sender);
        accountRepository.save(receiver);
        System.out.println("Transfer Complete Moved $" + amount);
    }
}


package com.scotiabank.corebanking.controller;
import com.scotiabank.corebanking.model.BankAccount;
import com.scotiabank.corebanking.repository.BankAccountRepository;
import com.scotiabank.corebanking.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import com.scotiabank.corebanking.dto.TransferRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank")
public class BankAccountController {

    private final BankAccountRepository repository;
    private final BankAccountService service;

    public BankAccountController(BankAccountRepository repository, BankAccountService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<BankAccount> getAccountDetails(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody TransferRequest request) {
        try {
            // Hand the form data to the Transaction Engine
            String result = service.transferFunds(
                    request.getFromAccountId(),
                    request.getToAccountId(),
                    request.getAmount()
            );
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            // If the engine throws an error (like insufficient funds), return a 400 Bad Request
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
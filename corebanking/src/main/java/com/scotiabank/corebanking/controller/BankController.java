package com.scotiabank.corebanking.controller;
import com.scotiabank.corebanking.service.BankService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/api/bank")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/transfer")
    public String executeTransfer(
            @RequestParam Long fromAccount,
            @RequestParam Long toAccount,
            @RequestParam double amount){
        bankService.transferMoney(fromAccount, toAccount, amount);
        return "Success: Transferred $" + amount + " From Account "+ fromAccount + " to Account " + toAccount;
    }
}

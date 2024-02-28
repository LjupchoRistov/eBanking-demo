package com.ebanking.controller;

import com.ebanking.dto.BankAccountDto;
import com.ebanking.dto.CurrencyTypeDto;
import com.ebanking.service.BankAccountService;
import com.ebanking.service.TransactionService;
import com.ebanking.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransactionController {
    private final BankAccountService bankAccountService;
    private final TransactionService transactionService;
    private final UserService userService;

    public TransactionController(BankAccountService bankAccountService, TransactionService transactionService, UserService userService) {
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @PostMapping("/transaction/new")
    public String createTransaction(
            @RequestParam String senderNum,
            @RequestParam String receiverNum,
            @RequestParam String currencyTypeSenderId,
            @RequestParam String description,
            @RequestParam String amount,
            Model model
    ) {


        return null;
    }
}

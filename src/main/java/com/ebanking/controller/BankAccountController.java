package com.ebanking.controller;

import com.ebanking.dto.BankAccountDto;
import com.ebanking.dto.TransactionDto;
import com.ebanking.models.UserEntity;
import com.ebanking.repository.TransactionRepository;
import com.ebanking.service.BankAccountService;
import com.ebanking.service.TransactionService;
import com.ebanking.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final TransactionService transactionService;
    private final UserService userService;

    public BankAccountController(BankAccountService bankAccountService, TransactionService transactionService, UserService userService) {
        this.bankAccountService = bankAccountService;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping("/user/accounts")
    public String getUserBankAccount(Model model){
        String username = "bubsi";
        UserEntity user = this.userService.findByUsername(username);
        List<BankAccountDto> accounts = this.bankAccountService.findBankAccountsByUser(user);

        model.addAttribute("accounts", accounts);

        return "accounts-list";
    }

    @GetMapping("/user/{id}/account")
    public String previewAccount(@PathVariable Long id,
                                 Model model){

        // Add the Bank Account to the model
        BankAccountDto bankAccountDto = this.bankAccountService.findBankAccountById(id);
        model.addAttribute("account", bankAccountDto);

        // Add the user of the acc
        UserEntity user = bankAccountDto.getUser();
        model.addAttribute("user", this.userService.findByUsername(user.getUsername()));

        // Add the Treansactions of the Bank Account
        List<TransactionDto> transactionDtoList = this.transactionService.findAllByBankAccount(bankAccountDto);
        model.addAttribute("transactions", transactionDtoList);

        // Add TransactionDto template
        TransactionDto transactionDto = new TransactionDto();
        model.addAttribute("transaction", transactionDto);

        return "accounts-details";
    }

    @GetMapping("/layout")
    public String layout(){
        return "accounts-list";
    }
}

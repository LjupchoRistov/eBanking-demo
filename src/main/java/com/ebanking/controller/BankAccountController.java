package com.ebanking.controller;

import com.ebanking.dto.BankAccountDto;
import com.ebanking.models.BankAccount;
import com.ebanking.models.UserEntity;
import com.ebanking.security.SecurityUtil;
import com.ebanking.service.BankAccountService;
import com.ebanking.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final UserService userService;

    public BankAccountController(BankAccountService bankAccountService, UserService userService) {
        this.bankAccountService = bankAccountService;
        this.userService = userService;
    }

    @GetMapping("/account")
    public String getUserBankAccount(Model model){
        String username = SecurityUtil.getSessionUser();
        UserEntity user = this.userService.findByUsername(username);
        List<BankAccountDto> accounts = this.bankAccountService.findBankAccountsByUser(user);

        model.addAttribute("accounts", accounts);

        return "accounts-list";
    }
}

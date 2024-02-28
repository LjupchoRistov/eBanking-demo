package com.ebanking.service;

import com.ebanking.dto.BankAccountDto;
import com.ebanking.models.UserEntity;

import java.util.List;

public interface BankAccountService {
    List<BankAccountDto> findBankAccountsByUser(UserEntity user);

    BankAccountDto findBankAccountById(Long id);
}

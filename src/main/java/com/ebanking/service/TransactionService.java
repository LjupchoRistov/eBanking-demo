package com.ebanking.service;

import com.ebanking.dto.BankAccountDto;
import com.ebanking.dto.TransactionDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TransactionService {
    List<TransactionDto> findAllByBankAccount(BankAccountDto bankAccountDto);
    TransactionDto createTransaction(TransactionDto transactionDto);
}

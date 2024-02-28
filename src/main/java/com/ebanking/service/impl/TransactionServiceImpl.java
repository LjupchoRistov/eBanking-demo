package com.ebanking.service.impl;

import com.ebanking.dto.BankAccountDto;
import com.ebanking.dto.TransactionDto;
import com.ebanking.mapper.TransactionMappper;
import com.ebanking.models.Transaction;
import com.ebanking.repository.BankAccountRepository;
import com.ebanking.repository.TransactionRepository;
import com.ebanking.repository.UserRepository;
import com.ebanking.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ebanking.mapper.BankAccountMapper.*;
import static com.ebanking.mapper.TransactionMappper.*;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionDto> findAllByBankAccount(BankAccountDto bankAccountDto) {
        List<Transaction> sender = this.transactionRepository.findAllBySenderEquals(mapToBankAccount(bankAccountDto));
        List<Transaction> receiver = this.transactionRepository.findAllByReceiverEquals(mapToBankAccount(bankAccountDto));

        List<Transaction> allTransactions = new ArrayList<>();
        allTransactions.addAll(sender);
        allTransactions.addAll(receiver);

        return allTransactions.stream().map(TransactionMappper::mapToTransactionDto).collect(Collectors.toList());
    }
}

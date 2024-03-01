package com.ebanking.mapper;

import com.ebanking.dto.TransactionDto;
import com.ebanking.models.Transaction;
import com.ebanking.repository.BankAccountRepository;

import static com.ebanking.mapper.CurrencyTypeMapper.*;
import static com.ebanking.mapper.BankAccountMapper.*;

public class TransactionMappper {
    public static Transaction mapToTransaction(TransactionDto transactionDto, BankAccountRepository bankAccountRepository){
        return  Transaction.builder()
                .id(transactionDto.getId())
                .sender(mapToBankAccount(transactionDto.getSender()))
                .amount(Double.valueOf(transactionDto.getAmount()))
                .currencyTypeSender(mapToCurrencyType(transactionDto.getCurrencyTypeSender()))
                .receiver(bankAccountRepository.findByAccountNumEquals(Integer.valueOf(transactionDto.getReceiver())))
                .transactionDate(transactionDto.getTransactionDate())
                .build();
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction){
        return  TransactionDto.builder()
                .id(transaction.getId())
                .sender(mapToBankAccountDto(transaction.getSender()))
                .amount(String.valueOf(transaction.getAmount()))
                .currencyTypeSender(mapToCurrencyTypeDto(transaction.getCurrencyTypeSender()))
                .receiver(transaction.getSender().getAccountNum().toString())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }
}

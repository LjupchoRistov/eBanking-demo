package com.ebanking.mapper;

import com.ebanking.dto.TransactionDto;
import com.ebanking.models.Transaction;

import static com.ebanking.mapper.CurrencyTypeMapper.*;

public class TransactionMappper {
    public static Transaction mapToTransaction(TransactionDto transactionDto){
        return  Transaction.builder()
                .id(transactionDto.getId())
                .sender(transactionDto.getSender())
                .amount(transactionDto.getAmount())
                .currencyTypeSender(mapToCurrencyType(transactionDto.getCurrencyTypeSender()))
                .receiver(transactionDto.getReceiver())
                .transactionDate(transactionDto.getTransactionDate())
                .build();
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction){
        return  TransactionDto.builder()
                .id(transaction.getId())
                .sender(transaction.getSender())
                .amount(transaction.getAmount())
                .currencyTypeSender(mapToCurrencyTypeDto(transaction.getCurrencyTypeSender()))
                .receiver(transaction.getReceiver())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }
}

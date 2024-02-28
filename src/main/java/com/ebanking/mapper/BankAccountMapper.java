package com.ebanking.mapper;

import com.ebanking.dto.BankAccountDto;
import com.ebanking.models.BankAccount;


public class BankAccountMapper {
    public static BankAccount mapToBankAccount(BankAccountDto bankAccountDto){
        return BankAccount.builder()
                .id(bankAccountDto.getId())
                .accountNum(bankAccountDto.getAccountNum())
                .isDebit(bankAccountDto.getIsDebit())
                .amount(bankAccountDto.getAmount())
                .currencyType(bankAccountDto.getCurrencyType())
                .dateCreatedOn(bankAccountDto.getDateCreatedOn())
                .user(bankAccountDto.getUser())
                .build();
    }

    public static BankAccountDto mapToBankAccountDto(BankAccount bankAccount){
        return BankAccountDto.builder()
                .id(bankAccount.getId())
                .accountNum(bankAccount.getAccountNum())
                .isDebit(bankAccount.getIsDebit())
                .amount(bankAccount.getAmount())
                .currencyType(bankAccount.getCurrencyType())
                .dateCreatedOn(bankAccount.getDateCreatedOn())
                .user(bankAccount.getUser())
                .build();
    }
}

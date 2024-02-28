package com.ebanking.dto;

import com.ebanking.models.BankAccount;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class TransactionDto {
    private Long id;
    private String Description;
    private BankAccount sender;
    private BankAccount receiver;
    private Long amount;
    private CurrencyTypeDto currencyTypeSender;
    private LocalDateTime transactionDate;
}
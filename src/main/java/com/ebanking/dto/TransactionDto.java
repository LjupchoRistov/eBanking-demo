package com.ebanking.dto;

import com.ebanking.models.BankAccount;
import com.ebanking.models.enums.CType;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
public class TransactionDto {
    private Integer id;
    private BankAccount sender;
    private BankAccount receiver;
    private Long amount;
    private CType currencyTypeSender;
    private Date transactionDate;
}
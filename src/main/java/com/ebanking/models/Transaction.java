package com.ebanking.models;

import com.ebanking.models.enums.CType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.type.CurrencyType;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table
@Entity(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Description;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private BankAccount sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private BankAccount receiver;

    private Long amount;
    @Enumerated(EnumType.STRING)
    private CType currencyTypeSender;

    @CreationTimestamp
    private LocalDateTime transactionDate;
}

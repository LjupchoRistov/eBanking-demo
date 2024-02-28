package com.ebanking.dto;

import com.ebanking.models.CurrencyType;
import com.ebanking.models.UserEntity;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class BankAccountDto {
    private Long id;
    private Integer accountNum;
    private Boolean isDebit;
    private Long amount;
    private LocalDateTime dateCreatedOn;
    private CurrencyTypeDto currencyTypeDto;
    private UserEntity user;
}

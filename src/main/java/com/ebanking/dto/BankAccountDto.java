package com.ebanking.dto;

import com.ebanking.models.UserEntity;
import com.ebanking.models.enums.CType;
import lombok.Builder;
import lombok.Data;

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
    private UserEntity user;
    private LocalDateTime dateCreatedOn;
    private CType currencyType;
}

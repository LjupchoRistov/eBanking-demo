package com.ebanking.dto;

import com.ebanking.models.UserEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
public class BankAccountDto {
    private Long id;
    private Integer accountNum;
    private Boolean isDebit;
    private Long amount;
    private UserEntity user;
}

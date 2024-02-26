package com.ebanking.dto;

import com.ebanking.models.UserEntity;
import com.ebanking.models.enums.CType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
public class BankAccountDto {
    private Long id;
    private Integer accountNum;
    private Boolean isDebit;
    private Long amount;
    private UserEntity user;
    private Date dateCreatedOn;
    private CType currencyType;
}

package com.ebanking.data;

import com.ebanking.models.*;
import com.ebanking.repository.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BankDataInit {

    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;
    private final CurrencyTypeRepository currencyTypeRepository;
    private final RoleRepository roleRepository;

    public BankDataInit(UserRepository userRepository, BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository, CurrencyTypeRepository currencyTypeRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
        this.currencyTypeRepository = currencyTypeRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void insertData() {
        if (this.roleRepository.findAll().isEmpty()) {
            this.roleRepository.save(new Role((long) 1, "ADMIN", new ArrayList<>()));
            this.roleRepository.save(new Role((long) 2, "EDITOR", new ArrayList<>()));
            this.roleRepository.save(new Role((long) 3, "USER", new ArrayList<>()));
        }

        if (this.userRepository.findAll().isEmpty()) {
            List<Role> roles = new ArrayList<>();
            roles.add(this.roleRepository.findById((long) 1).get());
            roles.add(this.roleRepository.findById((long) 2).get());
            roles.add(this.roleRepository.findById((long) 3).get());

            // HP: FEA6nGcpvnnvxNoKe4B/M1vqng1UPg3/96ntP9ajST0=
            // S: /OuH4gW9fJJarBgxZA7JcA==
            this.userRepository.save(new UserEntity((long) 999, "bubsi", "ljupcoristov02@gmail.com", "Ljupcho", "Ristov", "Skopje, Tiranska 1b", "FEA6nGcpvnnvxNoKe4B/M1vqng1UPg3/96ntP9ajST0=", "/OuH4gW9fJJarBgxZA7JcA==", roles));
        }

        if (this.currencyTypeRepository.findAll().isEmpty()){
            this.currencyTypeRepository.save(new CurrencyType((long) 999, "Macedonian Denar", (float) 1));
            this.currencyTypeRepository.save(new CurrencyType((long) 999, "Euro", (float) 61.67));
            this.currencyTypeRepository.save(new CurrencyType((long) 999, "United States Dollar", (float) 56.90));
        }

        if (this.bankAccountRepository.findAll().isEmpty()){
            UserEntity user = this.userRepository.findAll().get(0);
            CurrencyType currencyType = this.currencyTypeRepository.findByNameEquals("Macedonian Denar");
            this.bankAccountRepository.save(new BankAccount((long) 999, 1111111111, true, (long)10000, LocalDateTime.now(), currencyType, user));
            this.bankAccountRepository.save(new BankAccount((long) 999, 1111111112, true, (long)18000, LocalDateTime.now(), currencyType, user));
            this.bankAccountRepository.save(new BankAccount((long) 999, 1111111113, true, (long)340000, LocalDateTime.now(), currencyType, user));
        }

        if (this.transactionRepository.findAll().isEmpty()){
            BankAccount bankAccount1 = this.bankAccountRepository.findByAccountNumEquals(1111111111);
            BankAccount bankAccount2 = this.bankAccountRepository.findByAccountNumEquals(1111111112);
            CurrencyType currencyType = this.currencyTypeRepository.findByNameEquals("Macedonian Denar");
            this.transactionRepository.save(new Transaction((long) 999, "Shopping", bankAccount1, bankAccount2, (long)900, currencyType, LocalDateTime.now()));
            this.transactionRepository.save(new Transaction((long) 999, "New Computer", bankAccount1, bankAccount2, (long)45000, currencyType, LocalDateTime.now()));
            this.transactionRepository.save(new Transaction((long) 999, "For Dinner", bankAccount2, bankAccount1, (long)1100, currencyType, LocalDateTime.now()));
            this.transactionRepository.save(new Transaction((long) 999, "Ice Cream", bankAccount1, bankAccount2, (long)200, currencyType, LocalDateTime.now()));
            this.transactionRepository.save(new Transaction((long) 999, "For New Keyboard", bankAccount2, bankAccount1, (long)1600, currencyType, LocalDateTime.now()));
        }
    }
}

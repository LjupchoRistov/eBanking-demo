package com.ebanking.data;

import com.ebanking.models.BankAccount;
import com.ebanking.models.Role;
import com.ebanking.models.Transaction;
import com.ebanking.models.UserEntity;
import com.ebanking.models.enums.CType;
import com.ebanking.repository.BankAccountRepository;
import com.ebanking.repository.RoleRepository;
import com.ebanking.repository.TransactionRepository;
import com.ebanking.repository.UserRepository;
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
    private final RoleRepository roleRepository;

    public BankDataInit(UserRepository userRepository, BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
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

            this.userRepository.save(new UserEntity((long) 999, "bubsi", "ljupcoristov02@gmail.com", "FEA6nGcpvnnvxNoKe4B/M1vqng1UPg3/96ntP9ajST0=", "/OuH4gW9fJJarBgxZA7JcA==", roles));
        }

        if (this.bankAccountRepository.findAll().isEmpty()){
            UserEntity user = this.userRepository.findById((long)1).get();
            this.bankAccountRepository.save(new BankAccount((long) 999, 1111111111, true, (long)10000, LocalDateTime.now(), CType.Macedonian_Denar, user));
            this.bankAccountRepository.save(new BankAccount((long) 998, 1111111112, true, (long)15000, LocalDateTime.now(), CType.Macedonian_Denar, user));
            this.bankAccountRepository.save(new BankAccount((long) 997, 1111111113, false, (long)500, LocalDateTime.now(), CType.Dollar, user));
        }

        if (this.transactionRepository.findAll().isEmpty()){
            UserEntity user = this.userRepository.findById((long)1).get();
            BankAccount bankAccount1 = this.bankAccountRepository.findByAccountNumEquals(1111111111);
            BankAccount bankAccount2 = this.bankAccountRepository.findByAccountNumEquals(1111111112);
            this.transactionRepository.save(new Transaction((long) 999, "Shopping", bankAccount1, bankAccount2, (long)900, CType.Macedonian_Denar, LocalDateTime.now()));
            this.transactionRepository.save(new Transaction((long) 999, "New Computer", bankAccount1, bankAccount2, (long)45000, CType.Macedonian_Denar, LocalDateTime.now()));
            this.transactionRepository.save(new Transaction((long) 999, "For Dinner", bankAccount2, bankAccount1, (long)1100, CType.Macedonian_Denar, LocalDateTime.now()));
            this.transactionRepository.save(new Transaction((long) 999, "Ice Cream", bankAccount1, bankAccount2, (long)200, CType.Macedonian_Denar, LocalDateTime.now()));
            this.transactionRepository.save(new Transaction((long) 999, "For New Keyboard", bankAccount2, bankAccount1, (long)1600, CType.Macedonian_Denar, LocalDateTime.now()));
        }
    }
}

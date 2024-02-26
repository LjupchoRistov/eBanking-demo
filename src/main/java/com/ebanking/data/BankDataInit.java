package com.ebanking.data;

import com.ebanking.models.BankAccount;
import com.ebanking.models.Role;
import com.ebanking.models.Transaction;
import com.ebanking.models.UserEntity;
import com.ebanking.repository.RoleRepository;
import com.ebanking.repository.TransactionRepository;
import com.ebanking.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class BankDataInit {

    private final UserRepository userRepository;
    private final BankAccount bankAccount;
    private final TransactionRepository transactionRepository;
    private final RoleRepository roleRepository;

    public BankDataInit(UserRepository userRepository, BankAccount bankAccount, TransactionRepository transactionRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bankAccount = bankAccount;
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

        if ()
    }
}

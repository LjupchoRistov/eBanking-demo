package com.ebanking.service.impl;

import com.ebanking.dto.BankAccountDto;
import com.ebanking.mapper.BankAccountMapper;
import com.ebanking.models.BankAccount;
import com.ebanking.models.UserEntity;
import com.ebanking.repository.BankAccountRepository;
import com.ebanking.repository.TransactionRepository;
import com.ebanking.repository.UserRepository;
import com.ebanking.service.BankAccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import static com.ebanking.mapper.BankAccountMapper.*;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    private static final AtomicLong counter = new AtomicLong(0);

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<BankAccountDto> findBankAccountsByUser(UserEntity user) {
        List<BankAccount> bankAccounts = this.bankAccountRepository.findAllByUserEquals(user);

        return bankAccounts.stream().map(BankAccountMapper::mapToBankAccountDto).collect(Collectors.toList());
    }

    @Override
    public BankAccountDto findBankAccountById(Long id) {
        return this.bankAccountRepository.findById(id).map(BankAccountMapper::mapToBankAccountDto).get();
    }

    @Override
    public BankAccountDto createBankAccount(Long id) {
        UserEntity user = this.userRepository.findById(id).get();
        // generate account number

        return null;
    }
}

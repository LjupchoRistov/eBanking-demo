package com.ebanking.service.impl;

import com.ebanking.dto.BankAccountDto;
import com.ebanking.dto.TransactionDto;
import com.ebanking.mapper.TransactionMappper;
import com.ebanking.models.BankAccount;
import com.ebanking.models.CurrencyType;
import com.ebanking.models.Transaction;
import com.ebanking.repository.BankAccountRepository;
import com.ebanking.repository.CurrencyTypeRepository;
import com.ebanking.repository.TransactionRepository;
import com.ebanking.repository.UserRepository;
import com.ebanking.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ebanking.mapper.BankAccountMapper.*;
import static com.ebanking.mapper.TransactionMappper.*;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;
    private final CurrencyTypeRepository currencyTypeRepository;

    public TransactionServiceImpl(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository, CurrencyTypeRepository currencyTypeRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
        this.currencyTypeRepository = currencyTypeRepository;
    }

    @Override
    public List<TransactionDto> findAllByBankAccount(BankAccountDto bankAccountDto) {
        List<Transaction> sender = this.transactionRepository.findAllBySenderEquals(mapToBankAccount(bankAccountDto));
        List<Transaction> receiver = this.transactionRepository.findAllByReceiverEquals(mapToBankAccount(bankAccountDto));

        List<Transaction> allTransactions = new ArrayList<>();
        allTransactions.addAll(sender);
        allTransactions.addAll(receiver);

        return allTransactions.stream().map(TransactionMappper::mapToTransactionDto).collect(Collectors.toList());
    }

    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        BankAccount senderAcc = mapToBankAccount(transactionDto.getSender());
        BankAccount receiverAcc = this.bankAccountRepository.findByAccountNumEquals(Integer.valueOf(transactionDto.getReceiver()));

        CurrencyType currencyTypeSender = senderAcc.getCurrencyType();
        CurrencyType currencyTypeReceiver = receiverAcc.getCurrencyType();

        if (currencyTypeSender == null || currencyTypeReceiver == null) {
            throw new IllegalArgumentException("Currency types are null for sender or receiver");
        }

        // Calculate conversion rate
        double amountDouble = Double.parseDouble(transactionDto.getAmount());
        double rate = currencyTypeSender.getValue() / currencyTypeReceiver.getValue();
        Double convertedAmount = amountDouble * rate;

        // Deduct amount from sender's balance
        if (!senderAcc.canSubstractAmount(convertedAmount)) {
            // Handle the case where sender doesn't have enough balance, perhaps by throwing an exception or returning an error
            // For now, let's throw an IllegalArgumentException
            throw new IllegalArgumentException("Sender doesn't have enough balance");
        }
        senderAcc.substractAmount(convertedAmount);

        // Add amount to receiver's balance
        receiverAcc.addAmount(convertedAmount);

        // Create and save the transaction
        Transaction transaction = new Transaction();
        transaction.setSender(senderAcc);
        transaction.setReceiver(receiverAcc);
        transaction.setCurrencyTypeSender(senderAcc.getCurrencyType());
        transaction.setAmount(amountDouble);
        transaction.setDescription(transactionDto.getDescription());
        return mapToTransactionDto(this.transactionRepository.save(transaction));
    }
}

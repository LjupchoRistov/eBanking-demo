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
    public TransactionDto createTransaction(String senderNum, String receiverNum, String currencyTypeSenderId, String description, String amount) {
        BankAccount senderAcc = this.bankAccountRepository.findByAccountNumEquals(Integer.parseInt(senderNum));
        BankAccount receiverAcc = this.bankAccountRepository.findByAccountNumEquals(Integer.parseInt(receiverNum));
        CurrencyType currencyTypeSender = senderAcc.getCurrencyType();
        CurrencyType currencyTypeReceiver = receiverAcc.getCurrencyType();

        double rate = (double) (currencyTypeSender.getValue() / currencyTypeReceiver.getValue());
        Double amountToAdd = Double.parseDouble(amount) * rate;

        this.transactionRepository.save(new Transaction((long) 999, description, senderAcc, receiverAcc, amountToAdd, currencyTypeReceiver, LocalDateTime.now()));

        return null;
    }
}

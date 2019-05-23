package com.urvin.service;

import com.urvin.entity.Account;
import com.urvin.entity.AccountTransaction;
import com.urvin.model.Transaction;
import com.urvin.model.TransactionType;
import com.urvin.repository.AccountRepository;
import com.urvin.repository.AccountTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountTransactionRepository accountTransactionRepository;

    public Account addAccount(Long custId) {
        if(custId != null) {
            Account account = new Account();
            account.setCustId(custId);
            account.setBalance(0.0);
            return accountRepository.save(account);
        }
        return null;
    }

    public Account getAccountDetails(Long custId) {
        return accountRepository.findByCustId(custId);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public String accountTransaction(Transaction transaction) {
        Account account = accountRepository.findById(transaction.getAccountId()).get();
        if(account != null) {
            Double balanceAfterTxn=0.0;
            AccountTransaction accountTransaction = new AccountTransaction();
            accountTransaction.setAccountId(transaction.getAccountId());
            accountTransaction.setTransactionType(transaction.getTransactionType());
            accountTransaction.setBalanceBeforeTxn(account.getBalance());
            accountTransaction.setTxnAmount(transaction.getTxnAmount());
            if (TransactionType.DEPOSIT.equals(transaction.getTransactionType())) {
                balanceAfterTxn = (account.getBalance()+transaction.getTxnAmount());
            } else if(TransactionType.WITHDRAW.equals(transaction.getTransactionType())) {
                balanceAfterTxn = (account.getBalance()-transaction.getTxnAmount());
            }
            accountTransaction.setBalanceAfterTxn(balanceAfterTxn);
            accountTransactionRepository.save(accountTransaction);
            account.setBalance(balanceAfterTxn);
            accountRepository.save(account);
            return "Transaction completed successfully";
        }
        return "Account doesn't exist";
    }
}

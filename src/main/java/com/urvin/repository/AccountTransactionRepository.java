package com.urvin.repository;

import com.urvin.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,Long> {
    public List<AccountTransaction> findByAccountId(Long accountId);
}

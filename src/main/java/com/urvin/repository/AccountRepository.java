package com.urvin.repository;

import com.urvin.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByCustId(Long custId);
}

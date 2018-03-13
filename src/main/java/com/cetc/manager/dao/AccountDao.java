package com.cetc.manager.dao;

import com.cetc.manager.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao  extends JpaRepository<Account, Integer>{
}

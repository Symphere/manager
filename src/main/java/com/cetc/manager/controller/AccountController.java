package com.cetc.manager.controller;

import com.cetc.manager.dao.AccountDao;
import com.cetc.manager.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountDao accountDao;

    @RequestMapping(value = "/list")
    public List<Account> getAccounts(){
        return accountDao.findAll();
    }
}

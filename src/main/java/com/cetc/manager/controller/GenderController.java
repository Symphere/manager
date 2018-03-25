package com.cetc.manager.controller;


import com.cetc.manager.dao.GenderDao;
import com.cetc.manager.entity.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gender")
public class GenderController {
    @Autowired
    GenderDao genderDao;

    @RequestMapping("/all")
    public List<Gender> all(){
        return genderDao.findAll();
    }
}

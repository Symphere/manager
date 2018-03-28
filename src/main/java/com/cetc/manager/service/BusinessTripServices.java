package com.cetc.manager.service;

import com.cetc.manager.dao.BusinessTripDao;
import com.cetc.manager.dao.EmployeeDao;
import com.cetc.manager.entity.BusinessTrip;
import com.cetc.manager.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class BusinessTripServices {

    @Autowired
    private BusinessTripDao businessTripDao;

    @Autowired
    private EmployeeDao employeeDao;
}

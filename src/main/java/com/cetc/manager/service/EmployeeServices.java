package com.cetc.manager.service;

import com.cetc.manager.dao.EmployeeDao;
import com.cetc.manager.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServices {
    @Autowired
    private EmployeeDao employeeDao;

    public Map<String, String> getJobNumberAndName(){
        Map<String, String> map = new HashMap<>();
        List<Employee> employees = employeeDao.findAll();
        for (Employee employee: employees){
            map.put(employee.getJobNumber(), employee.getName());
        }
        return map;
    }

    public String getNameByJobNumber(String jobNumber){
        Employee employee = employeeDao.findByJobNumber(jobNumber);
        if (employee != null){
            return employee.getName();
        }
        return null;
    }
}

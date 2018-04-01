package com.cetc.manager.service.impl;

import com.cetc.manager.dao.EmployeeDao;
import com.cetc.manager.entity.Employee;
import com.cetc.manager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeDao employeeDao;

    public Map<String, String> getJobNumberAndName(){
        List<Employee> employees = employeeDao.findAll();
        Map<String, String> result = new HashMap<>();

        for(Employee employee: employees){
            result.put(employee.getJobNumber(), employee.getName());
        }

        return result;
    }

    /**
    * @Description: 根据姓名查找工号
    * @Param：
    * @return: 
    * @Auther: Symphere
    * @Date: 2018/3/30
    **/
    public List<String> getJobNumberByName(String name){
        List<String> jobNumber = new ArrayList<>();
        List<Employee> employees = employeeDao.findByName(name);
        for(Employee employee: employees){
            jobNumber.add(employee.getJobNumber());
        }
        return jobNumber;
    }
}

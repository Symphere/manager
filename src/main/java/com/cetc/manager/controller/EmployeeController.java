package com.cetc.manager.controller;

import com.cetc.manager.common.Md5Util;
import com.cetc.manager.common.MyUUID;
import com.cetc.manager.dao.EmployeeDao;
import com.cetc.manager.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    // 删除employ实体中的秘密信息: 密码
    private static Employee eraseSecurity(Employee e){
        e.setPassword("");
        return e;
    }

    private static List<Employee> eraseSecurity(List<Employee> es){
        List<Employee> tmp = new ArrayList<>();
        for (Employee e:es ){
            e = eraseSecurity(e);
            tmp.add(e);
        }
        return tmp;
    }

    @RequestMapping("/all")
    public List<Employee> findAll(){
        return eraseSecurity(employeeDao.findAll());
    }

    @RequestMapping("/login")
    public Employee login(@RequestParam("jobNumber") String jobNumber,@RequestParam("passwordMD5")String passwordMD5){
        return eraseSecurity(employeeDao.findByJobNumberAndPasswordMD5(jobNumber, passwordMD5));
    }

    @RequestMapping("/regist")
    public Employee regist(@RequestParam("jobNumber") String jobNumber,
                         @RequestParam("familyName")String familyName,
                         @RequestParam("firstName")String firstName,
                         @RequestParam("passwordMD5")String passwordMD5,
                         @RequestParam(value = "gender" ,required = false,defaultValue="0")int gender,
                         @RequestParam("password")String password,
                         @RequestParam(value = "position",required = false)String position){
        // 检查工号是否已被注册
        if(employeeDao.findByJobNumber(jobNumber) != null){
            return null;
        }
        // 密码校验
        if(passwordMD5.equals(Md5Util.getMd5(password))){
            return null;
        }

        Employee employee = new Employee();
        employee.setId(MyUUID.getUUID());
        employee.setJobNumber(jobNumber);
        employee.setFamilyName(familyName);
        employee.setFirstName(firstName);
        employee.setgender(gender);
        employee.setPassword(password);
        employee.setPasswordMD5(passwordMD5);
        employee.setPosition(position);

        employeeDao.save(employee);
        return eraseSecurity(employee);
    }

    @RequestMapping("/findByJobNumber")
    public Employee findByJobNumber(@RequestParam("jobNumber")String jobNumber){
        return eraseSecurity(employeeDao.findByJobNumber(jobNumber));
    }

    @RequestMapping("/isRegist")
    public boolean isRegist(@RequestParam("jobNumber")String jobNumber){
        return employeeDao.findByJobNumber(jobNumber) != null ;
    }
}

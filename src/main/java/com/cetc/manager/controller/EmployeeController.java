package com.cetc.manager.controller;

import com.cetc.manager.common.Mapping;
import com.cetc.manager.common.Md5Util;
import com.cetc.manager.common.MyUUID;
import com.cetc.manager.dao.EmployeeDao;
import com.cetc.manager.entity.Employee;
import com.cetc.manager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @Resource(name = "employeeService")
    EmployeeService employeeService;

    // 删除employ实体中的秘密信息: 密码
    private static Employee eraseSecurity(Employee e) {
        if (e == null) {
            return null;
        }
        e.setPassword("");
        return e;
    }

    private static List<Employee> eraseSecurity(List<Employee> es) {
        if (es.isEmpty()) return null;
        List<Employee> tmp = new ArrayList<>();
        for (Employee e : es) {
            e = eraseSecurity(e);
            tmp.add(e);
        }
        return tmp;
    }

    @RequestMapping("/all")
    public Map<String, Object> findAll() {
        List<Employee> employees = eraseSecurity(employeeDao.findAll());
        if (employees.isEmpty()) {
            return Mapping.map(Mapping.NOT_FOUND, null);
        }
        return Mapping.mapSuccess(employees);
    }

    @RequestMapping("/login")
    public Map<String, Object> login(@RequestParam("jobNumber") String jobNumber, @RequestParam("password") String passwordMD5) {
//        return eraseSecurity(employeeDao.findByJobNumberAndPasswordMD5(jobNumber, passwordMD5));
        Employee employee = eraseSecurity(employeeDao.findByJobNumberAndPassword(jobNumber, passwordMD5));
        if (employee == null) {
            return Mapping.map(Mapping.LOGIN_FAILED, null);
        }
        return Mapping.mapSuccess(employee);
    }

    @RequestMapping("/regist")
    public Map<String, Object> regist(@RequestParam("jobNumber") String jobNumber,
                                      @RequestParam("name") String name,
                                      @RequestParam(value = "passwordMD5", required = false, defaultValue = "") String passwordMD5,
                                      @RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
                                      @RequestParam("password") String password,
                                      @RequestParam(value = "position", required = false) String position) {
        // 检查工号是否已被注册
        if (employeeDao.findByJobNumber(jobNumber) != null) {
            return Mapping.map(Mapping.ALREADY_REGISTERED, null);
        }

        passwordMD5 = Md5Util.getMd5(password);
        // 密码校验
//        if(passwordMD5.equals(Md5Util.getMd5(password))){
//            return null;
//        }

        Employee employee = new Employee();
        employee.setId(MyUUID.getUUID());
        employee.setJobNumber(jobNumber);
        employee.setName(name);
        employee.setGender(gender);
        employee.setPassword(password);
        employee.setPasswordMD5(passwordMD5);
        employee.setPosition(position);

        employeeDao.save(employee);
        return Mapping.mapSuccess(eraseSecurity(employee));
    }

    @RequestMapping("/findByJobNumber")
    public Map<String, Object> findByJobNumber(@RequestParam("jobNumber") String jobNumber) {
        Employee employee = eraseSecurity(employeeDao.findByJobNumber(jobNumber));
        if (employee == null) {
            return Mapping.map(Mapping.NOT_FOUND, null);
        }
        return Mapping.mapSuccess(employee);
    }

    @RequestMapping("/isRegist")
    public Map<String, Object> isRegist(@RequestParam("jobNumber") String jobNumber) {
        return Mapping.map(0, "查询成功", employeeDao.findByJobNumber(jobNumber) != null);
    }

    @RequestMapping("/getEmployeeList")
    public Map<String, Object> getEmployeeList() {
        return Mapping.map(0, "查询成功", employeeService.getJobNumberAndName());
    }

    // 可以直接往后台传输bean
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Map<String, Object> test(@RequestBody Employee employee) {
        System.out.println(employee);
        return null;
    }
}

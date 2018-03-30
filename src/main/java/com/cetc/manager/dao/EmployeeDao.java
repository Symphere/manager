package com.cetc.manager.dao;

import com.cetc.manager.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDao extends JpaRepository<Employee, String> {
    Employee findByJobNumberAndPasswordMD5(String jobNumber,String passwordMD5);

    Employee findByJobNumberAndPassword(String jobNumber,String password);

    Employee findByJobNumber(String jobNumber);

    List<Employee> findByName(String Name);
}

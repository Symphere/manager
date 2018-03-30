package com.cetc.manager.service;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    /*
     * 获取<工号，姓名>哈希表
     * */
    Map<String, String> getJobNumberAndName();

    List<String> getJobNumberByName(String name);
}

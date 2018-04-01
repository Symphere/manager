package com.cetc.manager.service.impl;

import com.cetc.manager.dao.WorkLogDao;
import com.cetc.manager.entity.WorkLog;
import com.cetc.manager.entity.WorkLogVO;
import com.cetc.manager.service.EmployeeService;
import com.cetc.manager.service.WorkLogService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: manager
 * @deccription:
 * @author: Symphere
 * @create: 2018-04-01
 **/

@Component("workLogService")
public class WorkLogServiceImpl implements WorkLogService {

    @Autowired
    WorkLogDao workLogDao;

    @Resource(name = "employeeService")
    EmployeeService employeeService;

    Mapper mapper = new DozerBeanMapper();



    List<WorkLogVO> mapDoToVo(List<WorkLog> workLogs){
        List<WorkLogVO> workLogVOS = new ArrayList<>();
        Map<String, String> employees = employeeService.getJobNumberAndName();
        for(WorkLog workLog: workLogs){
            WorkLogVO workLogVO = mapper.map(workLog, WorkLogVO.class);
            workLogVO.setName(employees.get(workLog.getJobNumber()));
            workLogVOS.add(workLogVO);
        }
        return workLogVOS;
    }

    WorkLogVO mapDoToVo(WorkLog workLog){
        Map<String, String> employees = employeeService.getJobNumberAndName();
        WorkLogVO workLogVO = mapper.map(workLog, WorkLogVO.class);
        workLogVO.setName(employees.get(workLog.getJobNumber()));
        return workLogVO;
    }

    @Override
    public List<WorkLogVO> findAll() {
        return mapDoToVo(workLogDao.findAll());
    }

    @Override
    public WorkLogVO findWithId(String id) {
        return mapDoToVo(workLogDao.findWithId(id));
    }

    @Override
    public List<WorkLogVO> findByJobNumber(String jobNumber) {
        return mapDoToVo(workLogDao.findByJobNumber(jobNumber));
    }

    @Override
    public boolean saveAndFlush(WorkLog workLog) {
        try{
            workLogDao.saveAndFlush(workLog);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

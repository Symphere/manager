package com.cetc.manager.controller;

import com.cetc.manager.common.Mapping;
import com.cetc.manager.common.MyUUID;
import com.cetc.manager.dao.WorkLogDao;
import com.cetc.manager.entity.WorkLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workLog")
public class WorkLogController {
    @Autowired
    WorkLogDao workLogDao;

    @RequestMapping("/add")
    public Map<String, Object> add(@RequestParam(value = "id", required = false, defaultValue = "") String id,
                                   @RequestParam("jobNumber") String jobNumber,
                                   @RequestParam("fillingTime") Timestamp fillingTime, // 记录时间
                                   @RequestParam("startTime") Timestamp startTime, // 开始时间
                                   @RequestParam("endTime") Timestamp endTime,  // 本周结束时间
                                   @RequestParam("workContent") String workContent, // 本周工作
                                   @RequestParam("workPlan") String workPlan, // 下周计划
                                   @RequestParam(value = "remark",required = false, defaultValue = "") String remark, // 备注
                                   @RequestParam(value = "coordinate", required = false, defaultValue = "") String coordinate ){
        WorkLog workLog = new WorkLog();
        workLog.setId(id.equals("")?MyUUID.getUUID():id);
        workLog.setJobNumber(jobNumber);
        workLog.setFillingTime(fillingTime);
        workLog.setStartTime(startTime);
        workLog.setEndTime(endTime);
        workLog.setWorkContent(workContent);
        workLog.setWorkPlan(workPlan);
        workLog.setRemark(remark);
        workLog.setCoordinate(coordinate);
        try{
            workLogDao.saveAndFlush(workLog);
            return Mapping.map(0,"success",true);
        } catch (Exception e){
            return Mapping.map(0,"success",false);
        }
    }

    @RequestMapping("/findByJobNumber")
    public Map<String, Object> findByJobNumber(@RequestParam("jobNumber") String jobNumber){
        System.out.println("jobNumber: "+jobNumber);
        return Mapping.map(0,"success",workLogDao.findByJobNumber(jobNumber));
    }

    @RequestMapping("/findById")
    public Map<String, Object> findById(@RequestParam("id")String id){
        return Mapping.map(0,"success",workLogDao.findWithId(id));
    }

    @RequestMapping("/all")
    public Map<String, Object> findAll(){
        return Mapping.map(0,"success",workLogDao.findAll());
    }
}

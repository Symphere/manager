package com.cetc.manager.controller;

import com.cetc.manager.common.Mapping;
import com.cetc.manager.common.MyUUID;
import com.cetc.manager.dao.VocationDao;
import com.cetc.manager.entity.Vocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vocation")
public class VocationController {

    @Autowired
    VocationDao vocationDao;

    @RequestMapping("/add")
    public Map<String, Object> add(@RequestParam(value="id", required = false, defaultValue = "") String id,
                                   @RequestParam(value="jobNumber") String jobNumber,
                                   @RequestParam(value="reason")  String reason,
                                   @RequestParam(value="approvalJobNumber")  String approvalJobNumber, // 批准人
                                   @RequestParam(value="startTime")  Timestamp startTime,
                                   @RequestParam(value="endTime")  Timestamp endTime,
                                   @RequestParam(value="fillingTime")  Timestamp fillingTime, // 填表时间
                                   @RequestParam(value="vocationDay")  double vocationDay, // 请假时长
                                   @RequestParam(value="type")  String type /*请假类型*/){
        Vocation vocation = new Vocation();
        vocation.setId(id.equals("")?MyUUID.getUUID():id);
        vocation.setJobNumber(jobNumber);
        vocation.setReason(reason);
        vocation.setApprovalJobNumber(approvalJobNumber);
        vocation.setStartTime(startTime);
        vocation.setEndTime(endTime);
        vocation.setFillingTime(fillingTime);
        vocation.setVocationDay(vocationDay);
        vocation.setType(type);
        try{
            vocationDao.saveAndFlush(vocation);
            return Mapping.map(0,"success",true);
        } catch (Exception e){
            return Mapping.map(0,"success",false);
        }
    }

    @RequestMapping("/findById")
    public Map<String, Object> findById(String id){
        return Mapping.map(0,"success", vocationDao.findWithId(id));
    }

    @RequestMapping("/findByJobNumber")
    public Map<String, Object> findByJobNumber(String jobNumber){
        return Mapping.map(0,"success",vocationDao.findByJobNumber(jobNumber));
    }

    @RequestMapping("/all")
    public Map<String, Object> findAll(){
        return Mapping.map(0,"success",vocationDao.findAll());
    }
}


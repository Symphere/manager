package com.cetc.manager.controller;

import com.cetc.manager.common.Mapping;
import com.cetc.manager.common.MyUUID;
import com.cetc.manager.dao.BusinessTripDao;
import com.cetc.manager.entity.BusinessTrip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/businessTrip")
public class BusinessTripController {

    @Autowired
    BusinessTripDao businessTripDao;

    @RequestMapping("/add")
    public Map<String, Object> add(@RequestParam(value = "id", required = false, defaultValue = "") String id,
                                   @RequestParam("jobNumber") String jobNumber,
                                   @RequestParam("fillingTime") Timestamp fillingTime, // 记录时间
                                   @RequestParam("lastChangeTime") Timestamp lastChangeTime, // 最后一次修改时间
                                   @RequestParam("mission")  String mission, // 出差任务
                                   @RequestParam("destination")  String destination,// 出差地
                                   @RequestParam("startTime") Timestamp startTime, // 开始时间
                                   @RequestParam(value = "planEndTime",required = false, defaultValue = "2018-01-01 00:00:00") Timestamp planEndTime, // 预计结束时间
                                   @RequestParam(value = "realEndTime", required = false, defaultValue = "2018-01-01 00:00:00") Timestamp realEndTime, // 实际结束时间
                                   @RequestParam("approvalJobNumber") String approvalJobNumber){ // 批准人
        BusinessTrip businessTrip = new BusinessTrip();
        businessTrip.setId(id.equals("")?MyUUID.getUUID():id);
        businessTrip.setStartTime(startTime);
        businessTrip.setJobNumber(jobNumber);
        businessTrip.setFillingTime(fillingTime);
        businessTrip.setLastChangeTime(lastChangeTime);
        businessTrip.setMission(mission);
        businessTrip.setDestination(destination);
        businessTrip.setPlanEndTime(planEndTime);
        businessTrip.setRealEndTime(realEndTime);
        businessTrip.setApprovalJobNumber(approvalJobNumber);
        try{
            businessTripDao.saveAndFlush(businessTrip);
            return Mapping.map(0,"success", true);
        } catch (Exception e){
            return Mapping.map(0,"success", false);
        }
    }

    @RequestMapping("/findAllByJobNumber")
    public Map<String, Object> findByJobNumber(@RequestParam("jobNumber") String jobNumber){
        System.out.println("jobNumber: "+jobNumber);
        return Mapping.map(0,"success",businessTripDao.findByJobNumber(jobNumber));
    }

    @RequestMapping("/findById")
    public Map<String, Object> findById(@RequestParam("id")String id){
        return Mapping.map(0,"success",businessTripDao.findWithId(id));
    }

    @RequestMapping("/all")
    public Map<String, Object> findAll(){
        return Mapping.map(0,"success",businessTripDao.findAll());
    }
}

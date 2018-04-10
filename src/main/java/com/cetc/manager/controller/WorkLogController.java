package com.cetc.manager.controller;

import com.cetc.manager.common.Const;
import com.cetc.manager.common.Mapping;
import com.cetc.manager.common.MyUUID;
import com.cetc.manager.entity.WorkLog;
import com.cetc.manager.entity.WorkLogVO;
import com.cetc.manager.service.WorkLogService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workLog")
public class WorkLogController {

    @Resource(name = "workLogService")
    WorkLogService workLogService;

    @RequestMapping("/add")
    public Map<String, Object> add(@RequestParam(value = "id", required = false, defaultValue = "") String id,
                                   @RequestParam("jobNumber") String jobNumber,
                                   @RequestParam("fillingTime") Timestamp fillingTime, // 记录时间
                                   @RequestParam("startTime") Date startTime, // 开始时间
                                   @RequestParam("endTime") Date endTime,  // 本周结束时间
                                   @RequestParam("workContent") String workContent, // 本周工作
                                   @RequestParam("workPlan") String workPlan, // 下周计划
                                   @RequestParam(value = "remark", required = false, defaultValue = "") String remark, // 备注
                                   @RequestParam(value = "coordinate", required = false, defaultValue = "") String coordinate) {
        WorkLog workLog = new WorkLog();
        workLog.setId(id.equals("") ? MyUUID.getUUID() : id);
        workLog.setJobNumber(jobNumber);
        workLog.setFillingTime(fillingTime);
        workLog.setStartTime(startTime);
        workLog.setEndTime(endTime);
        workLog.setWorkContent(workContent);
        workLog.setWorkPlan(workPlan);
        workLog.setRemark(remark);
        workLog.setCoordinate(coordinate);
        if (workLogService.saveAndFlush(workLog)) {
            return Mapping.map(0, "success", true);
        } else {
            return Mapping.map(1, "failure", false);
        }
    }

    @RequestMapping("/findByJobNumber")
    public Map<String, Object> findByJobNumber(@RequestParam("jobNumber") String jobNumber) {
        System.out.println("jobNumber: " + jobNumber);
        List<WorkLogVO> workLogVOS = workLogService.findByJobNumber(jobNumber);
        if (workLogVOS.isEmpty()) {
            return Mapping.map(1, "not found", null);
        }
        return Mapping.map(0, "success", workLogVOS);
    }

    @RequestMapping("/findById")
    public Map<String, Object> findById(@RequestParam("id") String id) {
        WorkLogVO workLogVO = workLogService.findWithId(id);
        if (workLogVO == null) {
            return Mapping.map(1, "not found", null);
        }
        return Mapping.map(0, "success", workLogVO);
    }

    @RequestMapping("/all")
    public Map<String, Object> findAll() {
        List<WorkLogVO> workLogVOS = workLogService.findAll();
        if (workLogVOS.isEmpty()) {
            return Mapping.map(1, "not found", null);
        }
        return Mapping.map(0, "success", workLogVOS);
    }

    @RequestMapping("/search")
    public Map<String, Object> search(@RequestParam(value = "name", defaultValue = "") String name,
                                      @RequestParam(value = "startTime", defaultValue = "1970-01-01") Date startTime,
                                      @RequestParam(value = "endTime", defaultValue = "1970-01-01") Date endTime) {
//        return Mapping.map("0", "查询成功", businessTripServices.search(name, destination, startTime, endTime, approvalName));
        if (Const.DEFAULT_DATE.equals(startTime)) {
            startTime = null;
        }
        if (Const.DEFAULT_DATE.equals(endTime)) {
            endTime = null;
        }
        List<WorkLogVO> workLogVOS = workLogService.search(name, startTime, endTime);
        if (workLogVOS.isEmpty()) {
            return Mapping.map(1, "not found", null);
        }
        return Mapping.map(0, "success", workLogVOS);
    }
}

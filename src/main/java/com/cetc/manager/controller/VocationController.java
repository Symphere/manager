package com.cetc.manager.controller;

import com.cetc.manager.common.Mapping;
import com.cetc.manager.common.MyUUID;
import com.cetc.manager.dao.VocationDao;
import com.cetc.manager.entity.Vocation;
import com.cetc.manager.service.VocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vocation")
public class VocationController {

    @Resource(name="vocationService")
    VocationService vocationService;

    @RequestMapping("/add")
    /**
    * @Description: 添加请假表
    * @Param：[id, jobNumber, reason, approvalJobNumber, startTime, endTime, fillingTime, vocationDay, type]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Auther: Symphere
    * @Date: 2018/4/1
    **/
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
        if(vocationService.saveAndFlush(vocation)){
            return Mapping.map(0,"success",true);
        } else {
            return Mapping.map(1,"存储失败",false);
        }
    }

    @RequestMapping("/findById")
    /**
    * @Description: 根据Id查找 唯一
    * @Param：[id]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Auther: Symphere
    * @Date: 2018/4/1
    **/
    public Map<String, Object> findById(String id){
        return Mapping.map(0,"success", vocationService.findWithId(id));
    }

    @RequestMapping("/findByJobNumber")
    /**
    * @Description: 根据工号查找
    * @Param：[jobNumber]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Auther: Symphere
    * @Date: 2018/4/1
    **/
    public Map<String, Object> findByJobNumber(String jobNumber){
        return Mapping.map(0,"success",vocationService.findByJobNumber(jobNumber));
    }


    @RequestMapping("/all")
    /**
     * @Description: 获取所有的请假表
     * @Param：[]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Auther: Symphere
     * @Date: 2018/4/1
     **/
    public Map<String, Object> findAll(){
        return Mapping.map(0,"success",vocationService.findAll());
    }
}


package com.cetc.manager.controller;

import com.cetc.manager.common.Const;
import com.cetc.manager.common.Mapping;
import com.cetc.manager.common.MyUUID;
import com.cetc.manager.entity.BusinessTrip;
import com.cetc.manager.entity.BusinessTripVO;
import com.cetc.manager.service.BusinessTripServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/businessTrip")
public class BusinessTripController {

    @Resource(name="businessTripServices")
    private BusinessTripServices businessTripServices;

    /**
     * @Description: 添加出差表
     * @Param：[id, jobNumber, fillingTime, lastChangeTime, mission, destination, startTime, planEndTime, realEndTime, approvalJobNumber]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Auther: Symphere
     * @Date: 2018/3/30
     **/
    @RequestMapping("/add")
    public Map<String, Object> add(@RequestParam(value = "id", required = false, defaultValue = "") String id,
                                   @RequestParam("jobNumber") String jobNumber,
                                   @RequestParam("fillingTime") Timestamp fillingTime, // 记录时间
                                   @RequestParam("lastChangeTime") Timestamp lastChangeTime, // 最后一次修改时间
                                   @RequestParam("mission")  String mission, // 出差任务
                                   @RequestParam("destination")  String destination,// 出差地
                                   @RequestParam("startTime") Timestamp startTime, // 开始时间
                                   @RequestParam(value = "planEndTime",required = false, defaultValue = "1970-01-01 00:00:00") Timestamp planEndTime, // 预计结束时间
                                   @RequestParam(value = "realEndTime", required = false, defaultValue = "1970-01-01 00:00:00") Timestamp realEndTime, // 实际结束时间
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

        if(businessTripServices.saveAndFlush(businessTrip)){
            return Mapping.mapSuccess(true);
        } else {
            return Mapping.map(Mapping.SAVE_FAILURE, false);
        }
    }

    /**
     * @Description: 根据工号获取出差表信息
     * @Param：[jobNumber]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Auther: Symphere
     * @Date: 2018/3/30
     **/
    @RequestMapping("/findByJobNumber")
    public Map<String, Object> findByJobNumber(@RequestParam("jobNumber") String jobNumber){
        List<BusinessTripVO> businessTripVOS = businessTripServices.findAllByJobNumber(jobNumber);
        if(businessTripVOS.isEmpty()){
            return Mapping.map(Mapping.NOT_FOUND, null);
        }
        return Mapping.mapSuccess(businessTripVOS);
    }

    /**
     * @Description: 根据id获取出差表信息
     * @Param：[id]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Auther: Symphere
     * @Date: 2018/3/30
     **/
    @RequestMapping("/findById")
    public Map<String, Object> findById(@RequestParam("id")String id){
        BusinessTripVO businessTripVO = businessTripServices.findWithId(id);
        if(businessTripVO == null){
            return Mapping.map(Mapping.NOT_FOUND,null);
        }
        return Mapping.mapSuccess(businessTripVO);
    }

    @RequestMapping("/all")
    /**
    * @Description: 获取所有的出差表信息
    * @Param：[]
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    * @Auther: Symphere
    * @Date: 2018/3/30
    **/
    public Map<String, Object> findAll(){
        List<BusinessTripVO> businessTripVOList = businessTripServices.findAll();
        if(businessTripVOList.isEmpty()){
            return Mapping.map(Mapping.NOT_FOUND, null);
        }
        return Mapping.mapSuccess(businessTripVOList);
    }

    /**
     * @Description:
     * @Param：[name, destination, startTime, endTime, approvalName]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     * @Auther: Symphere
     * @Date: 2018/3/30
     **/
    @RequestMapping("/search")
    public Map<String, Object> search(@RequestParam(value="name",defaultValue = "") String name,
                                      @RequestParam(value="destination", defaultValue = "")String destination,
                                      @RequestParam(value = "startTime",defaultValue = "1970-01-01 00:00:00")Timestamp startTime,
                                      @RequestParam(value = "endTime",defaultValue = "1970-01-01 00:00:00")Timestamp endTime,
                                      @RequestParam(value="approvalName", defaultValue = "")String approvalName){
//        return Mapping.map("0", "查询成功", businessTripServices.search(name, destination, startTime, endTime, approvalName));
        if(Const.DEFAULT_TIMESTAMP.equals(startTime)){
            startTime = null;
        }
        if(Const.DEFAULT_TIMESTAMP.equals(endTime)){
            endTime = null;
        }
        List<BusinessTripVO> businessTripVOS = businessTripServices.search(name, destination, startTime, endTime, approvalName);
        if(businessTripVOS.isEmpty()){
            return Mapping.map(Mapping.NOT_FOUND,null);
        }
        return Mapping.mapSuccess(businessTripVOS);
    }
}

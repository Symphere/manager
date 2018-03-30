package com.cetc.manager.service;

import com.cetc.manager.entity.BusinessTrip;
import com.cetc.manager.entity.BusinessTripVO;

import java.sql.Timestamp;
import java.util.List;

public interface BusinessTripServices {

    List<BusinessTripVO> findAllByJobNumber(String jobNumber);

    List<BusinessTripVO> findAll();

    BusinessTripVO findWithId(String id);

    boolean saveAndFlush(BusinessTrip businessTrip);

    List<BusinessTripVO> search(String name, String destination, Timestamp startTime, Timestamp endTime, String approvalName);
}

package com.cetc.manager.service;

import com.cetc.manager.entity.WorkLog;
import com.cetc.manager.entity.WorkLogVO;

import java.util.List;

public interface WorkLogService {

    List<WorkLogVO> findAll();

    WorkLogVO findWithId(String id);

    List<WorkLogVO> findByJobNumber(String jobNumber);

    boolean saveAndFlush(WorkLog workLog);
}

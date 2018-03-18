package com.cetc.manager.dao;

import com.cetc.manager.entity.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkLogDao extends JpaRepository<WorkLog, String> {
}

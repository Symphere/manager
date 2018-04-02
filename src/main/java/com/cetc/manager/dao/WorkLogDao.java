package com.cetc.manager.dao;

import com.cetc.manager.entity.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkLogDao extends JpaRepository<WorkLog, String>,JpaSpecificationExecutor {

    List<WorkLog> findByJobNumber(String jobNumber);

    @Query(value = "select * from work_log where id=?1", nativeQuery = true)
    WorkLog findWithId(String id);
}

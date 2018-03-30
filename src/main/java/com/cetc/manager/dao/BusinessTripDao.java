package com.cetc.manager.dao;

import com.cetc.manager.entity.BusinessTrip;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BusinessTripDao extends JpaRepository<BusinessTrip, String>,JpaSpecificationExecutor {

    List<BusinessTrip> findByJobNumber(String jobNumber);


    @Query(value = "select * from business_trip where id=?1", nativeQuery = true)
    BusinessTrip findWithId(String id);
}

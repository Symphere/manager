package com.cetc.manager.dao;

import com.cetc.manager.entity.Vocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VocationDao extends JpaRepository<Vocation, String> {

    List<Vocation> findByJobNumber(String jobNumber);

    @Query(value = "select * from vocation where id=?1", nativeQuery = true)
    Vocation findWithId(String id);
}

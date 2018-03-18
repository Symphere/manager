package com.cetc.manager.dao;

import com.cetc.manager.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderDao extends JpaRepository<Gender, Integer> {
}

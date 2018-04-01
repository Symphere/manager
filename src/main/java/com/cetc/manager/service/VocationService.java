package com.cetc.manager.service;

import com.cetc.manager.entity.Vocation;
import com.cetc.manager.entity.VocationVO;

import java.util.List;

public interface VocationService {

    List<VocationVO> findAll();

    List<VocationVO> findByJobNumber(String jobNumber);

    VocationVO findWithId(String id);

    boolean saveAndFlush(Vocation vocation);
}

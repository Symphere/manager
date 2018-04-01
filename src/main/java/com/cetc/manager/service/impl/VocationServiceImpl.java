package com.cetc.manager.service.impl;

import com.cetc.manager.dao.VocationDao;
import com.cetc.manager.entity.Vocation;
import com.cetc.manager.entity.VocationVO;
import com.cetc.manager.service.EmployeeService;
import com.cetc.manager.service.VocationService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: manager
 * @deccription:
 * @author: Symphere
 * @create: 2018-04-01
 **/

@Component("vocationService")
public class VocationServiceImpl implements VocationService {

    @Autowired
    VocationDao vocationDao;

    @Resource(name="employeeService")
    EmployeeService employeeService;

    private Mapper mapper = new DozerBeanMapper();

    private List<VocationVO> mapDoToVo(List<Vocation> vocations){
        List<VocationVO> vocationVOS = new ArrayList<>();
        Map<String, String> employees = employeeService.getJobNumberAndName();
        for(Vocation vocation: vocations){
            VocationVO vocationVO = mapper.map(vocation, VocationVO.class);
            vocationVO.setApprovalName(employees.get(vocation.getApprovalJobNumber()));
            vocationVO.setName(employees.get(vocation.getJobNumber()));
            vocationVOS.add(vocationVO);
        }
        return vocationVOS;
    }

    private VocationVO mapDoToVo(Vocation vocation){
        Map<String, String> employees = employeeService.getJobNumberAndName();
        VocationVO vocationVO = mapper.map(vocation, VocationVO.class);
        vocationVO.setApprovalName(employees.get(vocation.getApprovalJobNumber()));
        vocationVO.setName(employees.get(vocation.getJobNumber()));
        return vocationVO;
    }

    public List<VocationVO> findAll(){
        List<Vocation> vocations = vocationDao.findAll();
        return mapDoToVo(vocations);
    }

    @Override
    public List<VocationVO> findByJobNumber(String jobNumber) {
        return mapDoToVo(vocationDao.findByJobNumber(jobNumber));
    }

    @Override
    public VocationVO findWithId(String id) {
        return mapDoToVo(vocationDao.findWithId(id));
    }

    @Override
    public boolean saveAndFlush(Vocation vocation) {
        try{
            vocationDao.saveAndFlush(vocation);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}

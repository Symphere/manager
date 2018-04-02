package com.cetc.manager.service.impl;

import com.cetc.manager.dao.VocationDao;
import com.cetc.manager.entity.Vocation;
import com.cetc.manager.entity.VocationVO;
import com.cetc.manager.service.EmployeeService;
import com.cetc.manager.service.VocationService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
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

    Mapper mapper = new DozerBeanMapper();

    List<VocationVO> mapDoToVo(List<Vocation> vocations){
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

    VocationVO mapDoToVo(Vocation vocation){
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

    @Override
    public List<VocationVO> search(String name, String type, Timestamp startTime, Timestamp endTime, String approvalName) {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                List<Predicate> predicatesOr1 = new ArrayList<>();
                if(null != name && !name.equals("") ) {
                    List<String> jobNumbers = employeeService.getJobNumberByName(name);
                    for(String jobNumber: jobNumbers){
                        predicatesOr1.add(criteriaBuilder.equal(root.get("jobNumber"), jobNumber));
                    }
                    predicates.add(criteriaBuilder.or(predicatesOr1.toArray(new Predicate[predicatesOr1.size()])));
                }
                List<Predicate> predicatesOr2 = new ArrayList<>();
                if(null != approvalName && !approvalName.equals("")){
                    List<String> jobNumbers = employeeService.getJobNumberByName(approvalName);
                    for(String jobNumber: jobNumbers){
                        predicatesOr2.add(criteriaBuilder.equal(root.get("approvalJobNumber"), jobNumber));
                    }
                    predicates.add(criteriaBuilder.or(predicatesOr2.toArray(new Predicate[predicatesOr2.size()])));
                }
                if(null != type && !type.equals("") ){
                    predicates.add(criteriaBuilder.equal(root.get("type"), type));
                }
                if(null != startTime){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime"), startTime));
                }
                if(null != endTime){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endTime"), endTime));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<Vocation> vocations = vocationDao.findAll(specification);
        return mapDoToVo(vocations);
    }
}

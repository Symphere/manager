package com.cetc.manager.service.impl;

import com.cetc.manager.dao.WorkLogDao;
import com.cetc.manager.entity.WorkLog;
import com.cetc.manager.entity.WorkLogVO;
import com.cetc.manager.service.EmployeeService;
import com.cetc.manager.service.WorkLogService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: manager
 * @deccription:
 * @author: Symphere
 * @create: 2018-04-01
 **/

@Component("workLogService")
public class WorkLogServiceImpl implements WorkLogService {

    @Autowired
    WorkLogDao workLogDao;

    @Resource(name = "employeeService")
    EmployeeService employeeService;

    Mapper mapper = new DozerBeanMapper();



    List<WorkLogVO> mapDoToVo(List<WorkLog> workLogs){
        List<WorkLogVO> workLogVOS = new ArrayList<>();
        Map<String, String> employees = employeeService.getJobNumberAndName();
        for(WorkLog workLog: workLogs){
            WorkLogVO workLogVO = mapper.map(workLog, WorkLogVO.class);
            workLogVO.setName(employees.get(workLog.getJobNumber()));
            workLogVOS.add(workLogVO);
        }
        return workLogVOS;
    }

    WorkLogVO mapDoToVo(WorkLog workLog){
        Map<String, String> employees = employeeService.getJobNumberAndName();
        WorkLogVO workLogVO = null;
        if(workLog !=  null){
            workLogVO = mapper.map(workLog, WorkLogVO.class);
            workLogVO.setName(employees.get(workLog.getJobNumber()));
        }
        return workLogVO;
    }

    @Override
    public List<WorkLogVO> findAll() {
        return mapDoToVo(workLogDao.findAll());
    }

    @Override
    public WorkLogVO findWithId(String id) {
        return mapDoToVo(workLogDao.findWithId(id));
    }

    @Override
    public List<WorkLogVO> findByJobNumber(String jobNumber) {
        return mapDoToVo(workLogDao.findByJobNumber(jobNumber));
    }

    @Override
    public boolean saveAndFlush(WorkLog workLog) {
        try{
            workLogDao.saveAndFlush(workLog);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<WorkLogVO> search(String name, Date startTime, Date endTime) {
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
                if(null != startTime){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime"), startTime));
                }
                if(null != endTime){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("endTime"), endTime));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<WorkLog> workLogs = workLogDao.findAll(specification);
        return mapDoToVo(workLogs);
    }
}

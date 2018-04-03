package com.cetc.manager.service.impl;

import com.cetc.manager.dao.BusinessTripDao;
import com.cetc.manager.entity.BusinessTrip;
import com.cetc.manager.entity.BusinessTripVO;
import com.cetc.manager.service.BusinessTripServices;
import com.cetc.manager.service.EmployeeService;
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

@Component("businessTripServices")
public class BusinessTripServicesImpl implements BusinessTripServices {

    @Resource(name = "employeeService")
    EmployeeService employeeService;

    @Autowired
    BusinessTripDao businessTripDao;

    Mapper mapper = new DozerBeanMapper();

    /**
     * @Description: 将BusinessTrip DO 转化成 VO
     * @Param：[businessTrips]
     * @return: List<BusinessTripVO>
     * @Auther: Symphere
     * @Date: 2018/3/30
     **/
    private List<BusinessTripVO> mapDoToVo(List<BusinessTrip> businessTrips){
        List<BusinessTripVO> businessTripVOS = new ArrayList<>();
        Map<String, String> employees = employeeService.getJobNumberAndName();
        for(BusinessTrip businessTrip: businessTrips){
            BusinessTripVO businessTripVO = mapper.map(businessTrip, BusinessTripVO.class);
            businessTripVO.setApprovalName(employees.get(businessTrip.getApprovalJobNumber()));
            businessTripVO.setName(employees.get(businessTrip.getJobNumber()));
            businessTripVOS.add(businessTripVO);
        }
        return businessTripVOS;
    }

    private BusinessTripVO mapDoToVo(BusinessTrip businessTrip){
        Map<String, String> employees = employeeService.getJobNumberAndName();
        BusinessTripVO businessTripVO = mapper.map(businessTrip, BusinessTripVO.class);
        businessTripVO.setApprovalName(employees.get(businessTrip.getApprovalJobNumber()));
        businessTripVO.setName(employees.get(businessTrip.getJobNumber()));
        return businessTripVO;
    }

    /**
    * @Description: 根据工号获取出差表
    * @Param：[jobNumber]
    * @return: List<BusinessTripVO>
    * @Auther: Symphere
    * @Date: 2018/3/30
    **/
    public List<BusinessTripVO> findAllByJobNumber(String jobNumber){
        List<BusinessTrip> businessTrips = businessTripDao.findByJobNumber(jobNumber);
        return mapDoToVo(businessTrips);
    }

    /**
     * @Description: 获取所有出差表
     * @Param：[]
     * @return: List<BusinessTripVO>
     * @Auther: Symphere
     * @Date: 2018/3/30
     **/
    @Override
    public List<BusinessTripVO> findAll() {
        List<BusinessTrip> businessTrips = businessTripDao.findAll();
        return mapDoToVo(businessTrips);
    }

    /**
     * @Description: 根据id取出差表
     * @Param：[]
     * @return: List<BusinessTripVO>
     * @Auther: Symphere
     * @Date: 2018/3/30
     **/
    @Override
    public BusinessTripVO findWithId(String id) {
        BusinessTrip businessTrip = businessTripDao.findWithId(id);
        return mapDoToVo(businessTrip);
    }

    /**
     * @Description: 根据条件查询出差表
     * @Param：[]
     * @return: List<BusinessTripVO>
     * @Auther: Symphere
     * @Date: 2018/3/30
     **/
    @Override
    public boolean saveAndFlush(BusinessTrip businessTrip){
        try{
            businessTripDao.saveAndFlush(businessTrip);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public List<BusinessTripVO> search(String name, String destination, Timestamp startTime, Timestamp endTime, String approvalName){
        System.out.println("destination: " + destination);
        Specification querySpecifi = new Specification() {
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
                if(null != destination && !destination.equals("") ){
                    predicates.add(criteriaBuilder.equal(root.get("destination"), destination));
                }
                if(null != startTime){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("startTime"), startTime));
                }
                if(null != endTime){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("realEndTime"), endTime));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        List<BusinessTrip> businessTrips = businessTripDao.findAll(querySpecifi);
        return mapDoToVo(businessTrips);
    }
}

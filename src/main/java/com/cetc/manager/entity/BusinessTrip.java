package com.cetc.manager.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "BusinessTrip")
public class BusinessTrip {
    @Id
    private String id;
    private String jobNumber;
    private Timestamp fillingTime; // 记录时间
    private Timestamp lastChangeTime; // 最后修改
    private String mission; // 出差任务
    private String destination;// 出差地
    private Timestamp startTime; // 开始时间
    private Timestamp planEndTime; // 预计结束时间
    private Timestamp realEndTime; // 实际结束时间
    private String approvalJobNumber; //批准人工号

    @Override
    public String toString() {
        return "BusinessTrip{" +
                "id='" + id + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", fillingTime=" + fillingTime +
                ", lastChangeTime=" + lastChangeTime +
                ", mission='" + mission + '\'' +
                ", destination='" + destination + '\'' +
                ", startTime=" + startTime +
                ", planEndTime=" + planEndTime +
                ", realEndTime=" + realEndTime +
                ", approvalJobNumber='" + approvalJobNumber + '\'' +
                '}';
    }

    public void setLastChangeTime(Timestamp lastChangeTime) {
        this.lastChangeTime = lastChangeTime;
    }

    public Timestamp getLastChangeTime() {

        return lastChangeTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setFillingTime(Timestamp fillingTime) {
        this.fillingTime = fillingTime;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public void setPlanEndTime(Timestamp planEndTime) {
        this.planEndTime = planEndTime;
    }

    public void setRealEndTime(Timestamp realEndTime) {
        this.realEndTime = realEndTime;
    }

    public void setApprovalJobNumber(String approvalJobNumber) {
        this.approvalJobNumber = approvalJobNumber;
    }

    public String getId() {

        return id;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public Timestamp getFillingTime() {
        return fillingTime;
    }

    public String getMission() {
        return mission;
    }

    public String getDestination() {
        return destination;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public Timestamp getPlanEndTime() {
        return planEndTime;
    }

    public Timestamp getRealEndTime() {
        return realEndTime;
    }

    public String getApprovalJobNumber() {
        return approvalJobNumber;
    }
}

package com.cetc.manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

public class BusinessTripVO {
    @Id
    private String id;
    private String jobNumber;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp fillingTime; // 记录时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp lastChangeTime; // 最后修改
    private String mission; // 出差任务
    private String destination;// 出差地

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp startTime; // 开始时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp planEndTime; // 预计结束时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp realEndTime; // 实际结束时间
    private String approvalJobNumber; //批准人工号
    private String name;
    private String approvalName;

    @Override
    public String toString() {
        return "BusinessTripVO{" +
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
                ", name='" + name + '\'' +
                ", approvalName='" + approvalName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Timestamp getFillingTime() {
        return fillingTime;
    }

    public void setFillingTime(Timestamp fillingTime) {
        this.fillingTime = fillingTime;
    }

    public Timestamp getLastChangeTime() {
        return lastChangeTime;
    }

    public void setLastChangeTime(Timestamp lastChangeTime) {
        this.lastChangeTime = lastChangeTime;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Timestamp planEndTime) {
        this.planEndTime = planEndTime;
    }

    public Timestamp getRealEndTime() {
        return realEndTime;
    }

    public void setRealEndTime(Timestamp realEndTime) {
        this.realEndTime = realEndTime;
    }

    public String getApprovalJobNumber() {
        return approvalJobNumber;
    }

    public void setApprovalJobNumber(String approvalJobNumber) {
        this.approvalJobNumber = approvalJobNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }
}

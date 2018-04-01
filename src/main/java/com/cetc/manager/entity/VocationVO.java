package com.cetc.manager.entity;
/*
 * 请假表实体
 */

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "Vocation")
public class VocationVO {

    @Id
    private String id;
    private String jobNumber;
    private String reason;
    private String approvalJobNumber; // 批准人

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp startTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp endTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp fillingTime; // 填表时间
    private double vocationDay; // 请假时长
    private String type; //请假类型
    private String name;
    private String approvalName;



    @Override
    public String toString() {
        return "VocationVO{" +
                "id='" + id + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", reason='" + reason + '\'' +
                ", approvalJobNumber='" + approvalJobNumber + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", fillingTime=" + fillingTime +
                ", vocationDay=" + vocationDay +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", approvalName='" + approvalName + '\'' +
                '}';
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApprovalJobNumber() {
        return approvalJobNumber;
    }

    public void setApprovalJobNumber(String approvalJobNumber) {
        this.approvalJobNumber = approvalJobNumber;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getFillingTime() {
        return fillingTime;
    }

    public void setFillingTime(Timestamp fillingTime) {
        this.fillingTime = fillingTime;
    }

    public double getVocationDay() {
        return vocationDay;
    }

    public void setVocationDay(double vocationDay) {
        this.vocationDay = vocationDay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

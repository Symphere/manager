package com.cetc.manager.entity;
/*
 * 请假表实体
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "Vocation")
public class Vocation {

    @Id
    private String id;
    private String jobNumber;
    private String reason;
    private String approvalJobNumber; // 批准人
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp fillingTime; // 填表时间
    private double vocationDay; // 请假时长

    public double getVocationDay() {
        return vocationDay;
    }

    public void setVocationDay(double vocationDay) {
        this.vocationDay = vocationDay;
    }

    @Override
    public String toString() {
        return "Vocation{" +
                "id='" + id + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", reason='" + reason + '\'' +
                ", approvalJobNumber='" + approvalJobNumber + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", fillingTime=" + fillingTime +
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
}

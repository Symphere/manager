package com.cetc.manager.entity;
/*
 * 工作日志实体类
 */
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name ="WorkLog")
public class WorkLog {
    @Id
    private String id;
    private String jobNumber;
    private Timestamp fillingTime;
    private Timestamp startTime; // 本周开始时间
    private Timestamp endTime;  // 本周结束时间
    private String workContent; // 本周工作
    private String workPlan; // 下周计划

    @Override
    public String toString() {
        return "WorkLog{" +
                "id='" + id + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", fillingTime=" + fillingTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", workContent='" + workContent + '\'' +
                ", workPlan='" + workPlan + '\'' +
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

    public String getWorkContent() {
        return workContent;
    }

    public void setWorkContent(String workContent) {
        this.workContent = workContent;
    }

    public String getWorkPlan() {
        return workPlan;
    }

    public void setWorkPlan(String workPlan) {
        this.workPlan = workPlan;
    }
}

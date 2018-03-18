package com.cetc.manager.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    private String id;
    private String jobNumber;
    private String familyName;
    private int gender;
    private String firstName;
    private String password;
    private String passwordMD5;
    private String position;

    public void setgender(int gender) {
        this.gender = gender;
    }

    public int getgender() {

        return gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", familyName='" + familyName + '\'' +
                ", gender=" + gender +
                ", firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                ", passwordMD5='" + passwordMD5 + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordMD5(String passwordMD5) {
        this.passwordMD5 = passwordMD5;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getId() {

        return id;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordMD5() {
        return passwordMD5;
    }

    public String getPosition() {
        return position;
    }

}

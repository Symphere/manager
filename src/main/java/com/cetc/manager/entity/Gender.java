package com.cetc.manager.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="Gender")
@Entity
public class Gender {
    @Id
    private int id;
    private String gender;

    @Override
    public String toString() {
        return "Gender{" +
                "id=" + id +
                ", gender='" + gender + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

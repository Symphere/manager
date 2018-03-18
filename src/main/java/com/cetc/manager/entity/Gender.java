package com.cetc.manager.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="Gender")
@Entity
public class Gender {
    @Id
    private int id;
    private String gendar;

    @Override
    public String toString() {
        return "Gender{" +
                "id=" + id +
                ", gendar='" + gendar + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGendar() {
        return gendar;
    }

    public void setGendar(String gendar) {
        this.gendar = gendar;
    }
}

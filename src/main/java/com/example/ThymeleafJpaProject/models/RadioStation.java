package com.example.ThymeleafJpaProject.models;

import javax.persistence.*;

@Entity(name = "radiostation")
public class RadioStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer id_user;
    private String radiostation_name;
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getRadiostation_name() {
        return radiostation_name;
    }

    public void setRadiostation_name(String name) {
        this.radiostation_name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

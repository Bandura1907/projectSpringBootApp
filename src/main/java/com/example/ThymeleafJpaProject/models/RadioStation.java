package com.example.ThymeleafJpaProject.models;

import javax.persistence.*;

@Entity(name = "radiostation")
public class RadioStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String radiostation_name;
    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

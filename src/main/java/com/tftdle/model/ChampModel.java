package com.tftdle.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Optional;

@Entity
@Table(name = "champions")
public class ChampModel {
    @Id
    private Long id;

    private String name = "";
    private String gender = "";
    private Integer cost = 0;
    private String type = "";
    private String imageurl = "";
    private String traits = "";
    private Integer attRange = 0;

    public ChampModel() {}

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Integer getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    public Integer getAttRange() {
        return attRange;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getTraits() {
        return traits;
    }
    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAttRange(Integer attRange) {
        this.attRange = attRange;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }
}

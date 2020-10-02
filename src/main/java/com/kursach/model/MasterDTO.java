package com.kursach.model;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class MasterDTO {



    private Integer id;

    private ArrayList<Integer> arrayId;

    private ArrayList<Short> arrayValue;

    private String buildName;

    private Integer req_id;

    public Integer getReq_id() {
        return req_id;
    }

    public void setReq_id(Integer req_id) {
        this.req_id = req_id;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public ArrayList<Integer> getArrayId() {
        return arrayId;
    }

    public void setArrayId(ArrayList<Integer> arrayId) {
        this.arrayId = arrayId;
    }

    public ArrayList<Short> getArrayValue() {
        return arrayValue;
    }

    public void setArrayValue(ArrayList<Short> arrayValue) {
        this.arrayValue = arrayValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

package com.liv3ly.demo.data.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseArrayResponse<T> {

//    @SerializedName("name")
//    private String name;

//    @SerializedName("data")
    private List<T> data;

//    @SerializedName("code")
//    private Integer code;

//    public String getName() {
//        return name;
//    }

    public List<T> getData() {
        return data;
    }

//    public Integer getCode() {
//        return code;
//    }
}

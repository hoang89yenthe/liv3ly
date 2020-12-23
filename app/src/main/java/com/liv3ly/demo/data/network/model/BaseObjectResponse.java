package com.liv3ly.demo.data.network.model;

import com.google.gson.annotations.SerializedName;

public class BaseObjectResponse<v> {

    @SerializedName("name")
    private String name;

    @SerializedName("data")
    private v data;

    @SerializedName("code")
    private String code;

    @SerializedName("errors")
    private ApiError errors;


    public v getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public ApiError getErrors() {
        return errors;
    }
}



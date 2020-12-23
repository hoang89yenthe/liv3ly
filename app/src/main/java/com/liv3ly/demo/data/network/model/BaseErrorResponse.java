package com.liv3ly.demo.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseErrorResponse {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("errors")
    @Expose
    private ApiError errors;

    public String getName() {
        return name;
    }

    public ApiError getErrors() {
        return errors;
    }
}

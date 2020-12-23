/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by HoangDH 23/12/2020.
 */

public class ApiError {

    @SerializedName("httpResponseError")
    @Expose
    private HttpResponseError httpResponseError;

    @SerializedName("validationError")
    @Expose
    private List<ValidationError> validationError;

    public HttpResponseError getHttpResponseError() {
        return httpResponseError;
    }

    public List<ValidationError> getValidationError() {
        return validationError;
    }
}

package com.liv3ly.demo.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HttpResponseError {

    @SerializedName("message")
    @Expose
    private Object message;

    @SerializedName("http_code")
    @Expose
    private int httpCode;

    @SerializedName("err_code")
    @Expose
    private int errCode;


    public Object getMessage() {
        return message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public int getErrCode() {
        return errCode;
    }
}

package com.liv3ly.demo.data.network.model;

import com.google.gson.annotations.SerializedName;

public class VerifyOtpRequest {

    @SerializedName("login_name")
    private String loginName;

    @SerializedName("otp")
    private String otp;

    public VerifyOtpRequest(String loginName, String otp) {
        this.loginName = loginName;
        this.otp = otp;
    }
}

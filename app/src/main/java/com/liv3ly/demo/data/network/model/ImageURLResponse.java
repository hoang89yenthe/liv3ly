package com.liv3ly.demo.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ImageURLResponse {

    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    @SerializedName("hex")
    @Expose
    private String hex;

    public String getHex() {
        return hex;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}

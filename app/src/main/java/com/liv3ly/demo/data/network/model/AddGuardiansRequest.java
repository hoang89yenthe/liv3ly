package com.liv3ly.demo.data.network.model;

import com.google.gson.annotations.SerializedName;

public class AddGuardiansRequest {

    @SerializedName("type")
    private String type;

    @SerializedName("supervisor_id")
    private Integer supervisorIdCareMe;

    @SerializedName("supervised_id")
    private Integer supervisedIdTakeMe;

    @SerializedName("relation")
    private String relation;

    public AddGuardiansRequest(String type, Integer supervisorIdCareMe, Integer supervisedIdTakeMe, String relation) {
        this.type = type;
        this.supervisorIdCareMe = supervisorIdCareMe;
        this.supervisedIdTakeMe = supervisedIdTakeMe;
        this.relation = relation;
    }
}

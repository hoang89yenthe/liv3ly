package com.liv3ly.demo.data.network.model;

import com.google.gson.annotations.SerializedName;

public class ValidationError {

    @SerializedName("field")
    private String field;

    @SerializedName("type")
    private String type;

    @SerializedName("actual")
    private String actual;

    @SerializedName("expected")
    private String expected;

    @SerializedName("nodeID")
    private String nodeID;

    @SerializedName("action")
    private String action;

    @SerializedName("message")
    private String message;

    public String getField() {
        return field;
    }

    public String getType() {
        return type;
    }

    public String getActual() {
        return actual;
    }

    public String getExpected() {
        return expected;
    }

    public String getNodeID() {
        return nodeID;
    }

    public String getAction() {
        return action;
    }

    public String getMessage() {
        return message;
    }
}

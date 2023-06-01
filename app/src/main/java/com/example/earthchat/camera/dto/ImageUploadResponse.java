package com.example.earthchat.camera.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageRegistResponse {
    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("message")
    @Expose
    private String message;

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

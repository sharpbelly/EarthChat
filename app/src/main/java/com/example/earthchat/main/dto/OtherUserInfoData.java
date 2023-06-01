package com.example.earthchat.dto;

import com.google.gson.annotations.SerializedName;

public class OtherUserInfoData {
    @SerializedName("message")
    private String message;

    public OtherUserInfoData(String message) {
        this.message = message;
    }
}

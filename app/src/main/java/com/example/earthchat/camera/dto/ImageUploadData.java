package com.example.earthchat.camera.dto;

import com.google.gson.annotations.SerializedName;

import okhttp3.MultipartBody;

public class ImageRegistData {
    @SerializedName("image")
    private MultipartBody.Part image;

    public ImageRegistData(MultipartBody.Part image) {
        this.image = image;
    }
}

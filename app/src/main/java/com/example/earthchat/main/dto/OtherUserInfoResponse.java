package com.example.earthchat.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtherUserInfoResponse {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("age")
    @Expose
    private int age;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("address")
    @Expose
    private String address;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public OtherUserInfoResponse(String name, int age, String phone, String address) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.address = address;
    }
}

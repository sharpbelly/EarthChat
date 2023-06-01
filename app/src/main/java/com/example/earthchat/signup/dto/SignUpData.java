package com.example.earthchat.dto;

import com.google.gson.annotations.SerializedName;

public class SignUpData {

    @SerializedName("id")
    String id;
    @SerializedName("password")
    String password;
    @SerializedName("name")
    String name;
    @SerializedName("age")
    int age;
    @SerializedName("phone")
    String phone;
    @SerializedName("address")
    String address;

    public SignUpData(String id, String password, String name, int age, String phone, String address){
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.address = address;
    }

}

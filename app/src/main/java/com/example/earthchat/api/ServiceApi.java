package com.example.earthchat.api;

import com.example.earthchat.dto.LoginData;

import retrofit2.Call;
import retrofit2.http.POST;

public interface Retrofit_interface {
//    @GET("posts/{UserID}")
//    Call<LoginData> test_api_get(
//            @Path("UserID") String userid);

    @POST("login.php")
    Call<LoginData> login();

    @POST("sign_up.php")
    Call<LoginData> signUp();

}

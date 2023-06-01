package com.example.earthchat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.earthchat.api.ServiceApi;
import com.example.earthchat.R;
import com.example.earthchat.dto.OtherUserInfoResponse;
import com.example.earthchat.networrk.RetrofitClient;
import com.example.earthchat.adapter.MainItemAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<OtherUserInfoResponse> userList;
    private MainItemAdapter adapter;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = RetrofitClient.getClient().create(ServiceApi.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        service.otherUser().enqueue(new Callback<List<OtherUserInfoResponse>>() {

            @Override
            public void onResponse(Call<List<OtherUserInfoResponse>> call, Response<List<OtherUserInfoResponse>> response) {
                if(response.isSuccessful()) {
                    List<OtherUserInfoResponse> data = response.body();
                    userList = new ArrayList<>();
                    for(int i=0;i<data.size();i++){
                        String name = data.get(i).getName();
                        int age = data.get(i).getAge();
                        String phone = data.get(i).getPhone();
                        String address = data.get(i).getAddress();
                        userList.add(new OtherUserInfoResponse(name, age, phone, address));
                    }
                    adapter = new MainItemAdapter(userList);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<OtherUserInfoResponse>> call, Throwable t) {
                Log.e("MainActivity", "Error : " + t.getMessage());
            }
        });

    }
}
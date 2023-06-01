package com.example.earthchat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.earthchat.R;
import com.example.earthchat.networrk.RetrofitClient;
import com.example.earthchat.api.ServiceApi;
import com.example.earthchat.dto.LoginData;
import com.example.earthchat.dto.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText mID;
    private EditText mPassword;
    private Button mLoginButton;
    private Button mSignUpButton;
    private ProgressBar mProgress;
    private ServiceApi service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mID = (EditText)findViewById(R.id.LoginID);
        mPassword = (EditText)findViewById(R.id.LoginPW);
        mLoginButton = (Button)findViewById(R.id.LoginButton);
        mSignUpButton = (Button) findViewById(R.id.SignUpButton);
        mProgress = (ProgressBar) findViewById(R.id.LoginProgress);
        service = RetrofitClient.getClient().create(ServiceApi.class);


        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void attemptLogin() {
        mID.setError(null);
        mPassword.setError(null);

        String id = mID.getText().toString();
        String password = mPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        //아이디 유효성 검사
        if(id.isEmpty()){
            mID.setError("아이디를 입력해주세요");
            focusView = mID;
            cancel = true;
        }

        //비밀번호 유효성 검사
        if(password.isEmpty()){
            mPassword.setError("비밀번호를 입력해주세요");
            focusView = mPassword;
            cancel = true;
        }

        if(cancel){
            focusView.requestFocus();
        }else{
            startLogin(new LoginData(id, password));
            showProgress(true);
        }
    }

    private void startLogin(LoginData data) {
        service.login(data).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse result = response.body();
                if(result.isStatus() == true) {
                    Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, result.getMessage(), Toast.LENGTH_LONG).show();
                    showProgress(false);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_LONG).show();
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });
    }

    private void showProgress(boolean show) {
        mProgress.setVisibility(show ? View.VISIBLE:View.GONE);

    }
}
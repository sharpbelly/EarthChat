package com.example.earthchat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.earthchat.R;
import com.example.earthchat.api.ServiceApi;
import com.example.earthchat.dto.SignUpData;
import com.example.earthchat.dto.SignUpResponse;
import com.example.earthchat.networrk.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText mID;
    private EditText mPassword;
    private EditText mName;
    private EditText mAge;
    private EditText mPhone;
    private Button mButton;
    private EditText mAddress;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        service= RetrofitClient.getClient().create(ServiceApi.class);
        mID=(EditText) findViewById(R.id.signUpId);
        mPassword=(EditText)findViewById(R.id.signupPassword);
        mName=(EditText)findViewById(R.id.signUpName);
        mAge=(EditText)findViewById(R.id.signUpAge);
        mPhone=(EditText)findViewById(R.id.signUpPhone);
        mAddress=(EditText)findViewById(R.id.signUpAddress);
        mButton=(Button)findViewById(R.id.signUpSubmitButton);

        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                attemptSignUp();
            }
        });
    }

    private void attemptSignUp() {
        mID.setError(null);
        mPassword.setError(null);
        mName.setError(null);
        mAge.setError(null);
        mPhone.setError(null);
        mAddress.setError(null);

        String id = mID.getText().toString();
        String password = mPassword.getText().toString();
        String name = mName.getText().toString();
        int age = Integer.parseInt(mAge.getText().toString());
        String phone = mPhone.getText().toString();
        String address = mAddress.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(id.isEmpty()){
            mID.setError("아이디를 입력해주세요");
            focusView = mID;
            cancel = true;
        }

        if(password.isEmpty()){
            mPassword.setError("비밀번호를 입력해주세요");
            focusView = mPassword;
            cancel = true;
        }

        if(name.isEmpty()){
            mName.setError("이름을 입력해주세요");
            focusView = mName;
            cancel = true;
        }

//        if(age < 0 || age > 120){
//            mAge.setError("나이를 제대로 작성해주세요");
//            focusView = mAge;
//            cancel = true;
//        }

        if(phone.isEmpty()){
            mPhone.setError("핸드폰번호를 입력해주세요");
            focusView = mPhone;
            cancel = true;
        }

        if(address.isEmpty()){
            mAddress.setError("주소를 입력해주세요");
            focusView = mAddress;
            cancel = true;
        }

        if(cancel){
            focusView.requestFocus();
        }else{
            startSignUp(new SignUpData(id, password, name, age, phone, address));
        }
    }

    private void startSignUp(SignUpData data) {
        service.signUp(data).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                SignUpResponse result = response.body();
                Toast.makeText(SignUpActivity.this, result.getMessage(), Toast.LENGTH_LONG).show();

                if(result.isStatus()){
                    finish();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "회원가입 에러 발생", Toast.LENGTH_LONG).show();
                Log.e("회원가입 에러 발생",t.getMessage());
            }
        });

    }
}
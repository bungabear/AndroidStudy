package com.bungabear.androidstudy.ActivityTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bungabear.androidstudy.BasicViewTest.BasicViewLogin;
import com.bungabear.androidstudy.R;

public class ResultRequestActivity extends AppCompatActivity {
    public static final int LOGIN_REQUEST = 1000;

    private String mStEmail = "", mStPw = "";
    private TextView tvEmail, tvPw;
    private Button btnLoginRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_test_result_request);
        tvEmail = findViewById(R.id.tv_activity_test_request_result_email);
        tvPw = findViewById(R.id.tv_activity_test_request_result_pw);
        btnLoginRequest = findViewById(R.id.btn_activity_test_request_login);
        btnLoginRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), BasicViewLogin.class),LOGIN_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == LOGIN_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                mStEmail = data.getStringExtra("email");
                mStPw = data.getStringExtra("pw");
                tvEmail.setText(mStEmail);
                tvPw.setText(mStPw);
            }
            else if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this,"로그인이 취소되었습니다", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

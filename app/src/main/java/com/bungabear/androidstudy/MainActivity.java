package com.bungabear.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bungabear.androidstudy.BasicView.BasicViewLogin;
import com.bungabear.androidstudy.BasicView.BasicViewTest;
import com.bungabear.androidstudy.BasicView.BasicViewTicTaeToe;
import com.bungabear.androidstudy.BasicView.BasicViewTicTaeToeDynamic;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1, btn2, btn3, btn4;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn_basic_test_activity);
        btn2 = findViewById(R.id.btn_basic_test_tic_tae_toe);
        btn3 = findViewById(R.id.btn_basic_test_login);
        btn4 = findViewById(R.id.btn_basic_test_tic_tae_toe_dynamic);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_basic_test_activity:
                startActivity(new Intent(getApplicationContext(), BasicViewTest.class));
                break;
            case R.id.btn_basic_test_tic_tae_toe:
                startActivity(new Intent(getApplicationContext(), BasicViewTicTaeToe.class));
                break;
            case R.id.btn_basic_test_login:
                startActivity(new Intent(getApplicationContext(), BasicViewLogin.class));
                break;
            case R.id.btn_basic_test_tic_tae_toe_dynamic:
                startActivity(new Intent(getApplicationContext(), BasicViewTicTaeToeDynamic.class));
                break;
        }
    }
}

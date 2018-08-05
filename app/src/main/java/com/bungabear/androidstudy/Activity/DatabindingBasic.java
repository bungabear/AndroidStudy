package com.bungabear.androidstudy.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bungabear.androidstudy.Model.DatabindingBasicTestModel;
import com.bungabear.androidstudy.R;
import com.bungabear.androidstudy.databinding.ActivityDatabindingBasicBinding;

public class DatabindingBasic extends AppCompatActivity {

    private ActivityDatabindingBasicBinding binding = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_basic);
        binding.tvDatabindingTest1.setText("기본 바인딩 테스트");
        binding.tvDatabindingTest1.setVisibility(View.VISIBLE);
        binding.setData(new DatabindingBasicTestModel("모델을 이용한 바인딩"));
    }
}

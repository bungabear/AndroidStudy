package com.bungabear.androidstudy.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bungabear.androidstudy.R;
import com.bungabear.androidstudy.databinding.ActivityDatabindingRecyclerBinding;

public class DatabindingRecycler extends AppCompatActivity {

    private ActivityDatabindingRecyclerBinding binding = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_recycler);
//        binding.setItems();
    }
}

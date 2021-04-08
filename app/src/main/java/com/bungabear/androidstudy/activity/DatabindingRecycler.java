package com.bungabear.androidstudy.activity;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

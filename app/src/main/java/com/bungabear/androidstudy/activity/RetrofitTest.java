package com.bungabear.androidstudy.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import com.bungabear.androidstudy.fragment.RetrofitTestPostFragment;
import com.bungabear.androidstudy.R;

/**
 * Created by Minjae Son on 2018-07-29.
 */
public class RetrofitTest extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_placeholder);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.placeholder, (Fragment) RetrofitTestPostFragment.create(this))
                .commit();
    }
}

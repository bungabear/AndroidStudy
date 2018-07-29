package com.bungabear.androidstudy.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.bungabear.androidstudy.Fragment.RetrofitTestPostFragment;
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

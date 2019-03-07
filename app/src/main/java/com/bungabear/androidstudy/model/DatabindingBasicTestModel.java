package com.bungabear.androidstudy.model;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.util.Log;
import android.widget.TextView;

public class DatabindingBasicTestModel {
    @BindingAdapter({"bind:bindingTest"})
    public static void bindingTest(TextView v, String text){
        v.setText(text);
        Log.d("test", text);
    }

    @BindingConversion
    public static String intToString(int num){
        return "BindingConversion을 거친 " + String.valueOf(num);
    }

    public DatabindingBasicTestModel(String text1){
        bindingTestText1 = text1;
    }
    public String bindingTestText1;
}


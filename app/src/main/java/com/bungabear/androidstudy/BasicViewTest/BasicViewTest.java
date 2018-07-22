package com.bungabear.androidstudy.BasicViewTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.bungabear.androidstudy.R;

public class BasicViewTest extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Button mainBtn1;
    private CheckBox mainChk1;
    private TextView mainTv1;
    private EditText mainEt1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_view_test);

        mainBtn1 = findViewById(R.id.main_btn_1);
        mainChk1 = findViewById(R.id.main_chk_1);
        mainTv1 = findViewById(R.id.main_tv_1);
        mainEt1 = findViewById(R.id.main_et_1);
        mainEt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Minjae",s.toString());
                mainTv1.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mainBtn1.setOnClickListener(this);
        mainChk1.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.main_btn_1){
            Log.d("Minjae", "버튼 눌림");
            mainChk1.setChecked(true);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.main_chk_1){
            Log.d("Minjae", ""+isChecked);
            mainTv1.setText(""+isChecked);
        }
    }
}

package com.bungabear.androidstudy.BasicView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.bungabear.androidstudy.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BasicViewLogin extends AppCompatActivity {

    private EditText etId, etPw;
    private TextView tvIdInfo, tvPwInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_view_test_login);

        etId = findViewById(R.id.et_login_id);
        etPw = findViewById(R.id.et_login_pw);
        tvIdInfo = findViewById(R.id.tv_login_id_info);
        tvPwInfo = findViewById(R.id.tv_login_pw_info);
        etId.addTextChangedListener(emailWatcher);
        etPw.addTextChangedListener(pwWatcher);

    }


    TextWatcher emailWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            String email = s.toString();
            // \\w : 문자, ++ 1개이상. @ : @, \\. : .(dot)
            Pattern regex = Pattern.compile("\\w++@\\w++\\.\\w++");
            Matcher m = regex.matcher(email);
            boolean b = m.matches();
            if(b){
                tvIdInfo.setText("정상적인 아이디입니다.");
                return;
            }
            tvIdInfo.setText("이메일 형식이 아닙니다.");
        }
    };

    TextWatcher pwWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            String pw = s.toString();
            if(pw.length() < 8) {
                tvPwInfo.setText("8자 이상 입력해주세요.");
                return;
            }
            else {
                Pattern regex = Pattern.compile("\\.*+[\\!@#\\$%\\^&\\*()]++\\.*+");
                Matcher m = regex.matcher(pw);
                boolean b = m.matches();
                if(b){
                    Pattern regex2 = Pattern.compile("\\.*+[A-Z]++\\.*+");
                    Matcher m2 = regex2.matcher(pw);
                    boolean b2 = m.matches();
                    if(b2){
                        tvPwInfo.setText("정상입니다.");
                    }
                    else {
                        tvPwInfo.setText("대문자를 입력해주세요.");
                    }
                }
                else {
                    tvPwInfo.setText("특수문자를 입력해주세요.");
                }
            }
        }
    };

}

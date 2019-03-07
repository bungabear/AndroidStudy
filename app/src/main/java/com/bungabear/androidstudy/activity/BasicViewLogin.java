package com.bungabear.androidstudy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bungabear.androidstudy.model.LoginResult;
import com.bungabear.androidstudy.R;
import com.bungabear.androidstudy.util.Singleton;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasicViewLogin extends AppCompatActivity {

    private EditText etId, etPw;
    private TextView tvIdInfo, tvPwInfo;
    private Button btnLogin;
    private Boolean mBoEmail = false, mBoPw = false;
    private Pattern upperCase = Pattern.compile(".*[A-Z]+.*");
    // ! @ # $ % ^ & * ( ) ' ; / ? \ [ ] | : " < > ,
    private Pattern specialChars = Pattern.compile(".*[!@#$%\\^\\.&*()';/?\\\\ \\[\\]\\|\\:\"<>,]+.*");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_view_test_login);

        etId = findViewById(R.id.et_login_id);
        etPw = findViewById(R.id.et_login_pw);
        tvIdInfo = findViewById(R.id.tv_login_id_info);
        tvPwInfo = findViewById(R.id.tv_login_pw_info);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.retrofitLogin.login(etId.getText().toString(), etPw.getText().toString()).enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if(response.isSuccessful()){
                            LoginResult result = response.body();
                            Log.d("test", "" + result.result);
                            Log.d("test", "" + result.token);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {

                    }
                });
//                Intent retI = new Intent();
//                if(mBoEmail && mBoPw){
//                    retI.putExtra("email", etId.getText().toString());
//                    retI.putExtra("pw", etPw.getText().toString());
//                    setResult(Activity.RESULT_OK, retI);
//                    finish();
//                }
//                else {
//                    if(!mBoPw){
//                        etPw.setError("비밀번호 형식이 아닙니다");
//                    }
//                    if(!mBoEmail){
//                        etId.setError("이메일 형식이 아닙니다");
//                    }
//                }
            }
        });
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
//            Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$").matcher(email).matches();
            if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                tvIdInfo.setText("정상적인 아이디입니다.");
                mBoEmail = true;
            }
            else {
                tvIdInfo.setText("이메일 형식이 아닙니다.");
                mBoEmail = false;
            }
        }
    };

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }

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
                mBoPw = false;
            }
            else {
                if(upperCase.matcher(pw).matches()){
                    if(specialChars.matcher(pw).matches()){
                        tvPwInfo.setText("정상입니다.");
                        mBoPw = true;
                    }
                    else {
                        tvPwInfo.setText("특수문자를 입력해주세요.");
                        mBoPw = false;
                    }
                }
                else {
                    tvPwInfo.setText("대문자를 입력해주세요.");
                    mBoPw = false;
                }
            }
        }
    };
}

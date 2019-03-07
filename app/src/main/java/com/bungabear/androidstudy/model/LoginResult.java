package com.bungabear.androidstudy.model;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("result")
    public String result;

    @SerializedName("token")
    public String token;

    public LoginResult(String result, String token) {
        this.result = result;
        this.token = token;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

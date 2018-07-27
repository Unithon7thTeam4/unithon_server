package com.unithon.unithon_server.Test.Model;

public class fcm {

    private String message;
    private String token;


    public fcm(){}
    public fcm(String message, String token){
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

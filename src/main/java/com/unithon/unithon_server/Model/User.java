package com.unithon.unithon_server.Model;


import lombok.ToString;

//@ToString
public class User {

    private String id;
    private String password;
    private String name;
    private String token;
    private String qr_code;


    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    };

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(){}
    public User(String id, String password, String name,String token){
        this.id = id;
        this.password = password;
        this.token = token;
        this.name = name;
    }

}

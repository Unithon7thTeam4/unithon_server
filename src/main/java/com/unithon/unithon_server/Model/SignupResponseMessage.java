package com.unithon.unithon_server.Model;


import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SignupResponseMessage {

    // HttpStatus
    private String status;
    // Http Default Message
    private User user;
    // Error Message to USER
    private int statusCode;

    public SignupResponseMessage() {}

    public SignupResponseMessage(String status, User user, int statusCode) {
        this.status = status;
        this.user = user;
        this.statusCode = statusCode;
    }
}

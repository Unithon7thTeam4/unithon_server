package com.unithon.unithon_server.Model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SigninResponseMessage {

    // HttpStatus
    private String status;
    // Http Default Message
    private String message;
    // Error Message to USER
    private int statusCode;

    public SigninResponseMessage() {}

    public SigninResponseMessage(String status, String message, int statusCode) {
        this.status = status;
        this.message = message;
        this.statusCode = statusCode;


    }
}

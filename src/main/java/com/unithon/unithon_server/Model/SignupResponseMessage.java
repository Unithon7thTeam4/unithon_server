package com.unithon.unithon_server.Model;


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
    private String message;
    // Error Message to USER
    private String errorMessage;
    // Error Code
    private int statusCode;


    public SignupResponseMessage() {}

    public SignupResponseMessage(String status, String message, int statusCode, String errorMessage) {
        this.status = status;
        this.message = message;
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }
}

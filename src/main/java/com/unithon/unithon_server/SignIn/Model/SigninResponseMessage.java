package com.unithon.unithon_server.SignIn.Model;


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
    private String errorMessage;
    // Error Code
    private String errorCode;

    public SigninResponseMessage() {}

    public SigninResponseMessage(String status, String message, String errorCode, String errorMessage) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}

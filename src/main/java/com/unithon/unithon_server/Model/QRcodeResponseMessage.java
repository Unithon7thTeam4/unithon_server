package com.unithon.unithon_server.Model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QRcodeResponseMessage {

    // HttpStatus
    private String status;
    // Http Default Message
    private String qrcodePath;
    private int statusCode;

    public QRcodeResponseMessage() {}

    public QRcodeResponseMessage(String status, String qrcodePath, int statusCode) {
        this.status = status;
        this.qrcodePath = qrcodePath;
        this.statusCode = statusCode;
    }
}

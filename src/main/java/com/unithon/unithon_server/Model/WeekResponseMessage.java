package com.unithon.unithon_server.Model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeekResponseMessage {
    // HttpStatus
    private String status;

    private int steadyCnt;
    // Http Default Message
    private String weekInfo;
    // Error Message to USER
    private int statusCode;


    public WeekResponseMessage() {}

    public WeekResponseMessage(String status,int steadyCnt ,String weekInfo, int statusCode) {
        this.status = status;
        this.weekInfo = weekInfo;
        this.statusCode = statusCode;
        this.steadyCnt = steadyCnt;
    }
}

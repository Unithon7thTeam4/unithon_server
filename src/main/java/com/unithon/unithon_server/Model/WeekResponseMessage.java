package com.unithon.unithon_server.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.JsonObject;
import com.unithon.unithon_server.WeekModel.WeekInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class WeekResponseMessage {
    // HttpStatus
    private String status;

    private int steadyCnt;
    // Http Default Message
//    @JsonProperty
    private WeekInfo weekInfo;
    private List<String> week;

    // Error Message to USER
    private int statusCode;


    public WeekResponseMessage() {}

    public WeekResponseMessage(String status,int steadyCnt ,WeekInfo weekInfo,List<String> week, int statusCode) {
        this.status = status;
        this.weekInfo = weekInfo;
        this.week = week;
        this.statusCode = statusCode;
        this.steadyCnt = steadyCnt;
    }
}

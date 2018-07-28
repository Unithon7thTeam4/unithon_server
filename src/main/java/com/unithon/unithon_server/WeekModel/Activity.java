package com.unithon.unithon_server.WeekModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Activity {
    private String strch_type;
    private int count;
    private String date;
    private String capture;

    public Activity(){}
    public Activity(String strch_type, int count ){}
}

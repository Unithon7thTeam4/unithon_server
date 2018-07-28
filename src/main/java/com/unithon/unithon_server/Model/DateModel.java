package com.unithon.unithon_server.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DateModel {
    private String id;
    private String date;

    public DateModel(String id, String date){
        this.id = id;
        this.date = date;
    }
}

package com.unithon.unithon_server.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Complete {
    private String id;
    private int strch_type;
    private String date;
    private String year;
    private String month;
    private String day;
    private int count;



    public Complete(){}
    public Complete(String id, int strchType, int count){
        this.id = id;
        this.strch_type = strchType;
        this.count = count;
    }
}

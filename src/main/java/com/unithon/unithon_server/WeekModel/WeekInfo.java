package com.unithon.unithon_server.WeekModel;

import com.unithon.unithon_server.Model.Complete;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class WeekInfo {
    private List<WeekComplete> sun;
    private List<WeekComplete> mon;
    private List<WeekComplete> tue;
    private List<WeekComplete> wed;
    private List<WeekComplete> thu;
    private List<WeekComplete> fri;
    private List<WeekComplete> sat;

    public WeekInfo(){}
//    public WeekInfo(List<Complete> sun,List<Complete> mon,List<Complete> tue,List<Complete> wed,List<Complete> thu,List<Complete> fri,List<Complete> sat){
//        this.sun = sun;
//        this.
//    }


}

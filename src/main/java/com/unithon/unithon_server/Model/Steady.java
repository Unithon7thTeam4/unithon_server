package com.unithon.unithon_server.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Steady {
    private String id;
    private int steady_cnt;

    public Steady(){}
    public Steady(String id, int steady_cnt){
        this.id = id;
        this.steady_cnt = steady_cnt;
    }
}

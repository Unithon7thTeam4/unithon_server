package com.unithon.unithon_server.Model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public class SteadyCheck {
    private String id;
    private String date;
    private String opt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public SteadyCheck(){}
    public SteadyCheck(String id, String date, String opt){
        this.id = id;
        this.date = date;
        this.opt = opt;
    }
}

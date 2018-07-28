package com.unithon.unithon_server.Checklist;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.unithon.unithon_server.Mapper.WeekMapper;
import com.unithon.unithon_server.Model.DateModel;
import com.unithon.unithon_server.Model.Steady;
import com.unithon.unithon_server.Model.SteadyCheck;
import com.unithon.unithon_server.Model.WeekResponseMessage;
import com.unithon.unithon_server.S3.S3Uploader;
import com.unithon.unithon_server.WeekModel.WeekInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class WeekController {

    private final S3Uploader s3Uploader;

    @Resource(name="com.unithon.unithon_server.Mapper.WeekMapper")
    WeekMapper weekMapper;

    @RequestMapping(value = "/weekly" ,method = RequestMethod.GET)
//    @ExceptionHandler({SQLException.class,DataAccessException.class})
    public ResponseEntity<WeekResponseMessage> week(@RequestParam("id") String id) throws Exception{

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        Date currentTime = new Date ();
        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");
        mSimpleDateFormat.setTimeZone(time);
        String mTime = mSimpleDateFormat.format (currentTime);
        String YMD[] = mTime.split("-");
        GetWeek gw = new GetWeek();
        String weeklist[] = gw.weekCalendar(mTime);
        String yoilArray[] = {"sun","mon","tue","wed","thu","fri","sat"};
        String yesterday = gw.getYesterday(mTime);

        JsonObject weekInfo = new JsonObject();
        Gson gson = new Gson();

        if(weekMapper.getComplete(new DateModel(id,yesterday)).size() == 0 && weekMapper.checkSteady(new SteadyCheck(id,mTime,"minus")) == null){
            int steady_cnt = weekMapper.getSteadyCnt(id);
            if(steady_cnt != 0){
                steady_cnt--;
                weekMapper.updateSteady(new Steady(id,steady_cnt));
                weekMapper.insertSteadyCheck(new SteadyCheck(id,mTime,"minus"));
            }
        }

        List<String>  week = new ArrayList<>();

        WeekInfo weekInfo1 = new WeekInfo();
        for(int i = 0; i < weeklist.length; i++){
            String yoil = yoilArray[i];
            week.add(weeklist[i]);

            DateModel dateModel = new DateModel(id, weeklist[i]);
            if(i == 0){
                weekInfo1.setSun(weekMapper.getComplete(dateModel));
            }
            if(i == 1){
                weekInfo1.setMon(weekMapper.getComplete(dateModel));
            }
            if(i == 2){
                weekInfo1.setTue(weekMapper.getComplete(dateModel));
            }
            if(i == 3){
                weekInfo1.setWed(weekMapper.getComplete(dateModel));
            }
            if(i == 4){
                weekInfo1.setThu(weekMapper.getComplete(dateModel));
            }
            if(i == 5){
                weekInfo1.setFri(weekMapper.getComplete(dateModel));
            }
            if(i == 6){
                weekInfo1.setSat(weekMapper.getComplete(dateModel));
            }
//            String arr = gson.toJson(weekMapper.getComplete(dateModel));
//            JsonArray jsonarr = gson.fromJson(arr,JsonArray.class);
//            weekInfo.add(yoil,jsonarr);
        }
//        String weekinfo = gson.toJson(weekInfo);

        Steady steady = new Steady("asd",0);
        WeekResponseMessage message = new WeekResponseMessage("success",weekMapper.getSteadyCnt(id),weekInfo1,week, Integer.parseInt(HttpStatus.OK.toString()));
        return new ResponseEntity<WeekResponseMessage>(message, HttpStatus.OK);
    }

}

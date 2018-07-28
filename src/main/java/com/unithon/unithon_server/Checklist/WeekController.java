package com.unithon.unithon_server.Checklist;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.unithon.unithon_server.Mapper.CompleteMapper;
import com.unithon.unithon_server.Mapper.WeekMapper;
import com.unithon.unithon_server.Model.Complete;
import com.unithon.unithon_server.Model.CompleteResponseMessage;
import com.unithon.unithon_server.Model.DateModel;
import com.unithon.unithon_server.S3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RequiredArgsConstructor
@RestController
public class WeekController {

    private final S3Uploader s3Uploader;

    @Resource(name="com.unithon.unithon_server.Mapper.WeekMapper")
    WeekMapper weekMapper;

    @RequestMapping(value = "/checkList/week" ,method = RequestMethod.GET)
//    @ExceptionHandler({SQLException.class,DataAccessException.class})
    public ResponseEntity<CompleteResponseMessage> day(@RequestParam("id") String id) throws Exception{

        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        Date currentTime = new Date ();
        String mTime = mSimpleDateFormat.format (currentTime);
        String YMD[] = mTime.split("-");
        GetWeek gw = new GetWeek();
        String weeklist[] = gw.weekCalendar(mTime);
        String yoilArray[] = {"sun","mon","tue","wen","thu","fri","sat"};

        JsonObject weekInfo = new JsonObject();
        Gson gson = new Gson();

        for(int i = 0; i < weeklist.length; i++){
            String yoil = yoilArray[i];
            DateModel dateModel = new DateModel(id, weeklist[i]);
            String arr = gson.toJson(weekMapper.getComplete(dateModel));
            JsonArray jsonarr = gson.fromJson(arr,JsonArray.class);
            weekInfo.add(yoil,jsonarr);
        }
        System.out.println(gson.toJson(weekInfo));

            CompleteResponseMessage message = new CompleteResponseMessage("success","already regist", Integer.parseInt(HttpStatus.ALREADY_REPORTED.toString()));
            return new ResponseEntity<CompleteResponseMessage>(message, HttpStatus.ALREADY_REPORTED);
    }

}

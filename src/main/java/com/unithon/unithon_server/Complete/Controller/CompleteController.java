package com.unithon.unithon_server.Complete.Controller;

import com.google.gson.Gson;
import com.unithon.unithon_server.FCM.pushFcmNotification;
import com.unithon.unithon_server.Mapper.CompleteMapper;
import com.unithon.unithon_server.Mapper.UserMapper;
import com.unithon.unithon_server.Model.*;
import com.unithon.unithon_server.QR.generateQR;
import com.unithon.unithon_server.S3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@RequiredArgsConstructor
@RestController
public class CompleteController {

    private final S3Uploader s3Uploader;

    @Resource(name="com.unithon.unithon_server.Mapper.CompleteMapper")
    CompleteMapper completeMapper;


    @RequestMapping(value = "/complete" ,method = RequestMethod.POST)
    @ExceptionHandler({SQLException.class,DataAccessException.class})
    public ResponseEntity<CompleteResponseMessage> complete(@RequestParam("id") String id, @RequestParam("strch_type") String strch_type,int count) throws Exception{
        Complete complete = new Complete(id,strch_type,count);
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        Date currentTime = new Date ();
        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");
        mSimpleDateFormat.setTimeZone(time);
        String mTime = mSimpleDateFormat.format (currentTime);

        String YMD[] = mTime.split("-");
        complete.setDate(mTime);
        complete.setYear(YMD[0]);
        complete.setMonth(YMD[1]);
        complete.setDay(YMD[2]);

        System.out.println("ju");

        String token = "fJpl8HJMY7Q:APA91bGRj1u8LBnwE5pZqWER4lmU5xi-ddy9H6l-9Dzy4DKMhLkctHiSFFd7h2KQ9evhbvuQ0UnVtZkvZAoC0cg9aYCAz673OV2iP8NHMZ250LGbNfXpsSNl_XNMJi9MtpFsap9CU7AKfDLvl1D9XndIuzcsKAUmDA";
        pushFcmNotification fcm = new pushFcmNotification("바른 자세에 한발 다가가셨네요!",token);
        fcm.start();

        if(completeMapper.isExistComplete(complete) != null){

            CompleteResponseMessage message = new CompleteResponseMessage("Fail","already regist", Integer.parseInt(HttpStatus.FORBIDDEN.toString()));
            return new ResponseEntity<CompleteResponseMessage>(message, HttpStatus.FORBIDDEN);
        }else{

            String abs = "https://s3.ap-northeast-2.amazonaws.com/originman-s3/strech/abs.png";
            String body = "https://s3.ap-northeast-2.amazonaws.com/originman-s3/strech/body.png";
            String leg = "https://s3.ap-northeast-2.amazonaws.com/originman-s3/strech/leg.png";

            if(strch_type.equals("복근운동"))  complete.setCapture(abs);
            if(strch_type.equals("전신운동"))  complete.setCapture(body);
            if(strch_type.equals("다리운동"))  complete.setCapture(leg);
//            complete.setCapture(s3Uploader.upload(capture, "Capture"));
            completeMapper.inserComplete(complete);
            int steady_cnt = completeMapper.getStedy(id);

            if(completeMapper.checkSteady(new SteadyCheck(id,mTime,"plus")) == null)
            if(steady_cnt != 3){
                steady_cnt++;
                completeMapper.updateSteady(new Steady(id,steady_cnt));
                completeMapper.insertSteadyCheck(new SteadyCheck(id,mTime,"plus"));
            }

            CompleteResponseMessage message = new CompleteResponseMessage("Success","complete stretching", Integer.parseInt(HttpStatus.OK.toString()));
            return new ResponseEntity<CompleteResponseMessage>(message, HttpStatus.OK);
        }


    }



}

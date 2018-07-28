package com.unithon.unithon_server.Complete.Controller;

import com.google.gson.Gson;
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

@RequiredArgsConstructor
@RestController
public class CompleteController {

    private final S3Uploader s3Uploader;

    @Resource(name="com.unithon.unithon_server.Mapper.CompleteMapper")
    CompleteMapper completeMapper;


    @RequestMapping(value = "/complete" ,method = RequestMethod.POST)
    @ExceptionHandler({SQLException.class,DataAccessException.class})
    public ResponseEntity<CompleteResponseMessage> complete(@RequestParam("id") String id, @RequestParam("strch_type") String strch_type, int count, @RequestParam("capture") MultipartFile capture) throws Exception{
        Complete complete = new Complete(id,strch_type,count);
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        Date currentTime = new Date ();
        String mTime = mSimpleDateFormat.format (currentTime);
        String YMD[] = mTime.split("-");
        complete.setDate(mTime);
        complete.setYear(YMD[0]);
        complete.setMonth(YMD[1]);
        complete.setDay(YMD[2]);



        if(completeMapper.isExistComplete(complete) != null){

            CompleteResponseMessage message = new CompleteResponseMessage("Fail","already regist", Integer.parseInt(HttpStatus.FORBIDDEN.toString()));
            return new ResponseEntity<CompleteResponseMessage>(message, HttpStatus.FORBIDDEN);
        }else{

            complete.setCapture(s3Uploader.upload(capture, "Capture"));
            completeMapper.inserComplete(complete);
            int steady_cnt = completeMapper.getStedy(id);
            if(steady_cnt != 7){
                steady_cnt++;
                completeMapper.updateSteady(new Steady(id,steady_cnt));
            }
            CompleteResponseMessage message = new CompleteResponseMessage("Success","complete stretching", Integer.parseInt(HttpStatus.OK.toString()));
            return new ResponseEntity<CompleteResponseMessage>(message, HttpStatus.OK);
        }


    }



}

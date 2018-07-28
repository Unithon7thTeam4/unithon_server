package com.unithon.unithon_server.Test.Controller;

import com.unithon.unithon_server.FCM.pushFcmNotification;
import com.unithon.unithon_server.QR.generateQR;
import com.unithon.unithon_server.S3.S3Uploader;
import com.unithon.unithon_server.Test.Model.fcm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class TestController {
    private final S3Uploader s3Uploader;


    @Value("${RdsUser}")
    private String rdsuser;

    @RequestMapping(value = "/test", method =  RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    private String sendFCM(@Valid @RequestBody fcm message) throws Exception{

        pushFcmNotification fcm = new pushFcmNotification(message.getMessage(),message.getToken());
        fcm.start();
        return "asdadsf";
    }

    @RequestMapping(value = "/qr", method =  RequestMethod.GET)
    private String qr(@Valid @RequestBody String a) throws Exception{

//        pushFcmNotification fcm = new pushFcmNotification(message.getMessage(),message.getToken());
//        fcm.start();

        generateQR q = new generateQR();
//        q.generateQRcode();
        return s3Uploader.upload(q.generateQRcode("asdf","asdf"),"QR-code");
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile, "QR-code");
    }

    @PostMapping("/testParam")
    @ResponseBody
    public String upload(@RequestParam("id") String id, String password, String token) throws IOException {
        System.out.println(id);
        System.out.println(password);
        return rdsuser;
    }

}

package com.unithon.unithon_server.Test.Controller;

import com.unithon.unithon_server.FCM.pushFcmNotification;
import com.unithon.unithon_server.QR.generateQR;
import com.unithon.unithon_server.Test.Model.fcm;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TestController {

    @RequestMapping(value = "/test", method =  RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    private String sendFCM(@Valid @RequestBody fcm message) throws Exception{

        pushFcmNotification fcm = new pushFcmNotification(message.getMessage(),message.getToken());
        fcm.start();
        return "asdadsf";
    }

    @RequestMapping(value = "/qr", method =  RequestMethod.GET)
    private String qr(@Valid @RequestBody fcm message) throws Exception{

//        pushFcmNotification fcm = new pushFcmNotification(message.getMessage(),message.getToken());
//        fcm.start();

        generateQR q = new generateQR();
        q.generateQRcode();
        return "asdadsf";
    }

}

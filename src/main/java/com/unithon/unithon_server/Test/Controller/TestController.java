package com.unithon.unithon_server.Test.Controller;

import com.google.gson.JsonObject;
import com.unithon.unithon_server.FCM.pushFcmNotification;
import com.unithon.unithon_server.SignIn.Model.User;
import com.unithon.unithon_server.Test.Model.fcm;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TestController {

    @RequestMapping(value = "/test", method =  RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    private String sendFCM(@Valid @RequestBody fcm message) throws Exception{

        pushFcmNotification fcm = new pushFcmNotification(message.getMessage(),message.getToken());
        fcm.start();
        return "asdadsf";
    }

}

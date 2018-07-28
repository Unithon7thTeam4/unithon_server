package com.unithon.unithon_server.SignIn.Controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.unithon.unithon_server.Mapper.UserMapper;
import com.unithon.unithon_server.Model.SigninResponseMessage;
import com.unithon.unithon_server.Model.SignupResponseMessage;
import com.unithon.unithon_server.Model.User;
import com.unithon.unithon_server.QR.generateQR;
import com.unithon.unithon_server.S3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class SignupController {

    private final S3Uploader s3Uploader;

    @Resource(name="com.unithon.unithon_server.Mapper.UserMapper")
    UserMapper userMapper;

    @RequestMapping(value = "/signUp" ,method = RequestMethod.POST)
    @ExceptionHandler({SQLException.class,DataAccessException.class})
    public ResponseEntity<SignupResponseMessage> SignIn(@RequestParam("id") String id, String password,String name, String token) throws Exception{

        User user = new User(id,password,name,token);
        if(userMapper.isIdExist(id) != null){
            SignupResponseMessage message = new SignupResponseMessage("Fail", null, Integer.parseInt(HttpStatus.FORBIDDEN.toString()));
            return new ResponseEntity<SignupResponseMessage>(message, HttpStatus.ALREADY_REPORTED);
        } else {
            generateQR q = new generateQR();
            String qr_contents = "unithon"+"/"+id+"/"+name;
            String qr_url = s3Uploader.upload(q.generateQRcode(qr_contents,id),"QR-code");
            user.setQr_code(qr_url);
            userMapper.insertUser(user);
            Gson gson = new Gson();
            String userJson = gson.toJson(user);
            SignupResponseMessage message = new SignupResponseMessage("Success",userMapper.isIdExist(id), Integer.parseInt(HttpStatus.OK.toString()));
            return new ResponseEntity<SignupResponseMessage>(message, HttpStatus.OK);
        }

    }



}

package com.unithon.unithon_server.SignIn.Controller;


import com.unithon.unithon_server.Mapper.UserMapper;
import com.unithon.unithon_server.Model.SigninResponseMessage;
import com.unithon.unithon_server.Model.SignupResponseMessage;
import com.unithon.unithon_server.Model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


@RestController
public class SignupController {

    @Resource(name="com.unithon.unithon_server.Mapper.UserMapper")
    UserMapper userMapper;


    @RequestMapping(value = "/signUp" ,method = RequestMethod.POST)
    @ExceptionHandler({SQLException.class,DataAccessException.class})
    public ResponseEntity<SignupResponseMessage> SignIn(@Valid @RequestBody User userinfo) throws Exception{

        if(userMapper.isIdExist(userinfo) != null){
            SignupResponseMessage message = new SignupResponseMessage("Fail", "", Integer.parseInt(HttpStatus.INTERNAL_SERVER_ERROR.toString()), "id already exist");
            return new ResponseEntity<SignupResponseMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            userMapper.insertUser(userinfo);
            SignupResponseMessage message = new SignupResponseMessage("Success",userinfo.getId(), Integer.parseInt(HttpStatus.OK.toString()), "");
            return new ResponseEntity<SignupResponseMessage>(message, HttpStatus.OK);
        }

    }



}

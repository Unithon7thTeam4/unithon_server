package com.unithon.unithon_server.SignIn.Controller;


import com.unithon.unithon_server.Mapper.UserMapper;
import com.unithon.unithon_server.Model.SigninResponseMessage;
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
public class SigninController {

    @Resource(name="com.unithon.unithon_server.Mapper.UserMapper")
    UserMapper userMapper;


    @RequestMapping(value = "/getUsers", method =  RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    private List<User> getUsers() throws Exception{
        return userMapper.getUsers();
    }



    @RequestMapping(value = "/signIn" ,method = RequestMethod.GET)
    @ExceptionHandler({SQLException.class,DataAccessException.class})
    public ResponseEntity<SigninResponseMessage> SignIn(@Valid @RequestBody User user) throws Exception{

        System.out.println(user.getId());
        if(userMapper.isIdExist(user) == null){
            SigninResponseMessage message = new SigninResponseMessage("Fail", "", "401", "not found id");
            return new ResponseEntity<SigninResponseMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            if(userMapper.validPW(user) == null){
                SigninResponseMessage message = new SigninResponseMessage("Fail", "", "401", "not valid pw");
                return new ResponseEntity<SigninResponseMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
            }else{
                SigninResponseMessage message = new SigninResponseMessage("Success", "", "200", "");
                return new ResponseEntity<SigninResponseMessage>(message, HttpStatus.OK);
            }
        }


    }








}

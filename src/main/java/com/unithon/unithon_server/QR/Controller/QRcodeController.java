package com.unithon.unithon_server.QR.Controller;


import com.unithon.unithon_server.Mapper.QRcodeMapper;
import com.unithon.unithon_server.Mapper.UserMapper;
import com.unithon.unithon_server.Model.QRcodeResponseMessage;
import com.unithon.unithon_server.Model.SigninResponseMessage;
import com.unithon.unithon_server.Model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;


@RestController
public class QRcodeController {


    @Resource(name="com.unithon.unithon_server.Mapper.QRcodeMapper")
    QRcodeMapper qrcodeMapper;


    @RequestMapping(value = "/generateQR" ,method = RequestMethod.GET)
    @ExceptionHandler({SQLException.class,DataAccessException.class})
    public ResponseEntity<QRcodeResponseMessage> SignIn(@RequestParam("id") String id) throws Exception {
        String qrurl = qrcodeMapper.getQR(id);
        System.out.println(qrurl);
        QRcodeResponseMessage message = new QRcodeResponseMessage("Success", qrurl, Integer.parseInt(HttpStatus.OK.toString()));
        return new ResponseEntity<QRcodeResponseMessage>(message, HttpStatus.OK);
    }
}

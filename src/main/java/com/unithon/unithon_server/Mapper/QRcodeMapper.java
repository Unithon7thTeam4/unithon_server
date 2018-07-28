package com.unithon.unithon_server.Mapper;

import com.unithon.unithon_server.Model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("com.unithon.unithon_server.Mapper.QRcodeMapper")
public interface QRcodeMapper {

    @Select("SELECT qr_code From User WHERE id = #{id}")
    String getQR(String id) throws Exception;
}

package com.unithon.unithon_server.Mapper;



import com.unithon.unithon_server.Model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("com.unithon.unithon_server.Mapper.UserMapper")
//@Mapper
public interface UserMapper {

     @Select("SELECT * FROM User")
     List<User> getUsers() throws Exception;

     @Select("INSERT INTO User(id,password,name,token,qr_code) VALUES(#{id},#{password},#{name},#{token},#{qr_code})")
     User insertUser(User user) throws Exception;



     @Select("SELECT * From User WHERE id = #{id}")
     User isIdExist(String id) throws Exception;

     @Select("SELECT * From User WHERE id = #{id} and password = #{password}")
     User validPW(String password) throws Exception;

}

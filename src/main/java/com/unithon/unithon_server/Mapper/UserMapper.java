package com.unithon.unithon_server.Mapper;



import com.unithon.unithon_server.SignIn.Model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("com.unithon.unithon_server.Mapper.UserMapper")
//@Mapper
public interface UserMapper {
     @Select("SELECT * FROM SignIn")
     List<User> getUsers() throws Exception;

     @Select("INSERT INTO SignIn(email,uuid,nickname) VALUES(#{email},#{uuid},#{nickname})")
     User insertUser(User user) throws Exception;

     @Select("SELECT * FROM SignIn WHERE email = #{email}")
     User findUserByEmail(String email) throws Exception;



}

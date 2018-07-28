package com.unithon.unithon_server.Mapper;

import com.unithon.unithon_server.Model.*;
import com.unithon.unithon_server.WeekModel.WeekComplete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("com.unithon.unithon_server.Mapper.WeekMapper")
//@Mapper
public interface WeekMapper {

    @Select("SELECT steady_cnt FROM User WHERE id = #{id}")
    int getSteadyCnt(String id) throws Exception;

    @Select("SELECT strch_type FROM User WHERE id = #{id}")
    int getStrchType(String id) throws Exception;

    @Select("UPDATE User SET steady_cnt = #{steady_cnt} WHERE id = #{id}")
    void updateSteady(Steady steady) throws Exception;

    @Select("SELECT strch_type,count,date,capture From CompleteStretching WHERE id = #{id} and date = #{date}")
    List<WeekComplete> getComplete(DateModel date) throws Exception;

    @Select("SELECT * FROM SteadyCheck WHERE id = #{id} and date = #{date} and opt = #{opt}")
    SteadyCheck checkSteady(SteadyCheck check) throws Exception;

    @Select("INSERT INTO SteadyCheck(id,date,opt) VALUES(#{id},#{date},#{opt})")
    void insertSteadyCheck(SteadyCheck check) throws Exception;

}

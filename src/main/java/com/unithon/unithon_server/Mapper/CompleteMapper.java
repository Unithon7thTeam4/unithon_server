package com.unithon.unithon_server.Mapper;


import com.unithon.unithon_server.Model.Complete;
import com.unithon.unithon_server.Model.Steady;
import com.unithon.unithon_server.Model.SteadyCheck;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("com.unithon.unithon_server.Mapper.CompleteMapper")
//@Mapper
public interface CompleteMapper {

    @Select("INSERT INTO CompleteStretching(id,strch_type,date,year,month,day,count,capture) VALUES(#{id},#{strch_type},#{date},#{year},#{month},#{day},#{count},#{capture})")
    Complete inserComplete(Complete complete) throws Exception;

    @Select("SELECT * From CompleteStretching WHERE id = #{id} and strch_type = #{strch_type} and date = #{date}")
    Complete isExistComplete(Complete complete) throws Exception;

    @Select("SELECT steady_cnt From User WHERE id = #{id}")
    int getStedy(String id) throws Exception;

    @Select("UPDATE User SET steady_cnt = #{steady_cnt} WHERE id = #{id}")
    void updateSteady(Steady steady) throws Exception;

    @Select("SELECT * FROM SteadyCheck WHERE id = #{id} and date = #{date} and opt = #{opt}")
    SteadyCheck checkSteady(SteadyCheck check) throws Exception;

    @Select("SELECT token FROM User WHERE id = #{id}")
    String getToekn(String id) throws Exception;

    @Select("INSERT INTO SteadyCheck(id,date,opt) VALUES(#{id},#{date},#{opt})")
    void insertSteadyCheck(SteadyCheck check) throws Exception;

}

package com.unithon.unithon_server.Mapper;


import com.unithon.unithon_server.Model.Complete;
import com.unithon.unithon_server.Model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("com.unithon.unithon_server.Mapper.CompleteMapper")
//@Mapper
public interface CompleteMapper {

    @Select("INSERT INTO CompleteStretching(id,strch_type,date,year,month,day,count) VALUES(#{id},#{strch_type},#{date},#{year},#{month},#{day},#{count})")
    Complete inserComplete(Complete complete) throws Exception;

    @Select("SELECT * From CompleteStretching WHERE id = #{id} and strch_type = #{strch_type} and date = #{date}")
    Complete isExistComplete(Complete complete) throws Exception;



}

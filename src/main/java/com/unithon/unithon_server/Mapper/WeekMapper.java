package com.unithon.unithon_server.Mapper;

import com.unithon.unithon_server.Model.Complete;
import com.unithon.unithon_server.Model.DateModel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("com.unithon.unithon_server.Mapper.WeekMapper")
//@Mapper
public interface WeekMapper {

    @Select("SELECT strch_type, date,capture From CompleteStretching WHERE id = #{id} and date = #{date}")
    List<Complete> getComplete(DateModel date) throws Exception;
}

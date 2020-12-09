package com.custchina.shequdemo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.custchina.shequdemo.model.Tourist;
import com.custchina.shequdemo.model.TouristExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TouristMapper{

    @Select("select * from tourist where user_id=#{id}")
    Tourist findById(@Param("id") Long id);

    Tourist find(@Param("usercode") String usercode,@Param("password") String password);

    List<Tourist> selectByExample();

    int insert(Tourist tourist);
}
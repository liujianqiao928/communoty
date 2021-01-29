package com.custchina.shequdemo.mapper;

import com.custchina.shequdemo.model.Tourist;
import com.custchina.shequdemo.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

      void insert(User user);
        @Select("select * from users where token =#{token}")
         User fingByToken(@Param("token") String token);
        @Select("select * from tourist where user_id=#{id}")
        Tourist findById(@Param("id")long id);
    @Select("select * from users where account_id=#{accountId}")
    User findByAccountId(@Param("accountId")String accountId);

    void update(User user);


//    @Select(" SELECT * FROM  users where id = #{id}")
//    User findById(@Param("id")Integer id);
}

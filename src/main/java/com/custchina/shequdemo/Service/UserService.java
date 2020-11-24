package com.custchina.shequdemo.Service;

import com.custchina.shequdemo.mapper.UserMapper;
import com.custchina.shequdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createorupdate(User user) {
      User dbUser =  userMapper.findByAccountId(user.getAccountId());
      if (dbUser == null){
          user.setGmtCreate(System.currentTimeMillis());
          user.setGmtModified(user.getGmtCreate());
          userMapper.insert(user);
      }else {
          user.setName(user.getName());
          dbUser.setGmtCreate(System.currentTimeMillis());
          dbUser.setToken(user.getToken());
          userMapper.update(dbUser);
      }
    }
}

package com.custchina.shequdemo.Service.ServiceImpl;

import com.custchina.shequdemo.Service.TouristService;
import com.custchina.shequdemo.excaption.CustomizeErrorCode;
import com.custchina.shequdemo.excaption.CustomizeException;
import com.custchina.shequdemo.mapper.TouristMapper;
import com.custchina.shequdemo.model.Tourist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TouristServiceImpl implements TouristService {
    @Autowired
    private TouristMapper touristMapper;
    @Override
    public Tourist find(String code, String password) {

        Tourist tourist = touristMapper.find(code, password);
//        if (tourist == null){
//            throw new CustomizeException(CustomizeErrorCode.LOGIN_OFF);
//        }
        return tourist;
    }

    @Override
    public int insert(Tourist tourist) {
        int insert = touristMapper.insert(tourist);
        return insert;
    }
}

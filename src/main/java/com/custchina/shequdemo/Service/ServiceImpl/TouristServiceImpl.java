package com.custchina.shequdemo.Service.ServiceImpl;

import com.custchina.shequdemo.Service.TouristService;
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
        return touristMapper.find(code,password);
    }

    @Override
    public int insert(Tourist tourist) {
        return touristMapper.insert(tourist);
    }
}

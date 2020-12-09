package com.custchina.shequdemo.Service;

import com.custchina.shequdemo.model.Tourist;
import org.springframework.stereotype.Service;

@Service
public interface TouristService {
    public Tourist find(String code, String password);

    int insert(Tourist tourist);
}

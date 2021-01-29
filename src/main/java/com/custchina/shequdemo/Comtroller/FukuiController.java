package com.custchina.shequdemo.Comtroller;

import com.custchina.shequdemo.excaption.CustomizeErrorCode;
import com.custchina.shequdemo.excaption.CustomizeException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FukuiController {
    @RequestMapping("/fankui")
    public String toFu(){
        throw new CustomizeException(CustomizeErrorCode.GN_OFF);
    }
}

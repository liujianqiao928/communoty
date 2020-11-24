package com.custchina.shequdemo.dto;

import com.custchina.shequdemo.excaption.CustomizeErrorCode;
import com.custchina.shequdemo.excaption.CustomizeException;
import lombok.Data;
import org.springframework.web.servlet.ModelAndView;


public class ResultDto {
    private Integer code;
    private String message;

    public Integer getCode() {

        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public static ResultDto errorOf(Integer code,String message){
       ResultDto resultDto= new ResultDto();
       resultDto.setCode(code);
       resultDto.setMessage(message);
       return resultDto;
    }

    public static ResultDto errorOf(CustomizeErrorCode errorCode) {
        return  errorOf(errorCode.getCode(),errorCode.getMessage());
    }
    public static ResultDto okof(){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }

    public static ResultDto errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }
}

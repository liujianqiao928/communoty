package com.custchina.shequdemo.advice;

import com.alibaba.fastjson.JSON;
import com.custchina.shequdemo.dto.ResultDto;
import com.custchina.shequdemo.excaption.CustomizeErrorCode;
import com.custchina.shequdemo.excaption.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomerizeErr {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(HttpServletRequest request, Throwable e, Model model, HttpServletResponse response){
//        HttpStatus status= getStatus(request);
//        model.addAttribute("message","服务器繁忙,请稍后重试");
       String contentType= request.getContentType();
       if ("application/json".equals(contentType)){
           ResultDto resultDto;
           if (e instanceof CustomizeException){
               resultDto= ResultDto.errorOf((CustomizeException) e);
           }else {
               resultDto= ResultDto.errorOf(CustomizeErrorCode.SYS_ERROR);
           }
           try {
               response.setCharacterEncoding("UTF-8");
               response.setContentType("application/json");
               response.setStatus(200);
              PrintWriter writer= response.getWriter();
              writer.write(JSON.toJSONString(resultDto));
              writer.close();
           } catch (IOException e1) {
               e1.printStackTrace();
           }
           return null;
       }else{
           if (e instanceof CustomizeException){
               model.addAttribute("message",e.getMessage());
           }else {
               model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
           }
           return new ModelAndView("error");
       }

    }
//    private HttpStatus getStatus(HttpServletRequest request){
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode!=null){
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return HttpStatus.valueOf(statusCode);
//    }
}

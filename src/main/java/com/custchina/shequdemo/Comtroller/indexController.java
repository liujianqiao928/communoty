package com.custchina.shequdemo.Comtroller;

import com.alibaba.fastjson.JSON;
import com.custchina.shequdemo.Service.QuestionService;
import com.custchina.shequdemo.cache.HotCache;
import com.custchina.shequdemo.dto.PageDto;
import com.custchina.shequdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class indexController {
    @Autowired
    private UserMapper userMapper;
@Autowired
private QuestionService questionService;
@Autowired
private HotCache hotCache;
    @GetMapping("/")
    public String index(HttpServletRequest request, String token,
                        Model model,
                        @RequestParam(value = "page",defaultValue = "1")Integer page,
                        @RequestParam(value = "size",defaultValue = "8")Integer size) {
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("token")) {
//                String value = cookie.getValue();
//                User user = userMapper.fingByToken(value);
//                if (user != null) {
//                    request.getSession().setAttribute("user", user);
//                }
//                break;
//            }
//
//
        List<String> tags = hotCache.getHots();
        PageDto pageDto =questionService.li(page,size);
        System.out.printf(JSON.toJSONString(pageDto));
        model.addAttribute("pageDto",pageDto);
        model.addAttribute("tags",tags);


        return "index";

    }
    @RequestMapping("/find")
    public String findBy(@RequestParam("check")String title,Model model, @RequestParam(value = "page",defaultValue = "1")Integer page,
                         @RequestParam(value = "size",defaultValue = "5")Integer size){
        PageDto pageDto = questionService.fingTitle(title,page,size);
        model.addAttribute("pageDto",pageDto);
        return "index";
    }
}
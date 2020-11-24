package com.custchina.shequdemo.Comtroller;



import com.custchina.shequdemo.Service.TouristService;
import com.custchina.shequdemo.model.Tourist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private TouristService touristService;

    @PostMapping("/tourist")
    public String login(@RequestParam("usercode" )String usercode,@RequestParam("password")String password,Model model,HttpSession session){
        Tourist tourist = touristService.find(usercode, password);
        System.out.println(tourist);
        if(tourist != null){
            session.setAttribute("tourist",tourist);
            return "redirect:/";
        }
        model.addAttribute("error","请重新登录");
        return "success";
    }

}

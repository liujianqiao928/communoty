package com.custchina.shequdemo.Comtroller;

import com.custchina.shequdemo.Service.QuestionService;
import com.custchina.shequdemo.dto.PageDto;

import com.custchina.shequdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PrefileController {

    @Autowired
    private QuestionService questionService;
    @GetMapping("/pre/{action}")
    public String prfike(HttpServletRequest request, @PathVariable(name = "action")String action, Model model, @RequestParam(value = "page",defaultValue = "1")Integer page,
                         @RequestParam(value = "size",defaultValue = "5")Integer size){
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null){
//            return "redirect:/";
//        }
        User user = new User();
        PageDto pageDto = questionService.list(user.getId(), page, size);
        model.addAttribute("pageDto",pageDto);

        return "prefile";
    }
}

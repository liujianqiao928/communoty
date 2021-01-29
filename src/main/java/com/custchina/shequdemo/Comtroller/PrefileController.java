package com.custchina.shequdemo.Comtroller;

import com.custchina.shequdemo.Service.QuestionService;
import com.custchina.shequdemo.dto.PageDto;

import com.custchina.shequdemo.dto.QuestionDto;
import com.custchina.shequdemo.excaption.CustomizeErrorCode;
import com.custchina.shequdemo.excaption.CustomizeException;
import com.custchina.shequdemo.mapper.QuestionEXMapper;
import com.custchina.shequdemo.model.Question;
import com.custchina.shequdemo.model.Tourist;
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
                         @RequestParam(value = "size",defaultValue = "10")Integer size){
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
        QuestionDto questionDto = new QuestionDto();
       Tourist tourist = (Tourist)request.getSession().getAttribute("tourist");
        if (tourist!=null) {
            Integer a=questionService.coungUser(tourist.getUser_id());
            ;
            PageDto pageDto = questionService.list(tourist.getUser_id(), page, size);

            System.out.println(a);
            model.addAttribute("pageDto", pageDto);
            model.addAttribute("count",a);
            return "prefile";
        }else {
            throw new CustomizeException(CustomizeErrorCode.LOGIN_ONE);
        }

    }
}

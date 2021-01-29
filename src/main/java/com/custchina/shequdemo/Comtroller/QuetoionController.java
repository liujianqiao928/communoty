package com.custchina.shequdemo.Comtroller;

import com.alibaba.fastjson.JSON;
import com.custchina.shequdemo.Service.CommentService;
import com.custchina.shequdemo.Service.QuestionService;
import com.custchina.shequdemo.dto.CommentCreateDto;
import com.custchina.shequdemo.dto.CommentDto;
import com.custchina.shequdemo.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuetoionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
//    @ResponseBody
    public String question(@PathVariable(name = "id")Long id, Model model){
       QuestionDto questionDto= questionService.getById(id);
       List<QuestionDto> relatedQuestions =questionService.selectex(questionDto);
        System.out.println(JSON.toJSON(questionDto));
       List<CommentDto> comments = commentService.listByQuestionId(id);
       questionService.incView(id);
       model.addAttribute("question",questionDto);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}

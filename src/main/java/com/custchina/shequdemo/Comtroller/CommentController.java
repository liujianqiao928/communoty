package com.custchina.shequdemo.Comtroller;

import com.custchina.shequdemo.Service.CommentService;
import com.custchina.shequdemo.dto.CommentCreateDto;
import com.custchina.shequdemo.dto.ResultDto;
import com.custchina.shequdemo.excaption.CustomizeErrorCode;
import com.custchina.shequdemo.model.Comment;
import com.custchina.shequdemo.model.Tourist;
import com.custchina.shequdemo.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDto commentDto, HttpServletRequest request, HttpSession session){
        Tourist tourist = (Tourist) request.getSession().getAttribute("tourist");

        User user=(User)request.getSession().getAttribute("user");
//        System.out.println(user);

        if (tourist==null && user==null){
            return ResultDto.errorOf(CustomizeErrorCode.NO_LOGIN);

        }
        if (commentDto==null || StringUtils.isBlank(commentDto.getContent())){
            return ResultDto.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment=  new Comment();
        comment.setParentId(commentDto.getParentId());
        comment.setContent(commentDto.getContent());
        comment.setType(commentDto.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(tourist.getUser_id());
        Tourist t = (Tourist) session.getAttribute("tourist");

        comment.setPhoto(t.getUser_photo());
        commentService.insert(comment);


        return ResultDto.okof();

    }
}

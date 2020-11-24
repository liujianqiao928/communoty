package com.custchina.shequdemo.Service;

import com.custchina.shequdemo.dto.CommentDto;
import com.custchina.shequdemo.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    void insert(Comment comment);

    List<CommentDto> listByQuestionId(Long id);
}

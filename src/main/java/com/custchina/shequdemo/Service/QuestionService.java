package com.custchina.shequdemo.Service;

import com.custchina.shequdemo.dto.PageDto;
import com.custchina.shequdemo.dto.QuestionDto;
import com.custchina.shequdemo.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
        PageDto li(Integer page, Integer size);
        PageDto fingTitle(String title ,Integer page,Integer size);

        PageDto list(Long id, Integer page, Integer size);

        QuestionDto getById(Long id);
Integer coungUser(Long id);
    void createOrupdate(Question question);

    void incView(Long id);

    List<QuestionDto> selectex(QuestionDto questionDto);
}

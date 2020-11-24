package com.custchina.shequdemo.Service.ServiceImpl;

import com.custchina.shequdemo.Service.QuestionService;
import com.custchina.shequdemo.dto.PageDto;
import com.custchina.shequdemo.dto.QuestionDto;
import com.custchina.shequdemo.mapper.QuestionMapper;
import com.custchina.shequdemo.mapper.TouristMapper;
import com.custchina.shequdemo.mapper.UserMapper;
import com.custchina.shequdemo.model.Question;
import com.custchina.shequdemo.model.Tourist;
import com.custchina.shequdemo.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TouristMapper touristMapper;
    @Override
    public PageDto li(Integer page, Integer size) {
        PageDto pageDto = new PageDto();
        Integer totalCount = questionMapper.count();
        pageDto.setPageination(totalCount,page,size);
        int offset = size*(page-1);
        if (page<1){
            page=1;
        }
        if (page>pageDto.getTotalPage()){
            page=pageDto.getTotalPage() ;
        }
        List<Question> li = questionMapper.li(offset,size);
        List<QuestionDto> liss = new ArrayList<>();
        for (Question question : li) {
//       user= userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);

            Tourist tourist=touristMapper.findById(question.getCreator());
            questionDto.setTourist(tourist);
            liss.add(questionDto);
        }
        pageDto.setQuestions(liss);

        return pageDto;
//        return liss;
    }

    @Override
    public PageDto fingTitle(String title,Integer page,Integer size) {

        PageDto pageDto = new PageDto();
        Integer totalCount = questionMapper.count();
        pageDto.setPageination(totalCount,page,size);
        int offset = size*(page-1);
        if (page<1){
            page=1;
        }
        if (page>pageDto.getTotalPage()){
            page=pageDto.getTotalPage() ;
        }

        List<Question> li = questionMapper.findTitle(title,offset,size);
        List<QuestionDto> liss = new ArrayList<>();
        for (Question question : li) {
            User user= userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            liss.add(questionDto);
        }
        pageDto.setQuestions(liss);

        return pageDto;
    }

    @Override
    public PageDto list(Long id, Integer page, Integer size) {
        PageDto pageDto = new PageDto();
        Integer totalCount = questionMapper.countByUserId(id);
        pageDto.setPageination(totalCount,page,size);
        int offset = size*(page-1);
        if (page<1){
            page=1;
        }
        if (page>pageDto.getTotalPage()){
            page=pageDto.getTotalPage() ;
        }

        List<Question> li = questionMapper.list(id,offset,size);
        List<QuestionDto> liss = new ArrayList<>();
        for (Question question : li) {
            User user= userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            liss.add(questionDto);
        }
        pageDto.setQuestions(liss);

        return pageDto;

    }

    @Override
    public QuestionDto getById(Long id) {
        Question question= questionMapper.getById(id);
//       if (question==null){
//           throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
//
//       }
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        User user= userMapper.findById(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }

    @Override
    public void createOrupdate(Question question) {
        if (question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            questionMapper.add(question);
        }else {
//            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }
    }

    @Override
    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionMapper.incView(question);
    }

    @Override
    public List<QuestionDto> selectex(QuestionDto questionDto) {
        if (StringUtils.isBlank(questionDto.getTag())){
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(questionDto.getTag(), ",");
        String collect = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(questionDto.getId());
        question.setTag(collect);
        List<Question> questions = questionMapper.selectex(question);
        List<QuestionDto> dtos = questions.stream().map(q -> {
            QuestionDto dto = new QuestionDto();
            BeanUtils.copyProperties(q,dto);
            return dto;
        }).collect(Collectors.toList());


        return dtos;
    }
}

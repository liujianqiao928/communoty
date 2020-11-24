package com.custchina.shequdemo.Service.ServiceImpl;

import com.custchina.shequdemo.Service.CommentService;
import com.custchina.shequdemo.dto.CommentDto;
import com.custchina.shequdemo.enums.CommentTypeEnum;
import com.custchina.shequdemo.excaption.CustomizeErrorCode;
import com.custchina.shequdemo.excaption.CustomizeException;
import com.custchina.shequdemo.mapper.CommentMapper;
import com.custchina.shequdemo.mapper.QuestionMapper;
import com.custchina.shequdemo.mapper.TouristMapper;
import com.custchina.shequdemo.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private TouristMapper touristMapper;
    @Override
    public void insert(Comment comment) {
        if (comment.getParentId() ==null || comment.getParentId()==0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType()==null || !CommentTypeEnum.iaExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            Comment dbc = commentMapper.selectByPrimaryKey(comment.getParentId());

            if (dbc == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        }else {
            Question question= questionMapper.getById(comment.getParentId());
            if (question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            questionMapper.incCommentCount(question);

        }
    }

    @Override
    public List<CommentDto> listByQuestionId(Long id) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        commentExample.setOrderByClause("gmt_create desc");
        if (comments.size()==0){

            return new ArrayList<>();
        }

        Set<Long> collect = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());

        List<Long> users = new ArrayList<>();
        users.addAll(collect);
//        TouristExample touristExample = new TouristExample();
//        touristExample.createCriteria().andUserIdIn(users);
     ;
        List<Tourist> tourists = touristMapper.selectByExample();

        Map<Long, Tourist> map = tourists.stream().collect(Collectors.toMap(tourist -> tourist.getUser_id(), tourist -> tourist));
        List<CommentDto> commentDTOS = comments.stream().map(comment -> {
            CommentDto commentDTO = new CommentDto();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setTourist(map.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
    }

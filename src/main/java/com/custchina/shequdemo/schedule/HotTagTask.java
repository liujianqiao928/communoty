package com.custchina.shequdemo.schedule;

import com.custchina.shequdemo.cache.HotCache;
import com.custchina.shequdemo.mapper.QuestionMapper;
import com.custchina.shequdemo.model.Question;
import com.custchina.shequdemo.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class HotTagTask {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private HotCache hotCache;
    @Scheduled(fixedRate = 20000)
    public void reportCurrentTime(){
        int offset =0;
        int limit = 20;
        log.info("the time is START{}",new Date());
        List<Question> list=new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        while (offset==0|| list.size()==limit ){
            list=questionMapper.selectByExampleWithBLOBsWithRowbounds(new QuestionExample(),new RowBounds(offset,limit));
            for (Question question : list) {


               String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    Integer a = map.get(tag);
                    if (a != null){
                        map.put(tag,a+5+question.getCommentCount());
                    }else {
                        map.put(tag,5+question.getCommentCount());
                    }

                }

                
            }
            offset+=limit;
        }

        hotCache.updateTags(map);
        log.info("the time is STOP{}",new Date());
    }
}

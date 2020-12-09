package com.custchina.shequdemo.cache;

import com.custchina.shequdemo.dto.HotDto;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HotCache {
    public List<String> getHots() {
        return hots;
    }

    public void setHots(List<String> hots) {
        this.hots = hots;
    }

    private List<String> hots = new ArrayList<>();

    public void updateTags(Map<String, Integer> tags) {
        int max = 10;
        PriorityQueue<HotDto> priorityQueue = new PriorityQueue<>(max);
        tags.forEach(
                (name,priority)->{
                    HotDto hotDto = new HotDto();
                    hotDto.setName(name);
                    hotDto.setPriority(priority);

                    if (priorityQueue.size()<max){
                        priorityQueue.add(hotDto);

                    }else {
                        HotDto min = priorityQueue.peek();
                        if(hotDto.compareTo(min)>0){
                            priorityQueue.poll();
                            priorityQueue.add(hotDto);
                        }
                    }
                }
        );
        List<String> list = new ArrayList<>();
        HotDto poll = priorityQueue.poll();

        while (poll!=null){
            list.add(0,poll.getName());
            poll=priorityQueue.poll();
        }
        hots=list;
//        System.out.println(hots);

    }
}
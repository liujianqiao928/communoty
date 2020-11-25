package com.custchina.shequdemo.dto;

import java.util.ArrayList;
import java.util.List;

public class PageDto {
    private List<QuestionDto> questions;
    private boolean showPre;
    private boolean showFirst;
    private boolean showNext;
    private boolean showEnd;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isShowPre() {
        return showPre;
    }

    public void setShowPre(boolean showPre) {
        this.showPre = showPre;
    }

    public boolean isShowFirst() {
        return showFirst;
    }

    public void setShowFirst(boolean showFirst) {
        this.showFirst = showFirst;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public boolean isShowEnd() {
        return showEnd;
    }

    public void setShowEnd(boolean showEnd) {
        this.showEnd = showEnd;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }

    public void setPageination(Integer totalCount, Integer page, Integer size) {

       if (totalCount%size==0){
            totalPage=totalCount / size;
        }

        else{
            totalPage =totalCount / size +1;
        }
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        this.page=page;
        pages.add(page);
        for (int i = 1 ; i<=2;i++){
            if (page-i>0){
                pages.add(0,page-i);
            }
            if (page+i<=totalPage){
                pages.add(page+i);
            }
        }

        if (page == 1){
            showPre=false;
        }else {
            showPre=true;
        }
        if (page==totalPage){
            showNext=false;
        }else {
            showNext=true;
        }
        if (pages.contains(1)){
            showFirst=false;
        }else {
            showFirst=true;
        }
        if (pages.contains(totalPage)){
            showEnd=false;
        }else {
            showEnd=true;
        }

    }
}

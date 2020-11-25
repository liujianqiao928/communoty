package com.custchina.shequdemo.dto;

import org.jetbrains.annotations.NotNull;

public class HotDto implements  Comparable
{
    private String name;
    private Integer priority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return this.getPriority()-((HotDto) o).getPriority();
    }
}

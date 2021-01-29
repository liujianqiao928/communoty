package com.custchina.shequdemo.mapper;

import com.custchina.shequdemo.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface QuestionEXMapper {

    void add(Question question);
//    @Select("select * from question  order by id desc limit #{offset} ,#{size}")
    List<Question> li(@Param("offset") Integer offset,@Param("size") Integer size);
    @Select("select count(1) from question")
    Integer count();
    @Select("select * from question where title like \"%\"#{title}\"%\" limit #{offset} ,#{size} ")
    List<Question> findTitle(@Param("title")String title,@Param("offset") Integer offset,@Param("size") Integer size);
//    @Select("select * from question where creator = #{id} limit #{offset} ,#{size}")
    List<Question> list(@Param("id") Long id, @Param("offset") Integer offset,@Param("size") Integer size);
    @Select("select count(1) from question where creator = #{id}")
    Integer countByUserId(@Param("id") Long id);

    List<Question> selectex(Question question);
    Question getById( Long id);
    @Update("update question set title=#{title},description =#{description},tag=#{tag},gmt_modified=#{gmtModified} where id=#{id}")
    void update(Question question);

    int incView(Question record);

    int incCommentCount(Question record);





}

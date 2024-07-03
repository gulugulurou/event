package com.wanan.bigevent.mapper;

import com.github.pagehelper.Page;
import com.wanan.bigevent.pojo.Article;
import com.wanan.bigevent.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Insert("insert into article (title, content, cover_img, state, category_id, create_user, create_time, update_time) " +
            "values (#{title}, #{content}, #{coverImg}, #{state}, #{categoryId}, #{createUser}, now(), now())")
    void add(Article article);

//    Page<Article> list(Integer userId, Integer categoryId, String state);

    List<Article> list(Integer userId, Integer categoryId, String state);
}

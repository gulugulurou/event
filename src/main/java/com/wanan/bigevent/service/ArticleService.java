package com.wanan.bigevent.service;

import com.wanan.bigevent.pojo.Article;
import com.wanan.bigevent.pojo.PageBean;

public interface ArticleService {

    void addArticle(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}

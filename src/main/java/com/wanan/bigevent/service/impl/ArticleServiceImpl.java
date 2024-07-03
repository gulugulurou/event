package com.wanan.bigevent.service.impl;

import com.wanan.bigevent.mapper.ArticleMapper;
import com.wanan.bigevent.pojo.Article;
import com.wanan.bigevent.service.ArticleService;
import com.wanan.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {
        Map<String, Object> userMap = ThreadLocalUtil.get();
        Integer userId = (Integer) userMap.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }
}

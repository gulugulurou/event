package com.wanan.bigevent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wanan.bigevent.mapper.ArticleMapper;
import com.wanan.bigevent.pojo.Article;
import com.wanan.bigevent.pojo.PageBean;
import com.wanan.bigevent.service.ArticleService;
import com.wanan.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
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

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pageBean = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> userMap = ThreadLocalUtil.get();
        Integer userId = (Integer) userMap.get("id");
        List<Article> list = articleMapper.list(userId, categoryId, state);
        Page<Article> page = ( Page<Article>) list;
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());
        return pageBean;
    }
}

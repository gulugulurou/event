package com.wanan.bigevent.controller;

import com.wanan.bigevent.pojo.Article;
import com.wanan.bigevent.pojo.PageBean;
import com.wanan.bigevent.pojo.Result;
import com.wanan.bigevent.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/add")
    public Result addArticle(@RequestBody @Validated Article article) {
        articleService.addArticle(article);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> page = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(page);
    }
}

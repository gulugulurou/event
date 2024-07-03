package com.wanan.bigevent.controller;

import com.wanan.bigevent.pojo.Article;
import com.wanan.bigevent.pojo.Result;
import com.wanan.bigevent.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("/add")
    public Result addArticle(@RequestBody Article article) {
        articleService.addArticle(article);
        return Result.success();
    }
    @GetMapping("/list")
    public Result<String> list() {
        return Result.success("所有文章清单......");
    }
}

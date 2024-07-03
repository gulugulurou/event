package com.wanan.bigevent.controller;

import com.wanan.bigevent.pojo.Category;
import com.wanan.bigevent.pojo.Result;
import com.wanan.bigevent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Result add(@RequestBody @Validated(value = {Category.Add.class}) Category category) {
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Category>> list() {
        return Result.success(categoryService.list());
    }

    @GetMapping("/detail")
    public Result<Category> detail(@RequestParam("id") int id) {
        return Result.success(categoryService.findById(id));
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated(value = {Category.Update.class}) Category category) {
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam("id") int id) {
        if (id <= 0) {
            return Result.error("入参错误");
        }
        categoryService.deleteById(id);
        return Result.success();
    }
}

package com.itcase.automall.controller.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcase.automall.bll.Impl.AbsSuperService;
import com.itcase.automall.controller.inter.ICategoryController;
import com.itcase.automall.entity.Category;
import com.itcase.automall.utils.HttpResult;
import com.itcase.automall.utils.snowflake.IdGeneratorSnowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/category")
public class CategoryControllerImpl extends AbsSuperController implements ICategoryController {

    @Autowired
    private Category category;

    @Autowired
    @Qualifier("categoryServiceImpl")
    private AbsSuperService categoryService;

    @Override
    public AbsSuperService getBll() {
        return categoryService;
    }

    //根据id删除汽车类别
    @DeleteMapping("/delCategory/{id}")
    public String delCategory(@PathVariable("id") Long id) throws IOException {
        category.setId(id);
        getBll().setModel(category);
        HttpResult httpResult = getBll().delete();
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    //多条件联合查询汽车及类别
    @GetMapping("/findCategory/{pageNumber}/{rowsCount}/{series}/{genre}")
    public String findCategory(@PathVariable("pageNumber") Integer pageNumber,
                               @PathVariable("rowsCount") Integer rowsCount,
                               @PathVariable("series") String series,
                               @PathVariable("genre") String genre) throws IOException {
        HashMap<String, Object> cons = new HashMap<>();
        if (!"null".equals(series) && series != null &&
                !"".equals(series))
            cons.put("series",series);
        if (!"null".equals(genre) && genre != null &&
                !"".equals(genre))
            cons.put("genre",genre);
        HttpResult httpResult = getBll().findByPage(cons, pageNumber, rowsCount);
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    //新增汽车类别
    @PostMapping("/saveCategory")
    public String saveCategory(@RequestBody String categoryStr) throws IOException {
        Category cate = new ObjectMapper().readValue(categoryStr, Category.class);
        cate.setId(new IdGeneratorSnowflake().snowflakeId());
        getBll().setModel(cate);
        HttpResult httpResult = getBll().save();
        return new ObjectMapper().writeValueAsString(httpResult);
    }

    //修改汽车类别
    @PutMapping("/editCategory")
    public String editCategory(@RequestBody String categoryStr) throws IOException {
        Category cate = new ObjectMapper().readValue(categoryStr, Category.class);
        getBll().setModel(cate);
        HttpResult httpResult = getBll().update();
        return new ObjectMapper().writeValueAsString(httpResult);
    }
}

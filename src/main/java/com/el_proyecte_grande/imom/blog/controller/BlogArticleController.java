package com.el_proyecte_grande.imom.blog.controller;
import com.el_proyecte_grande.imom.blog.model.BlogArticle;
import com.el_proyecte_grande.imom.blog.service.BlogArticleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
public class BlogArticleController {

    private final BlogArticleService blogArticleService;

    public BlogArticleController(BlogArticleService blogArticleService) {
        this.blogArticleService = blogArticleService;
    }


    @CrossOrigin
    @ApiOperation("Operation to list all blog articles ordered by date")
    @GetMapping("/blog/articles")
    public List<BlogArticle> getAllArticlesByDateDesc() {
        log.info("Log Message: ");
        return blogArticleService.getAllArticlesByDateDesc();
    }

    @CrossOrigin
    @ApiOperation("Operation to get blog article by id")
    @GetMapping("/blog/article/{id}")
    public BlogArticle getArticlesById(@PathVariable("id") Long id) {
        log.info("Log Message: ");
        return blogArticleService.getArticlesById(id);
    }

    @CrossOrigin
    @ApiOperation("Operation to list blog articles ordered by category")
    @GetMapping("/blog/articles/{category}")
    public List<BlogArticle> getArticlesByCategory(@PathVariable("category") String category) {
        log.info("Log Message: ");
        return blogArticleService.getArticlesByCategory(category);
    }

    @CrossOrigin
    @ApiOperation("Operation to return one article - article of the day")
    @GetMapping("/blog/article-of-the-day")
    public BlogArticle getArticleOfTheDay() {
        log.info("Log Message: ");
        return blogArticleService.getArticleOfTheDay();
    }

}

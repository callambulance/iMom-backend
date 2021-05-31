package com.el_proyecte_grande.imom.blog.service;
import com.el_proyecte_grande.imom.blog.model.BlogArticle;
import com.el_proyecte_grande.imom.blog.repository.BlogArticleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogArticleService {
    private final BlogArticleRepository blogArticleRepository;

    public BlogArticleService(BlogArticleRepository blogArticleRepository) {
        this.blogArticleRepository = blogArticleRepository;
    }

    public List<BlogArticle> getAllArticlesByDateDesc() {
        return blogArticleRepository.findAllByOrderByDateDesc();
    }

    public BlogArticle getArticlesById(Long id) {
        return blogArticleRepository.findById(id).orElseThrow();
    }

    public List<BlogArticle> getArticlesByCategory(String category) {
        return blogArticleRepository.findByCategory(category);
    }


    public BlogArticle getArticleOfTheDay() {
        BlogArticle articleOfTheDay;
        do {
            long count = blogArticleRepository.count();
            double intRandom = ((Math.random() * (count - 1)) + 1);
            articleOfTheDay = blogArticleRepository.findBlogArticleById((long) intRandom);
        } while (articleOfTheDay == null);
        return articleOfTheDay;
    }


}

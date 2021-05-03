package com.el_proyecte_grande.imom.blog.repository;
import com.el_proyecte_grande.imom.blog.model.BlogArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface BlogArticleRepository extends JpaRepository<BlogArticle, Long> {
    List<BlogArticle> findAllByOrderByDateDesc();

    List<BlogArticle> findByCategory(String category);

    BlogArticle findBlogArticleById(Long intRandom);
}

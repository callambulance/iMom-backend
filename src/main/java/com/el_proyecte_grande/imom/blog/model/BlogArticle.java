package com.el_proyecte_grande.imom.blog.model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "blog_articles")
public class BlogArticle {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @ApiModelProperty(value = "Identifier")
        @Column(name = "article_id")
        private Long id;

        @ApiModelProperty(value = "Article title")
        private String title;

        @ApiModelProperty(value = "Text of article")
        private String text;

        @ApiModelProperty(value = "Article category")
        private String category;

        @ApiModelProperty(value = "Date of submission")
        private LocalDate date;
}

package spring.service;


import spring.models.Article;

import java.util.List;

public interface ArticleService {

    List<Article> findAll();
    Article save(Article article);

}

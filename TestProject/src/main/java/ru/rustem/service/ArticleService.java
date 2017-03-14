package ru.rustem.service;


import org.springframework.data.domain.Page;
import ru.rustem.models.Article;

/**
 * This interface contains two method for work
 */
public interface ArticleService {
    Page<Article> getPageNews(Integer pageNumber, Integer count);
    Article save(Article article);
}

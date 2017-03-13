package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.rustem.models.Article;
import ru.rustem.repository.ArticleRepository;


@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Page<Article> getPageNews(Integer pageNumber, Integer count) {
        PageRequest request = new PageRequest(pageNumber - 1, count, Sort.Direction.DESC, "id");
        return articleRepository.findAll(request);
    }
    public Article save(Article article) {
        return articleRepository.save(article);
    }

}

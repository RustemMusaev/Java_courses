package spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.models.Article;
import spring.repository.ArticleRepository;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> findAll() {
        return (List<Article>) articleRepository.findAll();
    }
   public Page<Article> getPageNews(Integer pageNumber, Integer count) {
        PageRequest request = new PageRequest(pageNumber - 1, count, Sort.Direction.DESC, "id");
        return articleRepository.findAll(request);
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

}

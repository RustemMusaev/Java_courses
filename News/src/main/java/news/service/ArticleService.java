package news.service;


import news.model.Article;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ArticleService {

    List<Article> findAll();
    Article save(Article article);

}

package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.rustem.models.Article;
import ru.rustem.repository.ArticleRepository;

/**
 * This class implements method ArticleService interface
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * This method pagination all find Article in database, use count rows to page. For this, it forms a query
     * to the database, specifying the page number, the number of rows, the sort option, the sorting field
     * @param pageNumber - set page for return
     * @param count - set count rows to page
     * @return Page<Article> with set parame
     */
    @Override
    public Page<Article> getPageNews(Integer pageNumber, Integer count) {
        PageRequest request = new PageRequest(pageNumber - 1, count, Sort.Direction.DESC, "id");
        return articleRepository.findAll(request);
    }

    /**
     *
     * @param article - this object(wiyhout parm "id") for save to database
     * @return this Article after succesful insert to database(with param "id")
     */
    public Article save(Article article) {
        return articleRepository.save(article);
    }

}

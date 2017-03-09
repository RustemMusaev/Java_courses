package news.repository;


import news.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}

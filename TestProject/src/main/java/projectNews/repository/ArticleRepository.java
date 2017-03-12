package projectNews.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import projectNews.models.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}

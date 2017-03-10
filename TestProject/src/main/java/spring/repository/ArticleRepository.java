package spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.models.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}

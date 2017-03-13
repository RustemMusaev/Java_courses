package ru.rustem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.rustem.models.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}

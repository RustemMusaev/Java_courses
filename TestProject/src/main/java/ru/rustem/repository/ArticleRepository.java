package ru.rustem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.rustem.models.Article;

/**
 * This interface extend JpaRepository, which the have nested CRUD methods(Spring JPA)
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}

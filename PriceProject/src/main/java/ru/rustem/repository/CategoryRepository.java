package ru.rustem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rustem.model.Category;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByNameStartingWithIgnoreCase(String searchTerm);
}

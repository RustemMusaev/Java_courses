package ru.rustem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rustem.model.Product;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAll();

    List<Product> findByNameStartingWithIgnoreCase(String searchTerm);

    List<Product> findByPriceGreaterThanEqual(double price);

    List<Product> findByPriceLessThanEqual(double price);
}

package ru.rustem.service;


import ru.rustem.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> findByNameStartingWithIgnoreCase(String searchTerm);
    List<Product> findByPriceGreaterThanEqual(double price);
    List<Product> findByPriceLessThanEqual(double price);
}

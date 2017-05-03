package ru.rustem.dao;

import ru.rustem.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();

    List<Product> findPagination(Integer first, Integer count);

    Integer save(Product product);

    Product find(Integer id);

    boolean delete(Product product);

}

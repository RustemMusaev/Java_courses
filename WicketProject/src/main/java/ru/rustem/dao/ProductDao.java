package ru.rustem.dao;

import ru.rustem.model.Product;

import java.util.List;

public interface ProductDao extends BaseDao<Product> {

    List<Product> findPagination(Integer first, Integer count);

}

package ru.rustem.dao;

import ru.rustem.model.Product;
import ru.rustem.model.UserRequest;

import java.util.List;


public interface ProductDao {
    List<Product> findAnyParam(UserRequest userRequest);
}

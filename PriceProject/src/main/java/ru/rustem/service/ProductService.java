package ru.rustem.service;


import ru.rustem.model.Product;
import ru.rustem.model.UserRequest;

import java.util.List;

public interface ProductService {
    List<Product> findAnyParam(UserRequest userRequest);
}

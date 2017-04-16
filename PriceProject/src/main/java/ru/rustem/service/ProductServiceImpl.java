package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rustem.model.Product;
import ru.rustem.model.UserRequest;
import ru.rustem.dao.ProductDao;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAnyParam(UserRequest userRequest) {
        return productDao.findAnyParam(userRequest);
    }
}

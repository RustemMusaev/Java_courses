package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rustem.dao.ProductDao;
import ru.rustem.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    public ProductServiceImpl() {
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public List<Product> findPagination(Integer first, Integer count) {
        return productDao.findPagination(first, count);
    }

    @Override
    public Integer save(Product product) {
        Double price = new BigDecimal(product.getPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue();
        product.setPrice(price);
        return productDao.save(product);
    }

    @Override
    public Product find(Integer id) {
        return productDao.find(id);
    }

    @Override
    public boolean delete(Product product) {
        return productDao.delete(product);
    }
}

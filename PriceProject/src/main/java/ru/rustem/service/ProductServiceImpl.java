package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.rustem.model.Product;
import ru.rustem.repository.ProductRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByNameStartingWithIgnoreCase(String searchTerm) {
        return productRepository.findByNameStartingWithIgnoreCase(searchTerm);
    }

    @Override
    public List<Product> findByPriceGreaterThanEqual(double price) {
        return productRepository.findByPriceGreaterThanEqual(price);
    }

    @Override
    public List<Product> findByPriceLessThanEqual(double price) {
        return productRepository.findByPriceLessThanEqual(price);
    }


}

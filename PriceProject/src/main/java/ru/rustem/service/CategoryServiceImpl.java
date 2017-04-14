package ru.rustem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rustem.model.Category;
import ru.rustem.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findByNameStartingWithIgnoreCase(String searchTerm) {
        return categoryRepository.findByNameStartingWithIgnoreCase(searchTerm);
    }
}

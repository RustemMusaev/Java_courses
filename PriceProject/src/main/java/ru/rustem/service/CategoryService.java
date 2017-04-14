package ru.rustem.service;

import ru.rustem.model.Category;

import java.util.List;


public interface CategoryService {

    List<Category> findByNameStartingWithIgnoreCase(String searchTerm);
}

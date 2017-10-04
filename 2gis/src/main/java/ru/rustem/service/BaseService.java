package ru.rustem.service;

import java.util.List;

public interface BaseService<T> {
    Integer save(T model, int id);
    T find(Integer id);
    void update(T model);
    List<T> findAll();
    int[] saveBatch(List<T> list);
}

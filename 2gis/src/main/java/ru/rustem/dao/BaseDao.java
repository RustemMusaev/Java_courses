package ru.rustem.dao;

import java.util.List;

public interface BaseDao<T> {
    Integer save(T model);
    T find(Integer id);
    void update(T model);
    List<T> findAll();
    int[] saveBatch(List<T> list);
}

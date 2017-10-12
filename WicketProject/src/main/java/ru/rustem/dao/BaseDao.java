package ru.rustem.dao;

import java.util.List;

public interface BaseDao<T> {

    List<T> findAll();

    Integer save(T t);

    T find(Integer id);

    boolean delete(T t);
}

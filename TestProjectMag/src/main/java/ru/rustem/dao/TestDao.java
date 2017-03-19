package ru.rustem.dao;

import java.util.List;

public interface TestDao {
    List<Integer> findAll();
    void deleteAll();
    boolean save(int count);
}

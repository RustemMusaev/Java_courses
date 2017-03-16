package ru.rustem.dao;

import java.util.List;
import java.util.Map;

public interface TestDao {
    List<Integer> findAll();
    boolean save(int i);
    void deleteAll();
}

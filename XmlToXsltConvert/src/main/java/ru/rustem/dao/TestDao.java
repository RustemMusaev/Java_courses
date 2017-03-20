package ru.rustem.dao;

import java.util.List;

/**
 * This Class using for initialization method for work with database.
 * Method fibdAll() - return all object on table.
 * Method deleteAll() - clear table
 * Method save() - insert the required number of rows on table
 * */
public interface TestDao {
    List<Integer> findAll();
    void deleteAll();
    boolean save(int count);
}

package ru.rustem.allMethods;

import ru.rustem.dao.TestDao;

import java.util.List;

/**
 * This class use for work in database, using TestDao.class methods. Static method takes TestDao object and int parametr
 * for usin create count numbers. At first call deleteAll() method for claer table on database. After call save() method for
 * insert need count rows in table on database. After forming table static method usin method findAll() return all value on table
 * for List<Integer>
 */
public class InsertAndSelectForDb {
    public static List<Integer> insertAndSelectForDb(TestDao testDao, int count){
        testDao.deleteAll();
        testDao.save(count);
        return testDao.findAll();
    }
}

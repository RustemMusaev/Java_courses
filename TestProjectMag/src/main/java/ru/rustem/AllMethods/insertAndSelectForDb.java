package ru.rustem.allMethods;

import ru.rustem.dao.TestDao;

import java.util.List;

public class InsertAndSelectForDb {
    public static List<Integer> insertAndSelectForDb(TestDao testDao, int count){
        testDao.deleteAll();
        testDao.save(count);
        return testDao.findAll();
    }
}

package ru.rustem.AllMethods;

import ru.rustem.dao.TestDao;

import java.util.List;

public class insertAndSelectForDb {
    public static List<Integer> insertAndSelectForDb(TestDao testDao, int count){
        testDao.deleteAll();
        int i = 0;
        while ( i < count) {
            if (testDao.save(i)){
                i++;
            } else {
                System.out.println("save error to Base");
                break;
            }
        }
        return testDao.findAll();
    }
}

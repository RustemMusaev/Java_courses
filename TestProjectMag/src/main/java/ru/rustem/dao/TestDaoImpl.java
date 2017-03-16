package ru.rustem.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;


public class TestDaoImpl implements TestDao{

    private JdbcTemplate jdbcTemplate;


    public TestDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public  List<Integer> findAll() {
        //language=SQL

        return jdbcTemplate.queryForList("SELECT * FROM testdb", Integer.class);
    }
    @Override
    public boolean save(int i) {
        if (jdbcTemplate.update("INSERT INTO testdb (column_1) VALUES (?)",i) == 1) {
            return true;
        } else return false;
    }
    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE from testdb");
    }

}

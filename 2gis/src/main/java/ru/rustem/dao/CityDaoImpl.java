package ru.rustem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.rustem.model.City;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CityDaoImpl implements CityDao {
    //language=SQL
    private final static String SQL_SELECT_ALL_CITYS =
            "SELECT * FROM citys";
    //language=SQL
    private final static String SQL_UPDATE_CITY =
            "UPDATE citys SET name = ?, area = ?, count_people = ? WHERE id = ?";
    //language=SQL
    private final static String SQL_SELECT_BY_ID =
            "SELECT * FROM citys WHERE id = ?";

    private JdbcTemplate template;

    @Autowired
    public CityDaoImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Integer save(City model) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(template);
        jdbcInsert.withTableName("citys");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", model.getId());
        params.put("name", model.getName());
        params.put("area", model.getArea());
        params.put("count_people", model.getCountPeople());
        jdbcInsert.execute(params);
        return model.getId();
    }

    @Override
    public City find(Integer id) {
        return template.queryForObject(SQL_SELECT_BY_ID, cityRowMapper, id);
    }

    @Override
    public void update(City model) {
        template.update(SQL_UPDATE_CITY, model.getName(), model.getArea(), model.getCountPeople(), model.getId());
    }

    @Override
    public List<City> findAll() {
        return template.query(SQL_SELECT_ALL_CITYS, cityRowMapper);
    }

    @Override
    public int[] saveBatch(List<City> list) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(template).withTableName("PERSON").usingGeneratedKeyColumns("id");
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list.toArray());
        int[] ints = simpleJdbcInsert.executeBatch(batch);
        return ints;
    }

    private RowMapper<City> cityRowMapper = new RowMapper<City>() {
        public City mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Double area = resultSet.getDouble("area");
            int countPeople = resultSet.getInt("count_people");
            return new City(id, name, area, countPeople);
        }
    };
}

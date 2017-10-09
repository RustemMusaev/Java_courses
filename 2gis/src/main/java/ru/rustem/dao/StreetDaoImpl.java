package ru.rustem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.rustem.model.Street;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StreetDaoImpl implements StreetDao {
    //language=SQL
    private final static String SQL_SELECT_ALL_STREETS =
            "SELECT * FROM streets";
    //language=SQL
    private final static String SQL_UPDATE_STREET =
            "UPDATE streets SET name = ?, length = ? WHERE id = ?";
    //language=SQL
    private final static String SQL_SELECT_BY_ID =
            "SELECT * FROM streets WHERE id = ?";

    private JdbcTemplate template;

    @Autowired
    public StreetDaoImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Integer save(Street model) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(template);
        jdbcInsert.withTableName("streets");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", model.getName());
        params.put("length", model.getLength());
        jdbcInsert.execute(params);
        return model.getId();
    }

    @Override
    public int[] saveBatch(List<Street> list) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(template).withTableName("PERSON").usingGeneratedKeyColumns("id");
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list.toArray());
        int[] ints = simpleJdbcInsert.executeBatch(batch);
        return ints;
    }

    @Override
    public Street find(Integer id) {
        return template.queryForObject(SQL_SELECT_BY_ID, streetRowMapper, id);
    }

    @Override
    public void update(Street model) {
        template.update(SQL_UPDATE_STREET, model.getName(), model.getLength(), model.getId());
    }

    @Override
    public List<Street> findAll() {
        return template.query(SQL_SELECT_ALL_STREETS, streetRowMapper);
    }

    private RowMapper<Street> streetRowMapper = new RowMapper<Street>() {
        public Street mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Integer length = resultSet.getInt("length");
            return new Street(id, name, length);
        }
    };
}

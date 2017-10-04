package ru.rustem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.rustem.model.City;
import ru.rustem.model.Organization;
import ru.rustem.model.Street;
import sun.util.resources.cldr.es.TimeZoneNames_es;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrganizationDaoImpl implements OrganizathionDao {
    //language=SQL
    private final static String SQL_SELECT_ALL_ORGANIZATHIONS =
            "SELECT * FROM organizations";
    //language=SQL
    private final static String SQL_UPDATE_ORGANIZATHION =
            "UPDATE organizations SET name = ?, city_id = ?, street_id = ?,"+
                    " house_number = ?, description = ?, website = ?, date_update = ? WHERE id = ?";
    //language=SQL
    private final static String SQL_SELECT_BY_ID =
            "SELECT * FROM organizations WHERE id = ?";
    //language=SQL
    private final static String SQL_SELECT_BY_NAME =
            "SELECT * FROM organizations WHERE name = ?";

    private JdbcTemplate template;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private StreetDao streetDao;

    @Autowired
    public OrganizationDaoImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Integer save(Organization model) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(template);
        jdbcInsert.withTableName("organizations").usingGeneratedKeyColumns("id");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", model.getName());
        params.put("city_id", model.getCity().getId());
        params.put("street_id", model.getStreet().getId());
        params.put("house_number", model.getHouseNumber());
        params.put("description", model.getDescription());
        params.put("website", model.getWebsite());
        params.put("date_update", new Timestamp(System.currentTimeMillis()));
        int id = jdbcInsert.executeAndReturnKey(params).intValue();
        model.setId(id);
        return id;
    }

    @Override
    public Organization find(Integer id) {
        return template.queryForObject(SQL_SELECT_BY_ID, organizationRowMapper, id);
    }

    @Override
    public void update(Organization model) {
        template.update(SQL_UPDATE_ORGANIZATHION, model.getName(), model.getCity().getId(), model.getStreet().getId(),
                model.getHouseNumber(), model.getDescription(), model.getWebsite(), new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public List<Organization> findAll() {
        return template.query(SQL_SELECT_ALL_ORGANIZATHIONS, organizationRowMapper);
    }

    @Override
    public int[] saveBatch(List<Organization> list) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(template).withTableName("ORGANIZATHION").usingGeneratedKeyColumns("id");
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list.toArray());
        int[] ints = simpleJdbcInsert.executeBatch(batch);
        return ints;
    }

    private RowMapper<Organization> organizationRowMapper = new RowMapper<Organization>() {
        public Organization mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Integer cityId = resultSet.getInt("city_id");
            Integer streetId = resultSet.getInt("street_id");
            Integer houseNumber = resultSet.getInt("hous_number");
            String description = resultSet.getString("description");
            String website = resultSet.getString("website");
            Timestamp dateUpdate = resultSet.getTimestamp("date_update");
            City city = cityDao.find(cityId);
            Street street = streetDao.find(streetId);
            return new Organization(id, name, city, street, houseNumber,description,website,dateUpdate);
        }
    };
}

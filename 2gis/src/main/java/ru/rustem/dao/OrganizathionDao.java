package ru.rustem.dao;

import ru.rustem.model.Organization;

import java.sql.Timestamp;
import java.util.List;

public interface OrganizathionDao extends BaseDao<Organization> {
    List<Organization> find(String name);
    List<Organization> findByUpdateDate(Timestamp time);
}

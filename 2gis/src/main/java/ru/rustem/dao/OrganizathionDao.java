package ru.rustem.dao;

import ru.rustem.model.Organization;

import java.util.List;

public interface OrganizathionDao extends BaseDao<Organization> {
    List<Organization> find(String name);
}

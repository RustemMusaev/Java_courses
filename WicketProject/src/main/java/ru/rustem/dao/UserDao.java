package ru.rustem.dao;


import ru.rustem.model.User;

public interface UserDao extends BaseDao<User> {
    User findByLogin(String login);
}

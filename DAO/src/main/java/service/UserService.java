package service;

import dao.UsersDao;
import models.User;

import java.util.List;
import java.util.Map;

public class UserService {

    private UsersDao usersDao;


    public UserService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public boolean IsRegistred(String name) {

        return true;
    }
}
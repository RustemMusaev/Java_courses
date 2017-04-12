package ru.rustem.service;

import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.rustem.dao.UserDao;

import static org.mockito.Mockito.*;
import static ru.rustem.data.TestData.*;


@Configuration
public class TestConfig {

    @Bean
    UserServiceImpl usersService() {
        return new UserServiceImpl(userDao());
    }

    @Bean
    UserDao userDao() {
        UserDao usersDao = Mockito.mock(UserDao.class);
        when(usersDao.findAll()).thenReturn(USERS);
        doThrow(IllegalArgumentException.class).when(usersDao).findUserByToken(Matchers.anyString());
        doReturn(RUSTEM_TOKEN).when(usersDao).findTokenByUserId(RUSTEM_USER_ID);
        return usersDao;
    }
}

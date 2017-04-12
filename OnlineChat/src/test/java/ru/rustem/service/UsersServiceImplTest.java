package ru.rustem.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.rustem.model.User;

import java.util.Set;


import static org.junit.Assert.assertEquals;
import static ru.rustem.data.TestData.RUSTEM_TOKEN;

import static ru.rustem.data.TestData.RUSTEM_USER_ID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class UsersServiceImplTest {

    @Autowired
    UserServiceImpl usersService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetAllUsers() throws Exception {
        Set<User> set = usersService.findAll();
        System.out.println("set = " + set);
    }

    @Test
    public void testGetTokenByUserId() throws Exception {
        String expected = RUSTEM_TOKEN;
        String actual = usersService.findTokenByUserId(RUSTEM_USER_ID);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserByTokenOnIncorrectData() throws Exception {
        usersService.findUserByToken("sdfsdf");
    }
}
package ru.rustem.data;

import ru.rustem.model.Message;
import ru.rustem.model.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestData {

    public static final User RUSTEM_USER = new User("rust1986","$2a$10$iVATFAOmzw2iS7/mbV2NBOiRR.N50SpUAdfAWY8er0SJs860GS3CG",
            "Rustem", "Musaev",null,"9130001151",
            "rustem.esm@gmail.com",null,"5e8ZG89pLVJgchDaD4aj");
    public static final User ERVEEN_USER = new User("erveen1986","$2a$10$iVATFAOmzw2iS7/mbV2NBOiRR.N50SpUAdfAWY8er0SJs860GS3CG",
            "Rustem", "Musaev",null,"9130001152",
            "rustem.esm@gmail.com",null,"5e8ZG89pLVJgchDaD4aj");
    public static final User OLYA_USER = new User("olya1986","$2a$10$iVATFAOmzw2iS7/mbV2NBOiRR.N50SpUAdfAWY8er0SJs860GS3CG",
            "Rustem", "Musaev",null,"9130001153",
            "rustem.esm@gmail.com",null,"5e8ZG89pLVJgchDaD4aj");

    public static final Integer RUSTEM_USER_ID = 1;
    public static final String RUSTEM_TOKEN = "5e8ZG89pLVJgchDaD4aj";
    public static final Message RUSTEM_MESSAGE = new Message("Hello people, my name is Rustem", "2017-04-01 22:00:00", RUSTEM_USER);

    private static Set<User> createUsers() {
        Set<User> set =new HashSet<User>();
        set.add(RUSTEM_USER);
        set.add(ERVEEN_USER);
        set.add(OLYA_USER);
        return set;
    }
    public static final Set<User> USERS = createUsers();

    private static List<Message> createAuto() {
        return Arrays.asList(RUSTEM_MESSAGE);
    }

}

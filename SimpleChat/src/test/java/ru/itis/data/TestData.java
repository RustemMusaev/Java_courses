package ru.itis.data;


import ru.itis.model.Chat;
import ru.itis.model.ChatUser;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestData {

    public static final ChatUser RUSTEM = new ChatUser.Builder().id(1).login("Rustem").password_hash("qweasdzxc123456789").builder();
    public static final ChatUser ERVIN = new ChatUser.Builder().id(2).login("Ervin").password_hash("qweasdzxc123456789").builder();
    public static final ChatUser DIMAN = new ChatUser.Builder().id(3).login("Diman").password_hash("qweasdzxc123456789").builder();
    public static final ChatUser DEN = new ChatUser.Builder().id(4).login("Den").password_hash("qweasdzxc123456789").builder();

    public static final Chat CHAT1 = new Chat.Builder().id(1).name("Chat1").build();
    public static final Chat CHAT2 = new Chat.Builder().id(1).name("Chat2").build();

    private static Set<ChatUser> chatUserSet() {
        return new HashSet<>(0);
    }
    public static Set<Chat> chatSet() {
        return new HashSet<>(0);
    }




}

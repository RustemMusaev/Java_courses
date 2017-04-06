package ru.rustem.converter;


import ru.rustem.dto.UserInfo;
import ru.rustem.model.User;

public class UserToUserInfoConverter {
    public static UserInfo userToUserInfoConverter(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setLogin(user.getLogin());
        userInfo.setName(user.getName());
        userInfo.setEmail(user.getEmail());
        userInfo.setPhone(user.getPhone());
        userInfo.setSurname(user.getSurname());
        userInfo.setPhoto(user.getPhoto());
        return userInfo;
    }
}

package ru.itis.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.itis.dao.ChatUsersDao;
import ru.itis.dao.ChatsDao;
import ru.itis.dao.SessionDao;
import ru.itis.model.ChatUser;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailsService - интерфейс, описывающий логику работы
 * с данными по безопасности
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SessionDao sessionDao;

    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        if (sessionDao.isExistsToken(token)) {
            ChatUser chatUser = sessionDao.findUserByToken(token);
            List<GrantedAuthority> authorities = createGrantedAuthorities();

            return new UserDetailsImpl(chatUser.getLogin(), chatUser.getPassword_hash(), authorities);
        } else {
            return null;
        }
    }

    public static List<GrantedAuthority> createGrantedAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));//можно задать разные роли
        return authorities;
    }
}

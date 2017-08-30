package ru.rustem.rambler.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rustem.rambler.dao.PasswordDao;
import ru.rustem.rambler.dao.UserDao;
import ru.rustem.rambler.exception.CustomException;
import ru.rustem.rambler.models.Password;
import ru.rustem.rambler.models.User;
import ru.rustem.rambler.util.EncodeMD5;
import ru.rustem.rambler.util.EncodeSHA1;
import ru.rustem.rambler.util.EncoreMethod;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordDao passwordDao;
    private static final String SALT = "123456789";

    @Override
    public User findByLogin(String login) throws CustomException {
        return userDao.findByLogin(login);
    }

    @Override
    public boolean passwordIsCorrect(Integer userId, Integer passwordId, String pwd) throws CustomException {
        Password password = passwordDao.findById(passwordId);
        String databasePassword = passwordDao.findPasswordUseFile(userId) + password.getPassword();
        String inputPassword = generatePassword(pwd + SALT, password.getEncryption());
        return databasePassword.equals(inputPassword);
    }

    private String generatePassword(String pwd, String encryption) throws CustomException {
        String first = DigestUtils.md5Hex(pwd.substring(0, (int) (pwd.length() / 2)));
        EncoreMethod encoder;
        if (encryption.equals("SHA1")) {
            encoder = new EncodeSHA1();
        } else if (encryption.equals("MD5")) {
            encoder = new EncodeMD5();
        } else {
            throw new CustomException("unregister encrypt method");
        }
        String second = encoder.encode(pwd.substring((int) (pwd.length() / 2), pwd.length()));
        return first + second;
    }
}

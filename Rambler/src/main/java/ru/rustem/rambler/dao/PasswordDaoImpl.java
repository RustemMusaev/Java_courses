package ru.rustem.rambler.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.rustem.rambler.exception.CustomException;
import ru.rustem.rambler.models.Password;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@PropertySource("classpath:application.properties")
@Repository
@Transactional
public class PasswordDaoImpl implements PasswordDao {

    private SessionFactory sessionFactory;
    @Value("${file.folder}")
    private String folder;

    @Autowired
    public PasswordDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Password findById(Integer id) {
        return getSession().createQuery("FROM Password pwd where pwd.id = :id", Password.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public String findPasswordUseFile(Integer userId) throws CustomException {
        StringBuilder passwordHash = new StringBuilder();
        String fileName = folder + "ID" + userId + "MD5";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                passwordHash.append(line);
            }
        } catch (IOException e) {
            throw new CustomException("error read file = " + fileName);
        }
        return String.valueOf(passwordHash);
    }

    private Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
}

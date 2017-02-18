package ru.itis.server.services;

import org.springframework.stereotype.Service;
import ru.itis.server.models.User;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface UserService {
    @WebMethod
    User findOne(Integer id);
    @WebMethod
    List<User> findAll();
    @WebMethod
    User save(User user);
    @WebMethod
    void delete(Integer id);
    }
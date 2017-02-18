package ru.itis.server.services;

import ru.itis.server.models.Auto;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface AutoService {
    @WebMethod
    Auto findOne(Integer id);
    @WebMethod
    List<Auto> findAll();
    @WebMethod
    Auto save(Auto auto);
    @WebMethod
    void delete(Integer id);
}


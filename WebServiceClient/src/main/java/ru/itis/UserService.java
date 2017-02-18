
package ru.itis;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "UserService", targetNamespace = "http://services.server.itis.ru/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserService {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "delete", targetNamespace = "http://services.server.itis.ru/", className = "ru.itis.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://services.server.itis.ru/", className = "ru.itis.DeleteResponse")
    @Action(input = "http://services.server.itis.ru/UserService/deleteRequest", output = "http://services.server.itis.ru/UserService/deleteResponse")
    public void delete(
        @WebParam(name = "arg0", targetNamespace = "")
        Integer arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns ru.itis.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "save", targetNamespace = "http://services.server.itis.ru/", className = "ru.itis.Save")
    @ResponseWrapper(localName = "saveResponse", targetNamespace = "http://services.server.itis.ru/", className = "ru.itis.SaveResponse")
    @Action(input = "http://services.server.itis.ru/UserService/saveRequest", output = "http://services.server.itis.ru/UserService/saveResponse")
    public User save(
        @WebParam(name = "arg0", targetNamespace = "")
        User arg0);

    /**
     * 
     * @return
     *     returns java.util.List<ru.itis.User>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://services.server.itis.ru/", className = "ru.itis.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://services.server.itis.ru/", className = "ru.itis.FindAllResponse")
    @Action(input = "http://services.server.itis.ru/UserService/findAllRequest", output = "http://services.server.itis.ru/UserService/findAllResponse")
    public List<User> findAll();

    /**
     * 
     * @param arg0
     * @return
     *     returns ru.itis.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findOne", targetNamespace = "http://services.server.itis.ru/", className = "ru.itis.FindOne")
    @ResponseWrapper(localName = "findOneResponse", targetNamespace = "http://services.server.itis.ru/", className = "ru.itis.FindOneResponse")
    @Action(input = "http://services.server.itis.ru/UserService/findOneRequest", output = "http://services.server.itis.ru/UserService/findOneResponse")
    public User findOne(
        @WebParam(name = "arg0", targetNamespace = "")
        Integer arg0);

}
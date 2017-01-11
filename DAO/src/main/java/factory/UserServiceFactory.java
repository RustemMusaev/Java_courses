package factory;

import service.UserService;

public class UserServiceFactory {
    private static UserServiceFactory instanse;
    private UserService userService;

    static {
        instanse=new UserServiceFactory();
    }
    public static UserServiceFactory getInstanse(){
        return instanse;
    }
    public UserService getUserService(){
        return userService;
    }

    private UserServiceFactory(){

    }
}

package ru.itis;/**
 * Created by musaevrr on 15.02.2017.
 */
public class Client {
  public static void main(String[] argv) {
      UserService userService = new UserServiceImplService().getUserServiceImplPort();

      System.out.println(userService.findOne(1));
  }
}

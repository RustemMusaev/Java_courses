import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.StrictMath.pow;


public class main {
    public static void main(String[] args) {
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        String password_hash=encoder.encode("marsel");
        System.out.println(password_hash);
        if (encoder.matches("marsel",password_hash)){
            System.out.println(" result ");
        } else {
            System.out.println(password_hash);
            System.out.println(encoder.encode("marsel"));
        }
}
    public  static class A{
        void er(){

        };
    }
    public static class B extends A{
        void er(){

        };
    }
}

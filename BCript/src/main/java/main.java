import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class main {
    public static void main(String[] args) {
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        String password_hash=encoder.encode("marsel");
        if (encoder.matches("marsel",password_hash)){
            System.out.println(" result ");
        } else {
            System.out.println(password_hash);
            System.out.println(encoder.encode("marsel"));
        }

        class E{
            E next;
            Integer value;
        }

        while(list.next!=0)






    }
}

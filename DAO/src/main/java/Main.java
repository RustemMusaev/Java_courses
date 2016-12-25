import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Main {

    public static void main(String[] args) {
        String userFileName="C:\\Users\\musaevrr\\Desktop\\JAVA\\Java_courses\\DAO\\users.txt";
        String carsFileName="C:\\Users\\musaevrr\\Desktop\\JAVA\\Java_courses\\DAO\\cars.txt";
        final User MASHA= new User(43, "MASHA", 22);
        final User DASHA  = new User(34, "Dasha", 22);
        final User RUSTEM = new User(30,"rustem",30);
        final List<User> USER_LIST= Arrays.asList(MASHA,DASHA,RUSTEM);


        UsersDao usersDao=new UsersDaoFileBasedImpl(userFileName,carsFileName);
        User masha  = new User(104, "MancvxA", 51);
        boolean result=usersDao.update(masha);
        System.out.println(result);
        UserService userService=new UserService(usersDao);
        userService.addUser(MASHA);

        System.out.println(userService.finusertolist(30,USER_LIST).getName());


        // User user=usersDao.find(141);
        //System.out.println(carsDao.find(12).getColor());
        //System.out.println(user.getName());
        //usersDao.printmycarslist(user);

        //System.out.println(usersDao.find(13).getName());
        //usersDao.delete(13);
    }
}

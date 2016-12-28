import javax.jws.soap.SOAPBinding;
import java.util.List;

public class UserService {

    private  UsersDao usersDao;


    public UserService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public boolean IsRegistred(String name) {
        List<User> userList=usersDao.findAll();
        boolean result=false;
        if (userList!=null){
            for(User user:userList) {
                if (user.getName()==name) {
                    result=true;
                } else {
                    result=false;
                }
            }
        } else {
            result=false;
        }
        return result;
    }
}
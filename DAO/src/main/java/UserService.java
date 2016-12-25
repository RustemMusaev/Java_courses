import javax.jws.soap.SOAPBinding;
import java.util.List;

public class UserService {

    private  UsersDao usersDao;


    public UserService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public void addUser(User user) {
        user.status="Add";
        usersDao.save(user);
        usersDao.save(user);
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

    public String serviceuserfind(int id) {
        return  usersDao.userfind(id);
    }

    public User finusertolist (int id, List<User> userList) {
        return usersDao.findusertolist(id, userList);
    }

}
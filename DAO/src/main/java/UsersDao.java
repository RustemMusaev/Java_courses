import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UsersDao {
    User find(int id);
    List<User> findAll();
    boolean save(User user);
    boolean update(User user);
    boolean delete(int id);
    public void printmycarslist(User users);
    public List<Cars> getUserCars(int user_id);
}
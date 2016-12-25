import javax.jws.soap.SOAPBinding;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public  class UsersDaoFileBasedImpl implements UsersDao {

    String usersFileName;//="C:\\Users\\musaevrr\\Desktop\\JAVA\\userdao\\users.txt";
    String carsFileName;
    WriteReadFile file = new WriteReadFile();

    UsersDaoFileBasedImpl(String usersFileName, String carsFileName) {
        this.usersFileName = usersFileName;
        this.carsFileName = carsFileName;
    }

    public List<User> findAll() {
        List<User> userlist = new ArrayList<User>();
        userlist = file.ReadUsersFile(usersFileName);
        for (User user : userlist) {
            // System.out.println("count");
            user.setMycars(getUserCars(user.getId(), new CarsDaoFileBasedImpl(carsFileName).findAll()));
        }
        return userlist;
    }

    public User find(int id) {
        List<User> userList = this.findAll();
        User finduser = null;
        for (User usersearch : userList) {
            if (id == usersearch.getId()) {
                finduser = usersearch;
            }
        }
        if (finduser != null) {
            return finduser;
        } else {

            System.out.println("Not find user");
            return null;
        }
    }

    public String userfind(int id) {
        return find(id).getName();
    }

    public boolean save(User user) {
        List<User> userList = this.findAll();
        int count = 0;
        for (User usersearch : userList) {
            if (user.getId() == usersearch.getId()) {
                count++;
            }
        }
        if (count == 0) {
            userList.add(user);
            file.WriteUsersFile(userList, usersFileName);
            System.out.println("Добавлен новый User");
        } else {
            System.out.println("Не уникальный id");
            return false;
        }
        return true;
    }

    public boolean update(User user) {
        List<User> userList = findAll();
        int count = 0;
        for (User usersearch : userList) {
            if (user.getId() == usersearch.getId()) {
                usersearch.setAge(user.getAge());
                usersearch.setName(user.getName());
                count++;
            }
        }
        if (count > 0) {
            file.WriteUsersFile(userList, usersFileName);
            return true;
        } else {
            return false;
        }

    }

    public boolean delete(int id) {
        List<User> userList = this.findAll();
        int indexremove = -1;
        for (User usersearch : userList) {
            if (usersearch.getId() == id) {
                indexremove = userList.indexOf(usersearch);
            }
        }
        if (indexremove != -1) {
            userList.remove(indexremove);
            System.out.println("Delete complete");
            file.WriteUsersFile(userList, usersFileName);
        } else {
            System.out.println("id не найден");
            return false;
        }
        return true;
    }

    public List<Cars> getUserCars(int id, List<Cars> carsList) {
        //   System.out.println("id "+id);
        List<Cars> mycarslist = new ArrayList<Cars>();
        //if(mycarslist==null)
        int count = 0;
        for (Cars cars : carsList) {
            //     System.out.println(carsList.size());
            if (cars.getId_user() == id) {
                mycarslist.add(cars);
                //System.out.println("Cars found");
                count++;
            }
        }
        return mycarslist;
    }

    public void printmycarslist(User user) {
        for (Cars car : user.getMycars()) {
            System.out.println("id cars=" + car.getId() + "\tmodel=" + car.getModel() + "\tcolor=" + car.getColor() + "\tid user=" + car.getId_user());
        }
    }

    public User findusertolist(int id,List<User> userList) {
        User count=null;
        for(User temp:userList) {
            if (temp.getId()==id){
                count=temp;
            }
        }
        if (count==null){
            return  null;
        }
        return count;
    }
}
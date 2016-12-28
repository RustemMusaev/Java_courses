import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public  class UsersDaoFileBasedImpl implements UsersDao {

    String usersFileName;
    String carsFileName="C:\\\\Users\\\\musaevrr\\\\Desktop\\\\JAVA\\\\Java_courses\\\\DAO\\\\cars.txt";
    final String SEPARATOR="\t";

    public UsersDaoFileBasedImpl(String usersFileName) {
        this.usersFileName = usersFileName;
    }
    public List<User> findAll() {
        List<User> userlist = new ArrayList<User>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(usersFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userdata=line.split("\t");
                User user=new User(Integer.parseInt(userdata[0]),userdata[1],Integer.parseInt(userdata[2]));
                userlist.add(user);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        for (User user : userlist) {
            user.setMycars(getUserCars(user.getId()));
        }
        System.out.println("complete");
        return userlist;
    }
    public User find(int id) {
        User finduser = null;
        //try {
        System.out.println("33");
            List<User> userList = this.findAll();
            for (User usersearch : userList) {
                System.out.println(usersearch);
                if (id == usersearch.getId()) {
                    finduser = usersearch;
                }
            }
     //   }
      //  catch (IllegalArgumentException e) {
      //      System.out.println("Error");
     //       e.printStackTrace();
    //    }
        System.out.println("44");
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
            WriteUsersFile(userList, usersFileName);
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
            WriteUsersFile(userList, usersFileName);
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
            WriteUsersFile(userList, usersFileName);
        } else {
            System.out.println("id не найден");
            return false;
        }
        return true;
    }

    public List<Cars> getUserCars(int user_id) {
        //   System.out.println("id "+id);
        List<Cars> mycarslist = new ArrayList<Cars>();
        CarsDaoFileBasedImpl carsDaoFileBased=new CarsDaoFileBasedImpl(carsFileName);
        mycarslist=carsDaoFileBased.findAll();
        for (Cars cars : mycarslist) {
            if (cars.getId_user() == user_id) {
                mycarslist.add(cars);
           }
        }
        return mycarslist;
    }

    public void printmycarslist(User user) {
        for (Cars car : user.getMycars()) {
            System.out.println("id cars=" + car.getId() + "\tmodel=" + car.getModel() + "\tcolor=" + car.getColor() + "\tid user=" + car.getId_user());
        }
    }

    public  void WriteUsersFile(List<User> userList,String usersFileName){
        try(FileWriter writer = new FileWriter(usersFileName, false))
        {
            for(User user:userList) {
                String text = user.getId() + SEPARATOR + user.getName() + SEPARATOR + user.getAge() + System.getProperty("line.separator");
                writer.write(text);
            }
        } catch (IOException ex){
            throw new IllegalArgumentException();
        }
    }
}
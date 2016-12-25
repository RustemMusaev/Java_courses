import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteReadFile {

    final String SEPARATOR="\t";

    public List<User> ReadUsersFile(String usersFileName){
        List<User> userlist=new ArrayList<User>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(usersFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userdata=line.split("\t");
                User user=new User(Integer.parseInt(userdata[0]),userdata[1],Integer.parseInt(userdata[2]));
                userlist.add(user);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return  userlist;
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

    public  void WriteCarsFile(List<Cars> carsList,String carsFileName){
        try(FileWriter writer = new FileWriter(carsFileName, false))
        {
            for(Cars cars:carsList) {
                String text = cars.getId()+SEPARATOR+cars.getModel()+SEPARATOR+cars.getColor()+SEPARATOR+cars.getId_user()+System.getProperty("line.separator");
                writer.write(text);
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<Cars> ReadCarsFile(String carsFileName){
        List<Cars> carsList=new ArrayList<Cars>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(carsFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] carsdata=line.split("\t");
                Cars cars=new Cars(Integer.parseInt(carsdata[0]),carsdata[1],carsdata[2],Integer.parseInt(carsdata[3]));
                carsList.add(cars);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        return carsList;
    }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CarsDaoFileBasedImpl implements CarsDao{

    String carsFileName;
    final String SEPARATOR="\t";

    CarsDaoFileBasedImpl(String carsFileName) {
        this.carsFileName = carsFileName;
    }
    public List<Cars> findAll() {
        List<Cars> carslist = new ArrayList<Cars>();
    //    List<Cars> carsList=new ArrayList<Cars>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(carsFileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] carsdata=line.split("\t");
                Cars cars=new Cars(Integer.parseInt(carsdata[0]),carsdata[1],carsdata[2],Integer.parseInt(carsdata[3]));
                carslist.add(cars);
            }
        } catch (Exception e) {
           // throw new IllegalArgumentException();
        }
         return carslist;
    }
    public Cars find(int id){
        List<Cars> carsList=this.findAll();
        Cars findcars = null;
        for (Cars cars:carsList){
            if(id==cars.getId()) {
                findcars=cars;
            }
        }
        return findcars;
    }
    public  boolean save(Cars cars){
        List<Cars> carsList=this.findAll();
        int count=0;
        for (Cars carssearch:carsList) {
            if (cars.getId() == carssearch.getId()) {
                count++;
            }
        }
        if(count==0) {
            carsList.add(cars);
            WriteCarsFile(carsList,carsFileName);
            System.out.println("Добавлен новый Cars");
        } else {
            System.out.println("Не уникальный id");
        }
        return true;
    }
    public  boolean update(Cars cars){
        List<Cars> carsList=this.findAll();
        for (Cars carssearch:carsList){
            if(cars.getId()==carssearch.getId()) {
                carssearch.setModel(cars.getModel());
                carssearch.setColor(cars.getColor());
                carssearch.setId_user(cars.getId_user());
            }
        }
        WriteCarsFile(carsList,carsFileName);
        return true;
    }
    public  boolean delete(int id){
        List<Cars> carsList=this.findAll();
        int indexremove=-1;
        for (Cars carssearch:carsList){
            if(carssearch.getId()==id){
                indexremove=carsList.indexOf(carssearch);
            }
        }
        if(indexremove!=-1){
            carsList.remove(indexremove);
            System.out.println("Delete complete");
            WriteCarsFile(carsList,carsFileName);
        } else {
            System.out.println("id не найден");
        }
        return true;
    }

    public  void WriteCarsFile(List<Cars> carsList,String carsFileName){
        try(FileWriter writer = new FileWriter(carsFileName, false))
        {
            for(Cars cars:carsList) {
                String text = cars.getId() + SEPARATOR + cars.getModel() + SEPARATOR + cars.getColor() +SEPARATOR + cars.getId_user() + System.getProperty("line.separator");
                writer.write(text);
            }
        } catch (IOException ex){
            throw new IllegalArgumentException();
        }
    }
    }

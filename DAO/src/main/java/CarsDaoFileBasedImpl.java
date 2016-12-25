import sun.util.resources.cldr.aa.CurrencyNames_aa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CarsDaoFileBasedImpl implements CarsDao{

    String carsFileName;//="C:\\Users\\musaevrr\\Desktop\\JAVA\\userdao\\cars.txt";
    WriteReadFile file=new WriteReadFile();

    CarsDaoFileBasedImpl (String carsFileName) {
        this.carsFileName=carsFileName;
    }

    public List<Cars> findAll() {
        List<Cars> carslist = new ArrayList<Cars>();
        carslist=file.ReadCarsFile(carsFileName);
        // System.out.println("rr");
        return carslist;
    }

    public  Cars find(int id){
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
            file.WriteCarsFile(carsList,carsFileName);
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
        file.WriteCarsFile(carsList,carsFileName);
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
            file.WriteCarsFile(carsList,carsFileName);
        } else {
            System.out.println("id не найден");
        }
        return true;
    }

}
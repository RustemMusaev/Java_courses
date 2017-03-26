package ru.rustem;

import ru.rustem.dao.TestDao;
import ru.rustem.dao.TestDaoImpl;

import javax.xml.transform.TransformerException;
import java.util.List;

import static ru.rustem.staticMethods.CreateXmlWithJAXB.createXmlWithJAXB;
import static ru.rustem.staticMethods.InsertAndSelectForDb.insertAndSelectForDb;
import static ru.rustem.staticMethods.ParseXMLtoSumWithXMLStreamReader.parseXMLtoSumWithXMLStreamReader;
import static ru.rustem.staticMethods.TransformXmlWithXstl.transformXmlWithXstl;

public class MainBean {
    int count;
    String propertiesFileName;

    public MainBean() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPropertiesFileName() {
        return propertiesFileName;
    }

    public void setPropertiesFileName(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
    }

    public void start(){
        TestDao testDao = new TestDaoImpl(propertiesFileName);
        List<Integer> list = insertAndSelectForDb(testDao, count);
        createXmlWithJAXB(list);
        try {
            transformXmlWithXstl();
        } catch (TransformerException e) {
            System.out.println("TransformerException");;
        }
        parseXMLtoSumWithXMLStreamReader();
    }
}

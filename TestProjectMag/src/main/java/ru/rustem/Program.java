package ru.rustem;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.xml.sax.SAXException;
import ru.rustem.config.SpringConfig;
import ru.rustem.dao.TestDao;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import java.io.IOException;
import java.util.List;

import static ru.rustem.AllMethods.createXMLfile.createXMLfile;
import static ru.rustem.AllMethods.insertAndSelectForDb.insertAndSelectForDb;
import static ru.rustem.AllMethods.parseXMLtoSumAttribute.parseXMLtoSumAttribute;
import static ru.rustem.AllMethods.transformXmlWithXstl.transformXmlWithXstl;

public class Program {
    public static void main(String[] args) throws TransformerException, ParserConfigurationException, IOException, SAXException {
        int count = 200;
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        TestDao testDao = (TestDao) context.getBean("TestDao");
        long startTime = System.nanoTime();
        List<Integer> list = insertAndSelectForDb(testDao, count);
        createXMLfile(list);
        transformXmlWithXstl();
        parseXMLtoSumAttribute();
        long finishTime = System.nanoTime();
        System.out.println((finishTime-startTime)/1000000000);
     }





}
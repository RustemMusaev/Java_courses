package ru.rustem;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static final String XML_FILE_TO_WRITE = "\\JavaProject\\Java_courses\\XmlToXsltConvert\\1.xml";
    public static final String XML_FILE_TO_READ = "\\JavaProject\\Java_courses\\XmlToXsltConvert\\2.xml";
    public static final String XSLT_TEMPLATE = "\\JavaProject\\Java_courses\\XmlToXsltConvert\\style.xslt";
    public static final String PROPERTIES_FILE_NAME = "\\JavaProject\\Java_courses\\XmlToXsltConvert\\src\\main\\resources\\contex.properties";

    public static void main(String[] args) throws IOException, SAXException {
        int count = 100;
        System.out.println("Enter the count ( >0 ) :");
        Scanner in = new Scanner(System.in);
        try {
            if (in.nextInt() > 0 ) {
                count = in.nextInt();
            } else {
                System.out.println("You entered an incorrect argument, by default count = " + count);
            }
        } catch (InputMismatchException e) {
            System.out.println("You entered an incorrect argument, by default count = " + count);
        }
        MainBean mainBean = new MainBean();
        mainBean.setCount(count);
        mainBean.setPropertiesFileName(PROPERTIES_FILE_NAME);
        long startTime = System.nanoTime();
        mainBean.start();
        long finishTime = System.nanoTime();
        System.out.println("time = "+(finishTime-startTime)/1000000000);
    }
}
package ru.rustem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.rustem.config.SpringConfig;
import ru.rustem.model.Point;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

import static ru.rustem.parse.CreateXMLfileWithXMLStreamWriter.createXMLfileWithXMLStreamWriter;
import static ru.rustem.parse.ParseXMLtoSumWithXMLStreamReader.parseXMLtoSumWithXMLStreamReader;


@SpringBootApplication
@ComponentScan("ru.rustem")
public class Application {
    public static final String XML_FILE_TO_WRITE =
            "D:\\1.xml";
    public static final String XML_FILE_TO_READ =
            "D:\\1.xml";
    public static void main(String[] args) {
        {
            Point point = new Point("1","2","3","4","5","6");
            List<Point> points = new ArrayList<Point>();
            points.add(point);
            points.add(point);
            try {
                createXMLfileWithXMLStreamWriter(points);
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
            parseXMLtoSumWithXMLStreamReader();
            SpringApplication.run(SpringConfig.class, args);
        }
    }
}

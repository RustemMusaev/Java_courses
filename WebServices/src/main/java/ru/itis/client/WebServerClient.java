package ru.itis.client;

import ru.itis.server.WebServer;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class WebServerClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url=new URL("http://localhost:7878/ws/hello?wsdl");
        QName qName=new QName("http://server.itis.ru/", "WebSerberImplService");
        Service service=Service.create(url,qName);
        WebServer webServer=service.getPort(WebServer.class);
        System.out.println(webServer.getHelloWordAsString("message HELLO"));
    }
}

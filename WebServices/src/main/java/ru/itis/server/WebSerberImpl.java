package ru.itis.server;

import javax.jws.WebService;

@WebService(endpointInterface = "ru.itis.server.WebServer")
public class WebSerberImpl implements WebServer {

    @Override
    public String getHelloWordAsString(String name) {
        return "My first webserver aplication" + name;
    }
}

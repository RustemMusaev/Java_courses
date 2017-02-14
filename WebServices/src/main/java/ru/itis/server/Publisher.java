package ru.itis.server;

import javax.xml.ws.Endpoint;

/**
 * Created by musaevrr on 14.02.2017.
 */
public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:7878/ws/hello", new WebSerberImpl());
    }
}

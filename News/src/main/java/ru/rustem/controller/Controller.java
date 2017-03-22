package ru.rustem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import ru.rustem.model.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class Controller {

    @PostMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> returnXml(HttpServletRequest request) {
        System.out.println(request.getRequestURI());
        System.out.println(request.getParameterMap());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ModelXml> returnXml(@RequestParam Map<String,String> param, HttpServletRequest request) {
        List<XmlData> numericParameters = new LinkedList<>();
        List<XmlData> stringParameters = new LinkedList<>();
        System.out.println(request.getRequestURI());

        for (Map.Entry<String, String> entry : param.entrySet()) {
            if (valueIsStringParametrs(entry.getValue())) {
                XmlData xmlDataString = new XmlData();
                xmlDataString.setName(entry.getKey());
                xmlDataString.setValue(entry.getValue());
                stringParameters.add(xmlDataString);
            } else {
                XmlData xmlDataNumeric = new XmlData();
                xmlDataNumeric.setName(entry.getKey());
                xmlDataNumeric.setValue(entry.getValue());
                numericParameters.add(xmlDataNumeric);
            }
        }

        ModelXml modelXml = new ModelXml();
        ClientInfo clientInfo = new ClientInfo();
        String browserName = request.getHeader("User-Agent");
        String ipAdd = request.getRemoteAddr();
        clientInfo.setIpAddress(ipAdd);
        clientInfo.setUserAgent(browserName);
        modelXml.setClientInfo(clientInfo);

        Parametrs parametrs = new Parametrs();

        ResData resDataNumeric = new ResData();
        resDataNumeric.setData(numericParameters);
        parametrs.setNumericHashMap(resDataNumeric);

        ResData resDataString = new ResData();
        resDataString.setData(stringParameters);
        parametrs.setStringHashMap(resDataString);

        parametrs.setRequestMethod(request.getMethod());

        modelXml.setParametrs(parametrs);
        return new ResponseEntity<>(modelXml, HttpStatus.OK);
    }

    public boolean valueIsStringParametrs(String value) {
        String[] strings = value.split("[.,,]");
        System.out.println(strings.length);
        if (strings.length != 2) {
            return true;
        } else {
            if (stringIsNumeric(strings[0]) && stringIsNumeric(strings[1])) {
                return false;
            } else return true;
        }
    }

    public boolean stringIsNumeric(String string) {
        if (string.length() > 10) return false;
        String[] strings = string.split("");
        if (strings[0] == " " && strings.length == 1) return true;
        for (String s : strings){
            if (!s.matches("[0-9]")) return false;
        }
        return true;
    }
}

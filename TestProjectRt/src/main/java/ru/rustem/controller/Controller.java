package ru.rustem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.rustem.xml.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class is an controller, which handle requests
 */
@RestController
public class Controller {
    /**
     * This method handle "http://localhost:8080/xml" request
     * @param param - all parameters request
     * @param request - client request
     * @return valid Xml file
     */
    @RequestMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<RequestDetail> returnXml(@RequestParam Map<String,String> param, HttpServletRequest request) {
        List<ParameterElement> numericParameters = new LinkedList<>();
        List<ParameterElement> stringParameters = new LinkedList<>();
        System.out.println(request.getRequestURI());

        for (Map.Entry<String, String> entry : param.entrySet()) {
            if (valueIsStringParametrs(entry.getValue())) {
                ParameterElement parameterElementString = new ParameterElement();
                parameterElementString.setName(entry.getKey());
                parameterElementString.setValue(entry.getValue());
                stringParameters.add(parameterElementString);
            } else {
                ParameterElement parameterElementNumeric = new ParameterElement();
                parameterElementNumeric.setName(entry.getKey());
                parameterElementNumeric.setValue(entry.getValue());
                numericParameters.add(parameterElementNumeric);
            }
        }

        RequestDetail requestDetail = new RequestDetail();
        ClientInfo clientInfo = new ClientInfo();
        String browserName = request.getHeader("User-Agent");
        String ipAdd = request.getRemoteAddr();
        clientInfo.setIpAddress(ipAdd);
        clientInfo.setUserAgent(browserName);
        requestDetail.setClientInfo(clientInfo);

        Parametrs parametrs = new Parametrs();

        Parameter parameterNumeric = new Parameter();
        parameterNumeric.setParameter(numericParameters);
        parametrs.setNumericHashMap(parameterNumeric);

        Parameter parameterString = new Parameter();
        parameterString.setParameter(stringParameters);
        parametrs.setStringHashMap(parameterString);

        parametrs.setRequestMethod(request.getMethod());

        requestDetail.setParametrs(parametrs);
        return new ResponseEntity<>(requestDetail, HttpStatus.OK);
    }

    /**
     * This method input string and split[",","."]. If result is two string,
     * cheks every string on numeric parameter
     */
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

    /**
     * This metod check input string on numeric parameter, check all char is number
     * @param string
     * @return true if string is numeric parameter, else return false
     */
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

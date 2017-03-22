package ru.rustem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class Controller {

    @GetMapping("/news")
    public ResponseEntity<String> getAllMessages(@RequestParam Map<String, String> param) {
        Map<String, String> numericParameters = new HashMap<String, String>();
        Map<String, String> stringParameters = new HashMap<String, String>();

        for (Map.Entry<String, String> entry : param.entrySet()) {
            if (valueIsStringParametrs(entry.getValue())) {
                stringParameters.put(entry.getKey(), entry.getValue());
            } else {
                numericParameters.put(entry.getKey(), entry.getValue());
            }
        }

        return new ResponseEntity<String>(String.valueOf(param), HttpStatus.OK);
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

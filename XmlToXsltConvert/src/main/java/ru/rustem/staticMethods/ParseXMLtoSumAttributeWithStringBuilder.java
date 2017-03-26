package ru.rustem.staticMethods;

import java.io.*;

import static ru.rustem.Program.XML_FILE_TO_READ;

public class ParseXMLtoSumAttributeWithStringBuilder {
    public static void parseXMLtoSumAttributeWithStringBuilder() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(XML_FILE_TO_READ)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if(!line.startsWith("<?xml")){
                   sb.append(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb);
        String[] strings = String.valueOf(sb).split("");
        int count = 0;
        for (String a : strings) {
            if (a.matches("[0-9]")) {
                count = count + Integer.parseInt(a);
            }
        }
        System.out.println(count+ " count");
    }
}

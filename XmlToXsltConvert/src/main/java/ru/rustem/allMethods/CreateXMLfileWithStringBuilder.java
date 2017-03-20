package ru.rustem.allMethods;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static ru.rustem.Program.XML_FILE_TO_WRITE;

/**
 * This Class use to create xml file with used StringBuilder. At first create StringBuilder, and append title and append
 * all element Enumeration of values of list argument for forming XML file. After StringBuilder convert to String and
 * write need XML file.
 */
public class CreateXMLfileWithStringBuilder {
    public static void createXMLfileWithStringBuilder(List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><entries>");
        for (Integer i : list) {
            stringBuilder.append("<entry><field>" + i + "</field></entry>");
        }
        stringBuilder.append("</entries>");
        try(FileWriter fileWriter = new FileWriter(new File(XML_FILE_TO_WRITE))) {
            fileWriter.write(String.valueOf(stringBuilder));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("the file exists but is a directory rather than a regular file, does not exist but cannot be created,or cannot be opened for any other reason");
        }
    }
}

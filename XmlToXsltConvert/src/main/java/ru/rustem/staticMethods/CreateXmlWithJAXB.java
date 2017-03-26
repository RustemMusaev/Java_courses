package ru.rustem.staticMethods;


import ru.rustem.entryToXml.Entry;
import ru.rustem.entryToXml.Entries;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static ru.rustem.Program.XML_FILE_TO_WRITE;

public class CreateXmlWithJAXB {
    public  static void createXmlWithJAXB(List<Integer> list){
        Entries entries = new Entries();
        List<Entry> entryList = new LinkedList<>();
        for (Integer i : list) {
            Entry entry = new Entry();
            entry.setField(i);
            entryList.add(entry);
        }
        entries.setEntryList(entryList);
        try {
            File file = new File(XML_FILE_TO_WRITE);
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(entries, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }



}

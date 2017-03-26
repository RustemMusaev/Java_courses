package ru.rustem.entryToXml;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class Entry {

    private Integer field;


    public Entry() {

    }

    public Integer getField() {
        return field;
    }

    public void setField(Integer field) {
        this.field = field;
    }
}

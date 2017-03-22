package ru.rustem.model;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlData
{
    @XmlAttribute(name="name")
    private String name;

    @XmlValue
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
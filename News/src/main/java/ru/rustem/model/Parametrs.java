package ru.rustem.model;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Parametrs {

    @XmlAttribute(name = "method")
    private String requestMethod;

    @XmlElement(name = "numeric_parameters")
    private ResData numericHashMap;

    @XmlElement(name = "string_parameters")
    private ResData stringHashMap;

    public Parametrs() {
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public ResData getNumericHashMap() {
        return numericHashMap;
    }

    public void setNumericHashMap(ResData numericHashMap) {
        this.numericHashMap = numericHashMap;
    }

    public ResData getStringHashMap() {
        return stringHashMap;
    }

    public void setStringHashMap(ResData stringHashMap) {
        this.stringHashMap = stringHashMap;
    }

}


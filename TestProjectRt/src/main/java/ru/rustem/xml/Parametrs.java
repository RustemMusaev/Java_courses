package ru.rustem.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Parametrs {

    @XmlAttribute(name = "method")
    private String requestMethod;

    @XmlElement(name = "numeric_parameters")
    private Parameter numericHashMap;

    @XmlElement(name = "string_parameters")
    private Parameter stringHashMap;

    public Parametrs() {
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Parameter getNumericHashMap() {
        return numericHashMap;
    }

    public void setNumericHashMap(Parameter numericHashMap) {
        this.numericHashMap = numericHashMap;
    }

    public Parameter getStringHashMap() {
        return stringHashMap;
    }

    public void setStringHashMap(Parameter stringHashMap) {
        this.stringHashMap = stringHashMap;
    }

}


package ru.rustem.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;

@XmlRootElement(name = "request_detail")
@XmlAccessorType(XmlAccessType.FIELD)
public class Model {

    @XmlElement(name = "client_info")
    private HashMap<String,String> clientInfo;
    @XmlElement(name = "parametr")
    private List<HashMap> parametrMethod;
    @XmlElement(name = "string_parameters")
    private HashMap<String,String> stringParameters;
    @XmlElement(name = "numeric_parameters")
    private HashMap<String,String> numericParameters;


    public Model() {
    }

    public HashMap<String, String> getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(HashMap<String, String> clientInfo) {
        this.clientInfo = clientInfo;
    }

    public List<HashMap> getParametrMethod() {
        return parametrMethod;
    }

    public void setParametrMethod(List<HashMap> parametrMethod) {
        this.parametrMethod = parametrMethod;
    }

    public HashMap<String, String> getStringParameters() {
        return stringParameters;
    }

    public void setStringParameters(HashMap<String, String> stringParameters) {
        this.stringParameters = stringParameters;
    }

    public HashMap<String, String> getNumericParameters() {
        return numericParameters;
    }

    public void setNumericParameters(HashMap<String, String> numericParameters) {
        this.numericParameters = numericParameters;
    }
}

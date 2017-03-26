package ru.rustem.xml;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parameter")
public class Parameter {
    private List<ParameterElement> parameter;

    public List<ParameterElement> getParameter() {
        return parameter;
    }

    public void setParameter(List<ParameterElement> parameter) {
        this.parameter = parameter;
    }
}
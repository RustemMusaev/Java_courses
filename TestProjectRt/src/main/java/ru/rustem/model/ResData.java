package ru.rustem.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parameter")
public class ResData
{
    private List<XmlData> data;

    public List<XmlData> getData() {
        return data;
    }

    public void setData(List<XmlData> data) {
        this.data = data;
    }
}
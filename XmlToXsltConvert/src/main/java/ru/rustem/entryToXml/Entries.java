package ru.rustem.entryToXml;


import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class Entries {

    @XmlElement(name = "entry")
    private List<Entry> entryList;

    public Entries() {
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }
}

package ru.rustem.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "request_detail")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestDetail {

    @XmlElement(name = "client_info")
    private ClientInfo clientInfo;
    @XmlElement(name = "parameters")
    private Parametrs parametrs;

    public RequestDetail() {
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public Parametrs getParametrs() {
        return parametrs;
    }

    public void setParametrs(Parametrs parametrs) {
        this.parametrs = parametrs;
    }
}

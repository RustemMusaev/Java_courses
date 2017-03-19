package ru.rustem.allMethods;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import static ru.rustem.Program.XML_FILE_TO_READ;
import static ru.rustem.Program.XML_FILE_TO_WRITE;
import static ru.rustem.Program.XSLT_TEMPLATE;

public class TransformXmlWithXstl {
    public static void transformXmlWithXstl() throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        StreamSource xslStream = new StreamSource(XSLT_TEMPLATE);
        Transformer transformer = factory.newTransformer(xslStream);
        StreamSource in = new StreamSource(XML_FILE_TO_WRITE);
        StreamResult out = new StreamResult(XML_FILE_TO_READ);
        transformer.transform(in, out);
    }
}

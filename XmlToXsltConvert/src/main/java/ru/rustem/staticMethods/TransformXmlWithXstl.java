package ru.rustem.staticMethods;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import static ru.rustem.Program.*;

/**
 * This Class with transform XML file using XSLT template. Static method create TransformerFactory  object for transforming
 * xml file. After create stream and set rule for transform using XSLT file. We create input file to input stream and create
 * output file to result stream, using creting rule.
 */
public class TransformXmlWithXstl {
    public static void transformXmlWithXstl() {
        TransformerFactory factory = TransformerFactory.newInstance();
        StreamSource xslStream = new StreamSource(XSLT_TEMPLATE);
        Transformer transformer = null;
        StreamSource in = new StreamSource(XML_FILE_TO_WRITE);
        StreamResult out = new StreamResult(XML_FILE_TO_READ);
        try {
            transformer = factory.newTransformer(xslStream);
            transformer.transform(in, out);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}

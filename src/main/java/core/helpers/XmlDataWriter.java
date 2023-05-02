package core.helpers;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class XmlDataWriter {

    private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

    public static void writeNode(String parentName, String elementName, String elementValue) {

        try {
            String filepath = "src/test/resources/WriteData.xml";
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);
            Node testDAta = doc.getFirstChild();
            Node users = doc.getElementsByTagName(parentName).item(0);
            NamedNodeMap attribute = users.getAttributes();
            NodeList nodes = users.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node element = nodes.item(i);
                if (elementName.equals(element.getNodeName())) {
                    element.setTextContent("1500");
                    users.removeChild(element);
                }
            }
            Element username = doc.createElement(elementName);
            username.appendChild(doc.createTextNode(elementValue));
            users.appendChild(username);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);
            LOGGER.info("Done writing XML");
        } catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }
}

package task0;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class SubTask3 {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        System.out.println(readWithDOM());
    }
    public static String readWithDOM() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("DTPData.xml"));
        Element element = document.getDocumentElement();
        NodeList nodeList = element.getChildNodes();
        String result = element.getTextContent();
        return result;
    }
}

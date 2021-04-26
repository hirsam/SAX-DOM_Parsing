package task0;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SubTask1 {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        AdvancedHandler handler = new AdvancedHandler();
        parser.parse("src/Krasnodar.xml", handler);
    }
    private static class AdvancedHandler extends DefaultHandler {
        private String currentElem;
        int c = 1;
        @Override
        public void startDocument() throws SAXException {
            System.out.println("SAX parsing without validation");
            System.out.println("Start document processing");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("SAX parsing has ended");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElem = qName;
            System.out.println("\tStart element (" + currentElem + ") processing");
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            System.out.println("Stop element (" + currentElem + ") processing");
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);
            if(information != null && !information.isBlank()) {
                System.out.println(information.length() < 10? information:"");
            }


    }
        }
}




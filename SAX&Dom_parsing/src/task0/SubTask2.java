package task0;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SubTask2 {
    static ArrayList<DTPData> list = new ArrayList();
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        getData();
        writeDataInXML();

    }
    public static void writeDataInXML() throws XMLStreamException, FileNotFoundException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream("DTPData.xml"));
        writer.writeStartDocument();
        writer.writeCharacters("\n");
        writer.writeStartElement("Root");
        writer.writeCharacters("\n");
        for(DTPData o:list) {
            writer.writeStartElement("Dtp");
            writer.writeCharacters("\n");
            writer.writeStartElement("regName");
            writer.writeCharacters(o.getRegName());
            writer.writeEndElement();
            writer.writeCharacters("\n");
            writer.writeStartElement("district");
            writer.writeCharacters(o.getDistrict());
            writer.writeEndElement();
            writer.writeCharacters("\n");
            writer.writeStartElement("COORD_L");
            writer.writeCharacters(o.getCOORD_L());
            writer.writeEndElement();
            writer.writeCharacters("\n");
            writer.writeStartElement("COORD_W");
            writer.writeCharacters(o.getCOORD_W());
            writer.writeEndElement();
            writer.writeCharacters("\n");
            writer.writeEndElement();
        }
        writer.writeEndElement();
        writer.writeEndDocument();
    }
    public static void getData() throws IOException, SAXException, ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        MyHandler handler = new MyHandler();
        for (String path : getPaths()) {
            parser.parse(path, handler);
        }


    }
    public static ArrayList<String> getPaths() {
        ArrayList<String> paths = new ArrayList<>();
        File dir = new File("./src");
        for (File f : dir.listFiles()) {
            if (f.isFile() && f.getName().matches(".*\\.xml")) {
                    paths.add(f.getPath());
            }
        }
        return paths;
    }
    public static class MyHandler extends DefaultHandler {
        private String currentElement;
        private String currentRegName;
        private String currentDistrict;
        private String currentCOORD_L;
        private String currentCOORD_W;
        @Override
        public void startDocument() throws SAXException {
        }

        @Override
        public void endDocument() throws SAXException {
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentElement = qName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if((currentRegName != null && !currentRegName.isEmpty()) && (currentDistrict != null && !currentDistrict.isEmpty())
                    && (currentCOORD_L != null && !currentCOORD_L.isEmpty()) && (currentCOORD_W != null && !currentCOORD_W.isEmpty())) {
                list.add(new DTPData(currentRegName, currentDistrict, currentCOORD_L, currentCOORD_W));
                currentDistrict = null;
                currentCOORD_L = null;
                currentCOORD_W = null;

            }
            currentElement = "";
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);
            information = information.replace("\n", "").trim();
            if(!information.isEmpty()) {
                if (currentElement.equals("regName")) {
                    currentRegName = information;
                }
                if (currentElement.equals("district")) {
                    currentDistrict = information;
                }
                if (currentElement.equals("COORD_L")) {
                    currentCOORD_L = information;
                }
                if (currentElement.equals("COORD_W")) {
                    currentCOORD_W = information;
                }
            }
        }
    }
}

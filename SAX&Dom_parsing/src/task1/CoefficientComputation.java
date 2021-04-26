package task1;

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

public class CoefficientComputation {
    private static ArrayList<experimentData> experimentalData = new ArrayList<>();
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XMLStreamException {
      getExperimentData();
      writeInXML();
    }
    public static void writeInXML() throws FileNotFoundException, XMLStreamException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream("CalculationResult.xml"));
        writer.writeStartDocument();
        writer.writeCharacters("\n");
        writer.writeStartElement("Analyser");
        writer.writeCharacters("\n");
        for(experimentData d:experimentalData) {
            writer.writeStartElement("dataTable");
            writer.writeCharacters("\n");
            writer.writeStartElement("dataPoint");
            writer.writeAttribute("date", d.getObservingDate());
            writer.writeCharacters("\n");
            writer.writeStartElement("x");
            writer.writeCharacters(""+d.getX());
            writer.writeEndElement();
            writer.writeCharacters("\n");
            writer.writeStartElement("y");
            writer.writeCharacters(""+d.getY());
            writer.writeEndElement();
            writer.writeCharacters("\n");
            writer.writeEndElement();
            writer.writeCharacters("\n");
            writer.writeEndElement();
            writer.writeCharacters("\n");
        }
        writer.writeEmptyElement("line");
        writer.writeAttribute("b", ""+calculateB());
        writer.writeAttribute("k", ""+calculateK());
        writer.writeEndElement();
        writer.writeCharacters("\n");
        writer.writeEndDocument();
    }
    public static double calculateAverageX() {
        double result = 0;
        for(experimentData d:experimentalData) {
            result += d.getX();
        }
        return result/experimentalData.size();
    }
    public static double calculateAverageY() {
        double result = 0;
        for(experimentData d:experimentalData) {
            result += d.getY();
        }
        return result/experimentalData.size();
    }
    public static double calculateF1() {
        double result = 0;
        for(experimentData d:experimentalData) {
            result += d.getX() * d.getY();
        }
        return result;
    }
    public static double calculateF2() {
        double result = 0;
        for(experimentData d:experimentalData) {
            result += d.getX();
        }
        return result;
    }
    public static double calculateF3() {
        double result = 0;
        for(experimentData d:experimentalData) {
            result += Math.pow(d.getX(),2);
        }
        return result;
    }
    public static double calculateK() {
        return (calculateF1() - (calculateF2()*calculateAverageY()))/
                (calculateF3() - (calculateF2()*calculateAverageX()));
    }
    public static double calculateB() {
        return calculateAverageY() - calculateK()*calculateAverageX();
    }
    public static void getExperimentData() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        MyHandler handler = new MyHandler();
        parser.parse("src/ExperimentalData.xml", handler);
    }
    public static class MyHandler extends DefaultHandler {
        private String currentElem;
        private double currentX = 0.0;
        private double currentY = 0.0;
        private String currentDate;

        @Override
        public void startDocument() throws SAXException {
        }

        @Override
        public void endDocument() throws SAXException {
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentElem = qName;
            if(currentElem.equals("dataPoint")) currentDate = attributes.getValue("date");
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
        if((currentDate != null && !currentDate.isEmpty()) && (currentX != 0.0 && currentY != 0.0)) {
            experimentalData.add(new experimentData(currentX, currentY, currentDate));
            currentX = 0.0;
            currentY = 0.0;
            currentDate = null;
        }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);
            if(!information.isEmpty() && !information.isBlank()) {
                if (currentElem.equals("x")) {
                    this.currentX = Double.parseDouble(information);
                }
                if (currentElem.equals("y")) {
                    this.currentY = Double.parseDouble(information);
                }
            }
        }
    }
}

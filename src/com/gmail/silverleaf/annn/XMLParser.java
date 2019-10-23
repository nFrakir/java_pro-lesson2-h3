package com.gmail.silverleaf.annn;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    private List<Train> trains = new ArrayList<>();

    public XMLParser() {
        super();
    }

    public List<Train> getTrains() {
        return trains;
    }

    @Override
    public String toString() {
        return "XMLParser{" +
                "trains=" + trains +
                '}';
    }

    public void parse(String filePath) throws ParserConfigurationException, SAXException, IOException {
        DefaultHandler handler = new DefaultHandler() {
            Train train = new Train();
            String element;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                element = qName;
                if (qName.equals("train")) {
                    train.setId(attributes.getValue("id"));
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                element = "";
                if (qName.equals("train")) {
                    trains.add(train);
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                String info = new String(ch, start, length);

                if (element.equals("from")) {
                    train.setFrom(info);
                }
                if (element.equals("to")) {
                    train.setTo(info);
                }
                if (element.equals("date")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    try {
                        train.setDate(sdf.parse(info));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (element.equals("departure")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
                    try {
                        train.setDeparture(sdf.parse(info));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(filePath, handler);
    }
}

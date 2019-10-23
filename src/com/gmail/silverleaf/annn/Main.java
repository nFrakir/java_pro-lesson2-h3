package com.gmail.silverleaf.annn;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filePath = "trains.xml";

        List<Train> trains = new ArrayList<>();
        TrainsGenerator generator = new TrainsGenerator();
        generator.generateList(5);
        trains = generator.getTrains();

        System.out.println("Generated list: ");
        System.out.println(trains);

        XMLSaver saver = new XMLSaver(trains);
        try {
            saver.saveTrains(filePath);
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("List was saved: ");

        Train train = generator.generateTrain();
        trains.add(train);
        generator.setTrains(trains);

        System.out.println();
        System.out.println("New element added: ");
        System.out.println(trains);

	    XMLParser parser = new XMLParser();
	    try {
	        parser.parse(filePath);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Loaded list: ");
        System.out.println(trains);

        System.out.println();
        System.out.println("Find trains: ");


        printTrains("12:00","17:00", (ArrayList)trains);
    }

    private static void printTrains(String from, String to, ArrayList<Train> trains) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:MM");
        for(Train element: trains) {
            LocalTime departure = LocalTime.parse(sdf.format(element.getDeparture()));
            LocalTime start = LocalTime.parse(from);
            LocalTime end = LocalTime.parse(to);
            if (!(departure.isBefore(start)) && !(departure.isAfter(end))) {
                System.out.println(element);
            }
        }
    }
}

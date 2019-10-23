package com.gmail.silverleaf.annn;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class XMLSaver {
    List<Train> trains = new ArrayList<>();

    public XMLSaver(List<Train> trains) {
        super();
        this.trains = trains;
    }

    public XMLSaver() {
        super();
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public void saveTrains(String filePath) throws IOException, XMLStreamException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = null;
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:MM");
        try (FileWriter fw = new FileWriter(filePath)) {
            writer = factory.createXMLStreamWriter(fw);
            writer.writeStartDocument();
            writer.writeStartElement("trains");

            for (Train element: trains) {
                writer.writeStartElement("train");
                writer.writeAttribute("id", element.getId());

                writeElement(writer,"from", element.getFrom());
                writeElement(writer,"to", element.getTo());
                writeElement(writer,"date", sdfDate.format(element.getDate()));
                writeElement(writer,"departure", sdfTime.format(element.getDeparture()));

                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();
        } catch (IOException | XMLStreamException e) {
            throw e;
        }
    }

    private void writeElement(XMLStreamWriter writer, String name, String value) throws XMLStreamException {
        writer.writeStartElement(name);
        writer.writeCharacters(value);
        writer.writeEndElement();
    }
}

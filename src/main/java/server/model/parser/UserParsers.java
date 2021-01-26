package server.model.parser;

import server.model.User;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class UserParsers {
    static final String USER = "user";
    static final String ID = "id";
    static final String NAME = "name";
    static final String SURNAME = "surname";
    static final String MAIL = "mail";
    static final String CONTROL_QUESTION = "controlQuestion";
    static final String ANSWER_CONTROL_QUESTION = "answerControlQuestion";

    @SuppressWarnings({"unchecked", "null"})
    public List<User> readDatabase(String databaseFile) {
        List<User> users = new ArrayList<User>();
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(databaseFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            User user = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have an item element, we create a new item
                    String elementName = startElement.getName().getLocalPart();
                    switch (elementName) {
                        case USER:
                            user = new User();
                            break;
                        case ID:
                            event = eventReader.nextEvent();
                            user.setId(UUID.fromString(event.asCharacters().getData()));
                            break;
                        case NAME:
                            event = eventReader.nextEvent();
                            user.setName(event.asCharacters().getData());
                            break;
                        case SURNAME:
                            event = eventReader.nextEvent();
                            user.setSurname(event.asCharacters().getData());
                            break;
                        case MAIL:
                            event = eventReader.nextEvent();
                            user.setMail(event.asCharacters().getData());
                            break;
                        case CONTROL_QUESTION:
                            event = eventReader.nextEvent();
                            user.setControlQuestion(event.asCharacters().getData());
                            break;
                        case ANSWER_CONTROL_QUESTION:
                            event = eventReader.nextEvent();
                            user.setAnswerControlQuestion(event.asCharacters().getData());
                            break;
                    }
                }
//                     If we reach the end of an item element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(USER)) {
                        users.add(user);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return users;
    }

    //    write database
    public void saveDatabase(List<User> list, String nameDatabase) throws Exception {
        // create an XMLOutputFactory
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        // create XMLEventWriter
        XMLEventWriter eventWriter = outputFactory
                .createXMLEventWriter(new FileOutputStream(nameDatabase));
        // create an EventFactory
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        // create and write Start Tag
        StartDocument startDocument = eventFactory.createStartDocument();
        eventWriter.add(startDocument);
        eventWriter.add(end);

        // create config open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", "database");
        eventWriter.add(configStartElement);
        eventWriter.add(end);

        for (User user : list) {
            createStartNode(eventWriter, eventFactory, USER);
            eventWriter.add(end);
            createNode(eventWriter, ID, user.getId().toString(), eventFactory);
            createNode(eventWriter, NAME, user.getName(), eventFactory);
            createNode(eventWriter, SURNAME, user.getSurname(), eventFactory);
            createNode(eventWriter, MAIL, user.getMail(), eventFactory);
            createNode(eventWriter, CONTROL_QUESTION, user.getControlQuestion(), eventFactory);
            createNode(eventWriter, ANSWER_CONTROL_QUESTION, user.getAnswerControlQuestion(), eventFactory);
            createEndNode(eventWriter, eventFactory, USER);
        }

        eventWriter.add(eventFactory.createEndElement("", "", "database"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();
    }

    private void createNode(XMLEventWriter eventWriter, String name, String value, XMLEventFactory eventFactory) throws XMLStreamException {

//        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        createStartNode(eventWriter, eventFactory, name);
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        createEndNode(eventWriter, eventFactory, name);
    }

    private void createStartNode(XMLEventWriter eventWriter, XMLEventFactory xmlEventFactory, String name) throws XMLStreamException {
        XMLEvent tab = xmlEventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = xmlEventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        if (!name.equals(USER))
            eventWriter.add(tab);
        eventWriter.add(sElement);
    }

    private void createEndNode(XMLEventWriter eventWriter, XMLEventFactory xmlEventFactory, String name) throws XMLStreamException {
        // create End node
        XMLEvent tab = xmlEventFactory.createDTD("\t");
        if (name.equals(USER))
            eventWriter.add(tab);
        XMLEvent end = xmlEventFactory.createDTD("\n");
        EndElement eElement = xmlEventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }
}

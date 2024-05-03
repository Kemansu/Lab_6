package org.sc.manager;

import org.sc.data.*;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Данный класс выполняет чтение данных, которые хранятся в формате XML
 *
 * @author Kemansu
 * @since 1.0
 */
public class ReaderXML {

    public static void read() throws ParserConfigurationException, IOException, SAXException {
        String filePath = System.getenv("MY_TEXT_FILE");
        StringBuilder xmlContent = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                xmlContent.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            return;
        }

        InputSource in = new InputSource(new StringReader(xmlContent.toString()));
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(in);
            NodeList flatElements = document.getDocumentElement().getElementsByTagName("flat");

            if (flatElements.getLength() == 0) {
                System.out.println("File is empty");
            } else {
                for (int i = 0; i < flatElements.getLength(); i++) {
                    Node flatNode = flatElements.item(i);
                    NamedNodeMap attributes = flatNode.getAttributes();

                    // Создаем новый объект Flat
                    Flat flat = new Flat(Long.parseLong(attributes.getNamedItem("id").getNodeValue()));
                    flat.setName(attributes.getNamedItem("name").getNodeValue());
                    flat.setCoordinates(new Coordinates(
                            Double.parseDouble(attributes.getNamedItem("coordinateX").getNodeValue()),
                            Long.parseLong(attributes.getNamedItem("coordinateY").getNodeValue())
                    ));
                    flat.setCreationDate(LocalDate.parse(attributes.getNamedItem("creationDate").getNodeValue()));
                    flat.setArea(Long.parseLong(attributes.getNamedItem("area").getNodeValue()));
                    flat.setNumberOfRooms(Long.parseLong(attributes.getNamedItem("numberOfRooms").getNodeValue()));
                    flat.setKitchenArea(Double.parseDouble(attributes.getNamedItem("kitchenArea").getNodeValue()));
                    flat.setView(View.valueOf(attributes.getNamedItem("view").getNodeValue()));
                    if (attributes.getNamedItem("transport") != null) {
                        flat.setTransport(Transport.valueOf(attributes.getNamedItem("transport").getNodeValue()));
                    }
                    // Дополнительно обрабатываем данные о House, если они есть
                    if (attributes.getNamedItem("houseName") != null) {
                        House house = new House();
                        house.setName(attributes.getNamedItem("houseName").getNodeValue());
                        flat.setHouse(house);
                    }

                    // Добавляем объект Flat в коллекцию
                    CollectionManager.add(flat);
                }
            }
        } catch (Exception e) {
            System.out.println("File is empty or damaged");
            e.printStackTrace();
        }
    }
}

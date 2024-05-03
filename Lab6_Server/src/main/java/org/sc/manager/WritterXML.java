package org.sc.manager;

import org.sc.data.Flat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayDeque;

/**
 * Данный класс выполняет запись данных, которые хранятся в формате XML
 *
 * @author Kemansu
 * @since 1.0
 */

public class WritterXML {

    public static void write() {
        ArrayDeque<Flat> flatsCollection = CollectionManager.getArrayDeque();
        String filePath = System.getenv("MY_TEXT_FILE");
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("flats");
            doc.appendChild(rootElement);

            for (Flat flat : flatsCollection) {
                Element flatElement = doc.createElement("flat");

                flatElement.setAttribute("id", String.valueOf(flat.getId()));
                flatElement.setAttribute("name", flat.getName());
                flatElement.setAttribute("coordinateX", String.valueOf(flat.getCoordinates().getX()));
                flatElement.setAttribute("coordinateY", String.valueOf(flat.getCoordinates().getY()));
                flatElement.setAttribute("creationDate", String.valueOf(flat.getCreationDate()));
                flatElement.setAttribute("area", String.valueOf(flat.getArea()));
                flatElement.setAttribute("numberOfRooms", String.valueOf(flat.getNumberOfRooms()));
                flatElement.setAttribute("kitchenArea", String.valueOf(flat.getKitchenArea()));
                flatElement.setAttribute("view", flat.getView().name());
                if (flat.getTransport() != null) {
                    flatElement.setAttribute("transport", flat.getTransport().name());
                }
                if (flat.getHouse() != null) {
                    flatElement.setAttribute("houseName", flat.getHouse().getName());
                    flatElement.setAttribute("houseYear", String.valueOf(flat.getHouse().getYear()));
                    flatElement.setAttribute("houseNumberOfFlatsOnFloor", String.valueOf(flat.getHouse().getNumberOfFlatsOnFloor()));
                }

                rootElement.appendChild(flatElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Используем PrintWriter для записи в файл
            PrintWriter writer = new PrintWriter(new File(filePath));
            StreamResult result = new StreamResult(writer);
            transformer.transform(new DOMSource(doc), result);
            writer.close();

            System.out.println("Файл успешно сохранен!");

        } catch (Exception e) {
            System.out.println("Colletcion is empty :(");;
        }
    }
}

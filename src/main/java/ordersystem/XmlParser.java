package ordersystem;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.xml.sax.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    private static File file = new File("test_orders/xmlOrder.xml");

    public static Order xmlParsing() throws ParserConfigurationException, IOException, SAXException {
        // Create ability to parse XML document.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        document.getDocumentElement().normalize();

        // Traversing the XML file for Order data.
        NodeList orderList = document.getElementsByTagName("Order");
        Element orderElement = (Element) orderList.item(0);

        // Retrieving Order ID number and Order type.
        // String orderID = orderElement.getAttribute("type");
        String orderType = orderElement.getElementsByTagName("OrderType").item(0).getTextContent();

        // Traversing the XML file for Item data.
        List<Item> orderItems = new ArrayList<>();
        NodeList itemList = document.getElementsByTagName("Item");


        for(int i = 0; i < itemList.getLength(); i++) {
            Node node = itemList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element item = (Element) node;
                String name = item.getAttribute("type");
                double price = Double.parseDouble(item.getElementsByTagName("Price").item(0).getTextContent());
                int quantity = Integer.parseInt(item.getElementsByTagName("Quantity").item(0).getTextContent());

                orderItems.add(new Item(name, price, quantity));
            }
        }

        Order testOrder = new Order(orderType, orderItems);
        testOrder.getOrderDetails();

        return new Order(orderType, orderItems);

    }

    public static void main(String[] args) {
        try {
            Order order = xmlParsing();
        } catch (Exception e) {
            System.out.println("Error occurred: Parsing failed.");
        }
    }

}

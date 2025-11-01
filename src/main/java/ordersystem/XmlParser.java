package ordersystem;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import org.xml.sax.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates Order items out of XML files.
 * @author Jordan Curtis
 */
public class XmlParser {
    private File files = new File("test_orders");
    private ArrayList<Order> ordersToReturn = new ArrayList<>();

    // Lambda expression to get an array of files with specifically names ending in '.xml' from the data directory.
    private File[] xmlFiles = files.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));

    public List<Order> xmlParsing() {
        for(File xmlFile: xmlFiles) {
            try {
                // Create ability to parse XML document.
                DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(xmlFile);
                document.getDocumentElement().normalize();

                // Getting specifically the Order data.
                NodeList orderList = document.getElementsByTagName("Order");
                Element orderElement = (Element) orderList.item(0);

                // Retrieving Order ID number and Order type.
                // String orderID = orderElement.getAttribute("type");
                String orderType = orderElement.getElementsByTagName("OrderType").item(0).getTextContent();

                // Setting up traversal for the Item data.
                List<Item> orderItems = new ArrayList<>();
                NodeList itemList = document.getElementsByTagName("Item");

                // Traversing the node list for each Item element and adding them to the Item list.
                for (int i = 0; i < itemList.getLength(); i++) {
                    Node node = itemList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element item = (Element) node;
                        String name = item.getAttribute("type");
                        double price = Double.parseDouble(item.getElementsByTagName("Price").item(0).getTextContent());
                        int quantity = Integer.parseInt(item.getElementsByTagName("Quantity").item(0).getTextContent());

                        orderItems.add(new Item(name, price, quantity));
                    }
                }

                ordersToReturn.add(new Order(orderType, orderItems, Order.orderStatus.INCOMING));
            } catch (Exception e) {
                System.err.println("Cannot import order due to an error in the data/format of the XML file: " + xmlFile.getName());
            }
        }

        return ordersToReturn;

    }
}

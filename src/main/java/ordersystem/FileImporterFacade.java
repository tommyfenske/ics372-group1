package ordersystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Facade that centralizes the import calls by file type to one class.
 * @author Jordan Curtis
 */
public class FileImporterFacade {
    private JsonParser jsonParser;
    private XmlParser xmlParser;
    private List<Order> incomingOrders;
    private final File filepath = new File("data");

    public FileImporterFacade() {
        this.jsonParser = new JsonParser();
        this.xmlParser = new XmlParser();
        this.incomingOrders = new ArrayList<Order>();
    }

    // Import all orders in the data directory.
    public List<Order> fileImport() {
        incomingOrders.addAll(jsonParser.jsonParsing(filepath));
        incomingOrders.addAll(xmlParser.xmlParsing(filepath));

        return incomingOrders;
    }

    // Overloaded file import for manual order imports by specified file type.
    public List<Order> fileImport(String fileType) {
        List<Order> importOrders = new ArrayList<Order>();

        if(fileType.equalsIgnoreCase("json")) {
            importOrders.addAll(jsonParser.jsonParsing(filepath));
        } else if (fileType.equalsIgnoreCase("xml")) {
            importOrders.addAll(xmlParser.xmlParsing(filepath));
        } else {
            System.err.println("Unsupported file type: " + fileType);
        }

        return importOrders;
    }
}

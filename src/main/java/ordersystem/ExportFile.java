package ordersystem;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * export all orders into one json file without gson
 * @author Kheder
 */
public class ExportFile {
    public ExportFile() {}

    // this method takes all 3 lists of orders and makes them into one json file
    public void exportOrdersToJSON(List<Order> incomingOrders, List<Order> startedOrders, List<Order> completedOrders) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        boolean first = true;

        // incoming orders
        for (Order o : incomingOrders) {
            if (!first) sb.append(",\n");
            sb.append(orderToJson(o, "incoming"));
            first = false;
        }

        // started orders
        for (Order o : startedOrders) {
            if (!first) sb.append(",\n");
            sb.append(orderToJson(o, "started"));
            first = false;
        }

        // completed orders
        for (Order o : completedOrders) {
            if (!first) sb.append(",\n");
            sb.append(orderToJson(o, "completed"));
            first = false;
        }

        sb.append("\n]");

        // show save dialog
        JFrame frame = new JFrame();
        frame.setAlwaysOnTop(true); // makes chooser show on top
        frame.setVisible(false);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose where to save orders.json");
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON files", "json"));
        fileChooser.setSelectedFile(new java.io.File("orders.json")); // default name

        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filename = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filename.endsWith(".json")) {
                filename += ".json";
            }

            try (FileWriter writer = new FileWriter(filename)) {
                writer.write(sb.toString());
                System.out.println("orders exported to " + filename);
                System.out.println("export finished saved to: " + filename + "\n");
            } catch (IOException e) {
                System.out.println("error exporting orders " + e.getMessage());
            }
        } else {
            System.out.println("export canceled by user\n");
        }

        frame.dispose();
    }

    // helper: turn one order into json string (pretty printed)
    private String orderToJson(Order o, String status) {
        StringBuilder ob = new StringBuilder();
        ob.append("  {\n");
        ob.append("    \"orderID\": ").append(o.getOrderID()).append(",\n");
        ob.append("    \"type\": \"").append(o.getOrderType()).append("\",\n");
        ob.append("    \"status\": \"").append(status).append("\",\n");

        // items
        ob.append("    \"items\": [\n");
        List<Item> items = o.getItems();
        for (int i = 0; i < items.size(); i++) {
            Item it = items.get(i);
            ob.append("      { \"name\": \"").append(it.getName()).append("\", ");
            ob.append("\"quantity\": ").append(it.getQuantity()).append(", ");
            ob.append("\"price\": ").append(it.getPrice()).append(" }");
            if (i < items.size() - 1) ob.append(",");
            ob.append("\n");
        }
        ob.append("    ]\n");
        ob.append("  }");
        return ob.toString();
    }
}
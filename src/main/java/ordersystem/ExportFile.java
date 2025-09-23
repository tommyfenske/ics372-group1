package ordersystem;

import java.util.*;

/**
 * This is the exporter which will export all orders into a single JSON file.
 * @author Kheder Yusuf;
 */

public class ExportFile {
    // Holds a reference to the OrderManager so this class can access orders
    private OrderManager orderManager;

    public ExportFile( ) {}

    public void testConnection(){
        int count = orderManager.getCompletedOrders().size();
        System.out.println("Completed orders: " + count);
    }

    public void exportOrdersToJSON(List<Order> incomingOrders, List<Order> startedOrders, List<Order> completedOrders) {
        return;
    }
}


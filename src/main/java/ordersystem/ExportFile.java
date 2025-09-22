package ordersystem;

/**
 * This is the exporter which will export all orders into a single JSON file.
 * @author Kheder Yusuf;
 */

public class ExportFile {
    // Holds a reference to the OrderManager so this class can access orders
    private OrderManager orderManager;

    public ExportFile(OrderManager orderManager) {
        this.orderManager = orderManager;
    }

    public void testConnection(){
        int count = orderManager.getCompletedOrders().size();
        System.out.println("Completed orders: " + count);
    }
}


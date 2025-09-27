package ordersystem;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Order object handles all the item details of a customer's order details:
 * @author Jordan Curtis
 */

public class Order {
    static int latestID = 0;
    private final int orderID = ++latestID;
    private String orderType;
    private String status = "Incomplete";
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private List<Item> items = new ArrayList<Item>();
    private double total = 0;


    public Order(String orderType, List<Item> items) {
        this.openTime = LocalDateTime.now();
        this.orderType = orderType;
        this.items = items;

        for(Item item: items) {
            total += item.getPrice();
        }
    }

    public int getOrderID() {
        return this.orderID;
    }

    public String getOpenTime() {
        return this.openTime.toString();
    }

    public String getOrderType() {
        return this.orderType;
    }

    public String getStatus() {
        return this.status;
    }

    public void getOrderDetails() {
        double sum = 0;

        for(Item item: items) {
            System.out.println(item.toString());
        }

        System.out.println("\nTOTAL: " + sum);
    }

    // Throws InvalidOrderStatusChange when attempting to start an already started or closed order.
    public void startOrder() throws InvalidOrderStatusChange {
        if (this.status.equalsIgnoreCase("Started") || this.status.equalsIgnoreCase("Closed")) {
            throw new InvalidOrderStatusChange("Order has already been started.");
        }

        this.status = "Started";

    }

    // Throws InvalidOrderStatusChange when attempting to close an already closed or incomplete order.
    public void closeOrder() throws InvalidOrderStatusChange {
        if (this.status.equalsIgnoreCase("Closed") || this.status.equalsIgnoreCase("Incomplete")) {
            throw new InvalidOrderStatusChange("Order has already been closed.");
        }

        this.closeTime = LocalDateTime.now();
        this.status = "Closed";
    }

    /**
     * Overrides the toString method to print the Order's ID, type, and status
     * @author Tommy Fenske
     */
    @Override
    public String toString() {
        return String.format("Order ID: %d, Type: %s, Status: %s, Item Qty: %d", this.orderID, this.orderType, this.status, this.items.size());
    }

    public String displayOrder() {
        String handler = toString();

        for(Item item: items) {
            handler = handler + "\n" + item.toString();
        }

        return handler;
    }
}

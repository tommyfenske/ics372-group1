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
    private orderStatus status = orderStatus.INCOMING;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private List<Item> items = new ArrayList<Item>();
    private double total = 0;

    //Adding an ENUM property

    /**
     * This enum property will allow us to have functionality that will separate the orders based on their type
     */
    enum orderStatus{INCOMING,STARTED,COMPLETE,INCOMPLETE,CLOSED,CANCELED}


    /**
     * Constructor for Order class
     * @param orderType Type of order like pickup, delivery, etc
     * @param items List of items in the order
     * @param currentStatus Status of order, like started, completed, etc
     */
    public Order(String orderType, List<Item> items, orderStatus currentStatus) {
        this.openTime = LocalDateTime.now();
        this.orderType = orderType;
        this.items = items;
        this.status = currentStatus;



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

    public orderStatus getStatus() {
        return this.status;
    }

    // Returns the price, quantity, and name of each item in the terminal. It then adds the prices to display the total.
    public void getOrderDetails() {
        double sum = 0;

        for(Item item: items) {
            System.out.println(item.toString());
        }

        System.out.println("\nTOTAL: " + sum);
    }

    // Changes order status to "Started".
    // Throws InvalidOrderStatusChange when attempting to start an already started or closed order.
    public void startOrder() throws InvalidOrderStatusChange {
        if (this.status == orderStatus.STARTED || this.status == orderStatus.CLOSED) {
            throw new InvalidOrderStatusChange("Order has already been started.");
        }

        this.status = orderStatus.STARTED;

    }
    // Changes order status to "Cancelled".
    // Throws InvalidOrderStatusChange when attempting to cancel an already closed or cancelled order.
    public void cancelOrder() throws InvalidOrderStatusChange {
        if (this.status == orderStatus.CLOSED || this.status == orderStatus.CANCELED) {
            throw new InvalidOrderStatusChange("Order has already been closed or cancelled.");
        }

        this.closeTime = LocalDateTime.now();
        this.status = orderStatus.CANCELED;
    }


    public List<Item> getItems() {
        return this.items;
    }

    // Changes order status to "Closed".
    // Throws InvalidOrderStatusChange when attempting to close an already closed or incomplete order.
    public void closeOrder() throws InvalidOrderStatusChange {
        if (this.status == orderStatus.CLOSED || this.status == orderStatus.INCOMPLETE) {
            throw new InvalidOrderStatusChange("Order has already been closed.");
        }

        this.closeTime = LocalDateTime.now();
        this.status = orderStatus.CLOSED;
    }


    /**
     * Marks the order as completed (finished successfully).
     */
    public void completeOrder() throws InvalidOrderStatusChange {
        if (this.status != orderStatus.STARTED) {
            throw new InvalidOrderStatusChange("Order must be started before it can be completed.");
        }
        this.status = orderStatus.COMPLETE;
    }

    /**
     * Overrides the toString method to print the Order's ID, type, and status
     * @author Tommy Fenske
     */
    @Override
    public String toString() {
        return String.format("Order ID: %d, Type: %s, Status: %s, Item Qty: %d", this.orderID, this.orderType, this.status, this.items.size());
    }

    // Returns details of order (similar to getOrderDetails()) except it returns the String of the items to be used in different contexts.
    public String displayOrder() {
        String handler = toString();

        for(Item item: items) {
            handler = handler + "\n" + item.toString();
        }

        return handler;
    }
}

package ordersystem;

/**
 * Example class for an Order.
 * Variable types may be incorrect. This is a placeholder
 * until we determine a better implementation.
 * @author Tommy Fenske
 */

public class Order {
    private int id;
    private String type;
    // Food items data structure
    private int quantity;
    private int time;
    private int price;

    public Order(int id, String type, int quantity, int time, int price) {
        // Constructor
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

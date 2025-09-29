package ordersystem;

/**
 * Item object contains information regarding individual items ordered by the user.
 * @author Jordan Curtis
 */

public class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Overrides the format of toString() to be compatible with this Item class.
    @Override
    public String toString() {
        return String.format("Item: %s, Price: %.2f, Quantity %d", name, price, quantity);
    }
}

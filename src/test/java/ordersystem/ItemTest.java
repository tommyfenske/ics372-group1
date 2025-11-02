package ordersystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Item class
 */
public class ItemTest {

    @Test
    public void testCreateItem() {
        Item item = new Item("Pizza", 15.99, 2);

        assertEquals("Pizza", item.getName());
        assertEquals(15.99, item.getPrice(), 0.01);
        assertEquals(2, item.getQuantity());
    }

    @Test
    public void testSetters() {
        Item item = new Item("Burger", 10.00, 1);

        item.setName("Cheeseburger");
        item.setPrice(12.00);
        item.setQuantity(3);

        assertEquals("Cheeseburger", item.getName());
        assertEquals(12.00, item.getPrice(), 0.01);
        assertEquals(3, item.getQuantity());
    }

    @Test
    public void testToString() {
        Item item = new Item("Fries", 3.50, 2);
        String result = item.toString();

        assertTrue(result.contains("Fries"));
        assertTrue(result.contains("3.50"));
    }
}

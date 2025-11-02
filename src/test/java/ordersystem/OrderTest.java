package ordersystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for Order class
 */
public class OrderTest {

    private List<Item> items;

    @BeforeEach
    public void setUp() {
        Order.latestID = 0;
        items = new ArrayList<>();
        items.add(new Item("Burger", 10.00, 1));
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order("Delivery", items, Order.orderStatus.INCOMING);

        assertNotNull(order);
        assertEquals("Delivery", order.getOrderType());
        assertEquals(Order.orderStatus.INCOMING, order.getStatus());
    }

    @Test
    public void testStartOrder() throws InvalidOrderStatusChange {
        Order order = new Order("Pickup", items, Order.orderStatus.INCOMING);
        order.startOrder();

        assertEquals(Order.orderStatus.STARTED, order.getStatus());
    }

    @Test
    public void testCompleteOrder() throws InvalidOrderStatusChange {
        Order order = new Order("To Go", items, Order.orderStatus.STARTED);
        order.completeOrder();

        assertEquals(Order.orderStatus.COMPLETE, order.getStatus());
    }

    @Test
    public void testCancelOrder() throws InvalidOrderStatusChange {
        // Feature 1: Test cancel functionality
        Order order = new Order("Delivery", items, Order.orderStatus.INCOMING);
        order.cancelOrder();

        assertEquals(Order.orderStatus.CANCELED, order.getStatus());
    }

    @Test
    public void testCannotStartAlreadyStarted() {
        Order order = new Order("Pickup", items, Order.orderStatus.STARTED);

        assertThrows(InvalidOrderStatusChange.class, () -> order.startOrder());
    }
}

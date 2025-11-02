package ordersystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit tests for OrderManager - Feature 1: Cancel orders
 */
public class OrderManagerTest {

    private OrderManager manager;
    private List<Item> items;

    @BeforeEach
    public void setUp() {
        Order.latestID = 0;
        manager = new OrderManager(null);

        items = new ArrayList<>();
        items.add(new Item("Pizza", 12.99, 1));
    }

    @Test
    public void testStartOrder() {
        Order order = new Order("Delivery", items, Order.orderStatus.INCOMING);
        manager.getIncomingOrders().add(order);

        boolean result = manager.startOrder(order.getOrderID());

        assertTrue(result);
        assertEquals(1, manager.getStartedOrders().size());
    }

    @Test
    public void testCompleteOrder() {
        Order order = new Order("Pickup", items, Order.orderStatus.STARTED);
        manager.getStartedOrders().add(order);

        boolean result = manager.completeOrder(order.getOrderID());

        assertTrue(result);
        assertEquals(1, manager.getCompletedOrders().size());
    }

    @Test
    public void testCancelIncomingOrder() {
        // Feature 1: Cancel incoming order
        Order order = new Order("Delivery", items, Order.orderStatus.INCOMING);
        manager.getIncomingOrders().add(order);

        boolean result = manager.cancelOrder(order.getOrderID());

        assertTrue(result);
        assertEquals(Order.orderStatus.CANCELED, order.getStatus());
    }

    @Test
    public void testCancelStartedOrder() {
        // Feature 1: Cancel started order
        Order order = new Order("To Go", items, Order.orderStatus.STARTED);
        manager.getStartedOrders().add(order);

        boolean result = manager.cancelOrder(order.getOrderID());

        assertTrue(result);
        assertEquals(Order.orderStatus.CANCELED, order.getStatus());
    }

    @Test
    public void testCannotCancelCompleted() {
        // Feature 1: Cannot cancel completed order
        Order order = new Order("Pickup", items, Order.orderStatus.COMPLETE);
        manager.getCompletedOrders().add(order);

        boolean result = manager.cancelOrder(order.getOrderID());

        assertFalse(result);
    }
}

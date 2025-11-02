package ordersystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

/**
 * Unit tests for XmlParser - Feature 3: XML import
 */
public class XmlParserTest {

    private XmlParser parser;
    private File testDir;

    @BeforeEach
    public void setUp() {
        parser = new XmlParser();
        testDir = new File("src/test/resources/test_orders");
    }

    @Test
    public void testParseValidXml() {
        // Feature 3: Parse valid XML files
        List<Order> orders = parser.xmlParsing(testDir);

        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Test
    public void testParseDeliveryOrder() {
        // Feature 4: Test order types
        List<Order> orders = parser.xmlParsing(testDir);

        Order delivery = orders.stream()
            .filter(o -> "Delivery".equals(o.getOrderType()))
            .findFirst()
            .orElse(null);

        assertNotNull(delivery);
        assertEquals(3, delivery.getItems().size());
    }

    @Test
    public void testHandleMalformedXml() {
        // Feature 3: Should handle buggy XML gracefully
        List<Order> orders = parser.xmlParsing(testDir);

        // Should not crash, return valid orders
        assertNotNull(orders);
    }

    @Test
    public void testEmptyDirectory() {
        File emptyDir = new File("nonexistent");
        List<Order> orders = parser.xmlParsing(emptyDir);

        assertNotNull(orders);
    }
}

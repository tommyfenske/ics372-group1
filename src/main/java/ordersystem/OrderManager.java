package ordersystem;

import java.util.*;

/**
 * This class handles all of the orders, started, incompleted and completed
 */
public class OrderManager {
    // How the user interacts with the OrderManager
    private TerminalInterface terminalInterface;

    private List<Order> incomingOrders;
    private List<Order> startedOrders;
    private List<Order> completedOrders;

    public OrderManager() {
        incomingOrders = new ArrayList<Order>();
        startedOrders = new ArrayList<Order>();
        completedOrders = new ArrayList<Order>();

        terminalInterface = new TerminalInterface(this);
    }

    /**
     * Instantiates the FileHandler class which returns an ArrayList of Orders.
     * Then adds each of the returned Orders to the incomingOrders ArrayList.
     * @return void
     * @author Tommy Fenske
     */
    void fileFromJSON() {
        FileHandler fh = new FileHandler();
        List<Order> newOrders = fh.jsonParsing();

        System.out.printf("Adding %d orders.\n", newOrders.size());
        for (Order order : newOrders) {
            System.out.println(order.toString());
        }
        incomingOrders.addAll(newOrders);

    }

    /**
     * Returns the Incoming Orders Arraylist of unstarted orders
     * @return ArrayList<Order> incomingOrders
     * @author Tommy Fenske
     */
    public List<Order> getIncomingOrders() {
        return incomingOrders;
    }

    /**
     * Returns the Started Orders Arraylist of started but uncompleted orders
     * @return ArrayList<Order> startedOrders
     * @author Tommy Fenske
     */
    public List<Order> getStartedOrders() {
        return startedOrders;
    }

    /**
     * Returns the Started Orders Arraylist of completed orders
     * @return ArrayList<Order> completedOrders
     * @author Tommy Fenske
     */
    public List<Order> getCompletedOrders() {
        return completedOrders;
    }

    /**
     * Prints each Order object in incomingOrders ArrayList
     * @return void
     * @author Tommy Fenske
     */
    public void printIncomingOrders() {
        for (Order order : incomingOrders) {
            System.out.println(order.toString());
        }
    }

    /**
     * Prints each Order object in startedOrders ArrayList
     * @return void
     * @author Tommy Fenske
     */
    public void printStartedOrders() {
        for (Order order : startedOrders) {
            System.out.println(order.toString());
        }
    }

    /**
     * Prints each Order object in completedOrders ArrayList
     * @return void
     * @author Tommy Fenske
     */
    public void printCompletedOrders() {
        for (Order order : completedOrders) {
            System.out.println(order.toString());
        }
    }

    /**
     * TODO: Write the code to fit the below description of the function
     * Searches the incomingOrders List for the orderID,
     * if a matching ID is found, move the order from incomingOrders to startedOrders List and return true
     * if no matching ID is found, return false
     * @return true if ID found, otherwise false
     */
    boolean startOrder(int orderID) {
        return false;
    }

    /**
     * TODO: Write the code to fit the below description of the function
     * Searches the startedOrders List for the orderID,
     * if a matching ID is found, move the order from startedOrders to completedOrders List and return true
     * if no matching ID is found, return false
     */
    boolean completeOrder(int orderID) {
        return false;
    }

    /**
     * TODO: Write the code to fit the below description of the function
     * Searches the all Order Lists for the orderID,
     * if a matching ID is found, call an Order method to display order details
     * if no matching ID is found, return false
     */
    boolean displayOrder(int orderID) {
        return false;
    }

}

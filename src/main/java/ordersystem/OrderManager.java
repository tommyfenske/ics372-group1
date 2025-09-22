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
        terminalInterface = new TerminalInterface(this);
        incomingOrders = new ArrayList<Order>();
        startedOrders = new ArrayList<Order>();
        completedOrders = new ArrayList<Order>();
    }

    /**
     * Instantiates the FileHandler class which returns an ArrayList of Orders.
     * Then adds each of the returned Orders to the incomingOrders ArrayList.
     * @return void
     * @author Tommy Fenske
     */
    void fileFromJSON() {
        FileHandler fh = new FileHandler();
        fh.jsonParsing();
        // TODO: delete line above and replace with comments below once FileHandler returns ArrayList
        //ArrayList<Order> newOrders = fh.jsonParsing();
        /*
        for (Order order : newOrders) {
            incomingOrders.add(order);
        }
         */
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
}

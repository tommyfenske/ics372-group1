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
        terminalInterface = new TerminalInterface();
        incomingOrders = new ArrayList<Order>();
        startedOrders = new ArrayList<Order>();
        completedOrders = new ArrayList<Order>();
    }

    /**
     * Instantiates the FileHandler class which returns an ArrayList of Orders.
     * Then adds each of the returned Orders to the incomingOrders ArrayList.
     *
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
     *
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
     *
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
     *
     * @return void
     * @author Tommy Fenske
     */
    public void printCompletedOrders() {
        for (Order order : completedOrders) {
            System.out.println(order.toString());
        }
    }

    // Return the list of incoming orders
    public List<Order> getIncomingOrders() { return incomingOrders; }

    // Return the list of started orders
    public List<Order> getStartedOrders()  { return startedOrders; }
    // Return the list of completed orders
    public List<Order> getCompletedOrders(){ return completedOrders; }


    /**
     * Searches the incomingOrders List for the orderID,
     * if a matching ID is found, move the order from incomingOrders to startedOrders List and return true
     * if no matching ID is found, return false
     * @return true if ID found, otherwise false
     * @author Majid  Farah
     */
    boolean startOrder(int orderID) {
        Iterator<Order> iterator = incomingOrders.iterator();

        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getOrderID() == orderID) {
                try {
                    order.startOrder();
                } catch (InvalidOrderStatusChange e) {
                    return false;
                }

                iterator.remove();
                startedOrders.add(order);
                return true;
            }
        }

        return false;
    }
    /**
     * Searches the startedOrders List for the orderID,
     * if a matching ID is found, move the order from startedOrders to completedOrders List and return true
     * if no matching ID is found, return false
     * @author Majid Farah
     */
    boolean completeOrder(int orderID) {
        Iterator<Order> iterator = startedOrders.iterator();

        while (iterator.hasNext()) {
            Order order = iterator.next();
            if (order.getOrderID() == orderID) {
                try {
                    order.closeOrder();
                } catch (InvalidOrderStatusChange e) {
                    return false;
                }

                iterator.remove();
                completedOrders.add(order);
                return true;
            }
        }

        return false;
    }

    /**
     * Searches the all Order Lists for the orderID,
     * Searches all Order Lists for the orderID,
     * if a matching ID is found, return the order
     * if no matching ID is found, return null
     * @author Majid Farah
     */
    Order getOrder(int orderID) {
        for (Order order : incomingOrders) {
            if (order.getOrderID() == orderID) {
                return order;
            }
        }

        for (Order order : startedOrders) {
            if (order.getOrderID() == orderID) {
                return order;
            }
        }

        for (Order order : completedOrders) {
            if (order.getOrderID() == orderID) {
                return order;
            }
        }

        return null;
    }
}
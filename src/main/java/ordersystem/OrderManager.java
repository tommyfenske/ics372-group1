package ordersystem;

import java.util.*;

import javafx.application.Platform;
import java.io.File;
import java.nio.file.*;

/**
 * This class handles all the orders: incoming, started, and completed
 */
public class OrderManager {

    private static GUIController guiController;
    private static boolean pollDirectory = true;

    private List<Order> incomingOrders;
    private List<Order> startedOrders;
    private List<Order> completedOrders;

    public OrderManager(GUIController controller) {
        guiController = controller;

        incomingOrders = new ArrayList<Order>();
        startedOrders = new ArrayList<Order>();
        completedOrders = new ArrayList<Order>();

        //setupWatcher(this, guiController);
    }

    /**
     * Instantiates the FileHandler class which returns an ArrayList of Orders.
     * Then adds each of the returned Orders to the incomingOrders ArrayList.
     * @author Tommy Fenske
     */
    void fileFromJSON() {
        FileHandler fh = new FileHandler();
        List<Order> newOrders = fh.jsonParsing();
        this.incomingOrders.addAll(newOrders);

        // Update GUIController after new orders have been added

    }

    /**
     *
     * This setter is needed to link TerminalInterface, it was breaking our
     * code when we had it set as an object and then referencing it back in
     * the Terminal Interface class
     * @author Ruben Vallejo
     */
    public void setTerminalInterface(TerminalInterface terminalInterface){
        //this.terminalInterface = terminalInterface;
    }

    /**
     * Prints each Order object in incomingOrders ArrayList
     * @author Tommy Fenske
     */
    public void printIncomingOrders() {
        //Debug Statement
        //System.out.println("DEBUG: Incoming orders count = " + incomingOrders.size());
        for (Order order : incomingOrders) {
            System.out.println(order.toString());
        }
    }

    /**
     * Prints each Order object in startedOrders ArrayList
     * @author Tommy Fenske
     */
    public void printStartedOrders() {
        for (Order order : startedOrders) {
            System.out.println(order.toString());
        }
    }

    /**
     * Prints each Order object in completedOrders ArrayList
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

    /**
     * Calling our FileExport to be used by the user to export the orders
     * into one Json file
     * @author Ruben Vallejo
     */

    public void fileExport(){
        ExportFile fileToExport = new ExportFile();
        fileToExport.exportOrdersToJSON(this.getIncomingOrders(),
                this.getStartedOrders(),this.getCompletedOrders());
    }

    public static void setupWatcher() {
        Thread t = new Thread(() -> {
            File dataDir = new File("data");

            while(pollDirectory) {
                try {
                    //System.out.println("Sleep");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Platform.runLater(() -> {
                    //System.out.println("Poll");
                    for (String s : dataDir.list()) {
                        // TODO: handle null pointer exception
                        System.out.println(s);
                        // Get reference to individual file
                        File currentFile = new File( dataDir.getPath() + "/" + s);

                        // TODO: Code for sending file to the FileHandler will go here
                        guiController.addIncomingOrders();

                        // Delete file
                        if (currentFile.delete()) {
                            System.out.println("Deleted the file: " + currentFile.getName());
                        } else {
                            System.out.println("Failed to delete the file: " + currentFile.getName());
                        }
                    }
                });
            }
        });
        t.setName("Polling Thread");
        t.start();
    }
}
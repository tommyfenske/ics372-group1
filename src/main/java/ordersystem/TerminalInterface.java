package ordersystem;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import ordersystem.OrderManager;

/**
 * Used to interface with the user using the terminal.
 * The TerminalInterface should create FileHandlers as needed,
 * and call methods from the OrderManager class.
 * @author Tommy Fenske
 *
 *
 * What the interface needs to do:
 * 1. Import orders from JSON
 *      Start a new FileHandler instance
 *
 * 2. List orders
 *
 * 3. Manipulate current orders
 *      Start an order, display an order, complete an order
 *
 * 4. Export orders to
 *
 * 5. Exit program
 */

public class TerminalInterface {
    private boolean exitProgram = false;
    private final Scanner myScan = new Scanner(System.in);
    private OrderManager orderManager;

    /**
     * Constructor function that introduces the program and then starts the command loop
     * until the user is done with the program.
     * @author Tommy Fenske
     */

    //Updating so OrderManager Object is passed through TerminalInterface class
    //Fixing the stackOverflow issue
    public TerminalInterface() {
        orderManager = new OrderManager();

        printStars();
        System.out.println("\nRestaurant Order Tracking System");
        System.out.println("By ICS 372 Group 1\n");
        printStars();
        System.out.println();

        while (!exitProgram) {
            getCommand();
        }
    }

    /**
     * Prints general commands available to the user, and gets input on what command to run.
     * @return void
     * @author Tommy Fenske
     */
    public void getCommand() {
        // Output list of commands
        printStars();
        System.out.println("Commands:");
        System.out.println("[1]\tInput Order from JSON File");
        System.out.println("[2]\tList Orders");
        System.out.println("[3]\tManipulate Orders");
        System.out.println("[4]\tExport Orders to JSON File");
        System.out.println("[5]\tExit Program.");
        printStars();
        System.out.println();

        // Get integer input from user and store in command variable
        int command = getIntInput();

        // Choose what command to run
        switch (command) {
            case 1: // INPUT FROM JSON FILE
                jsonInput();
                break;
            case 2: // LIST ORDERS
                listOrders();
                break;
            case 3: // MANIPULATE ORDERS
                manipulateOrders();
                break;
            case 4: // EXPORT TO JSON FILE
                exportOrders();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.println("Invalid Command.");
                break;
        }
    }

    /**
     * Instantiates FileHandler class to get list of Orders, and passes them to OrderManager.
     * @author Tommy Fenske
     */
    private void jsonInput() {
        System.out.println("JSON Input Started.");
        orderManager.fileFromJSON();
        System.out.println("JSON Input Finished.");
    }

    /**
     * Connects to OrderManager class to get list all orders.
     * @author Tommy Fenske
     */
    private void listOrders() {
        // TODO: When OrderManager has more functionality, call method to print orders.
        System.out.println("List Orders Started.");

        System.out.println("Incoming Orders: ");
        orderManager.printIncomingOrders();
        System.out.println();

        System.out.println("Started Orders: ");
        orderManager.printStartedOrders();
        System.out.println();

        System.out.println("Completed Orders: ");
        orderManager.printCompletedOrders();
        System.out.println();

        System.out.println("List Orders Finished.");
    }

    /**
     * Prints order manipulation commands available to the user,
     * and gets input on what command to run. Then calls the appropriate
     * method in the Order Manager class.
     * @author Tommy Fenske
     */
    private void manipulateOrders() {
        System.out.println("Manipulate Orders Started.");

        // Output list of commands
        printStars();
        System.out.println("Manipulate Incoming Order Commands:");
        System.out.println("[1]\tStart Order");
        System.out.println("[2]\tDisplay Order");
        System.out.println("[3]\tComplete Order");
        System.out.println("[4]\tBack");
        printStars();
        System.out.println();

        // Get integer input from user and store in command variable
        int command = getIntInput();

        // Choose what command to run
        int id; // id of order to be manipulated, assigned later by user input
        boolean result; // result of commands being sent to OrderManager;
        switch (command) {
            case 1: // START ORDER
                List<Order> incoming = orderManager.getIncomingOrders();

                // Print incoming orders and get id from user
                printStars();
                System.out.println("Incoming Orders Not Started:");
                if (incoming.isEmpty()) { // if no incoming orders, exit
                    System.out.println("NO INCOMING ORDERS");
                    printStars();
                    break;
                } else {
                    for (Order o :  incoming) {
                        System.out.println(o);
                    }
                    printStars();
                    id = getIDInput();
                    result = orderManager.startOrder(id);
                    if (!result) {
                        System.out.printf("No order matches the ID: %d\n", id);
                    } else {
                        System.out.printf("Order ID: %d has been started.\n", id);
                    }
                }

                break;
            case 2: // DISPLAY ORDER
                // Print all orders
                printStars();
                System.out.println("Incoming Orders:");
                orderManager.printIncomingOrders();
                System.out.println("Started Orders:");
                orderManager.printStartedOrders();
                System.out.println("Completed Orders:");
                orderManager.printCompletedOrders();
                printStars();

                id = getIDInput(); // Get id of order to display
                Order toDisplay = orderManager.getOrder(id); // Get Order object from OrderManager
                if (toDisplay == null) {
                    System.out.println("No order matches the ID: " + id);
                    break;
                } else {
                    System.out.printf("Displaying order ID: %d\n", id);
                    printStars();
                    System.out.println(toDisplay.displayOrder()); // Print order details
                    printStars();
                }
                break;
            case 3: // COMPLETE ORDER
                List<Order> started = orderManager.getStartedOrders();

                // Print incoming orders and get id from user
                printStars();
                System.out.println("Started Orders Not Completed:");
                if (started.isEmpty()) { // if no incoming orders, exit
                    System.out.println("NO STARTED ORDERS");
                    printStars();
                    break;
                } else {
                    for (Order o :  started) {
                        System.out.println(o);
                    }
                    printStars();
                    id = getIDInput();
                    result = orderManager.completeOrder(id);
                    if (!result) {
                        System.out.printf("No order matches the ID: %d\n", id);
                    } else {
                        System.out.printf("Order ID: %d has been completed.\n", id);
                    }
                }
                break;
            case 4: // BACK
                break;
            default:
                System.out.println("Invalid Command.");
                break;
        }

        System.out.println("Manipulate Orders Finished.");
    }

    /**
     * Connects to ? class to export current orders into a JSON file.
     * @author Tommy Fenske
     */
    private void exportOrders() {
        // TODO: When FileExport class is added, call method to export current orders from OrderManager class.
        System.out.println("Export Orders Started.");
        // orderManager.exportOrdersToJSON();
        System.out.println("Export Orders Finished.");
    }

    /**
     * Exits the TerminalInterface part of the program.
     * @author Tommy Fenske
     */
    private void exit() {
        System.out.println("Exiting Program.");
        myScan.close();
        exitProgram = true;
    }

    /**
     * Prompts the user to input an integer to be used in commands
     * @return The next integer the user inputs
     * @author Tommy Fenske
     */
    private int getIntInput() {
        System.out.print("Enter the integer of desired command: ");
        return myScan.nextInt();
    }

    /**
     * Prompts the user to input an id to be used in commands
     * @return The next integer the user inputs
     * @author Tommy Fenske
     */
    private int getIDInput() {
        System.out.print("Enter the ID of desired order: ");
        return myScan.nextInt();
    }



    /**
     * Prints a line of spaced out stars to make the program look nice.
     * @return void
     * @author Tommy Fenske
     */
    private void printStars() {
        System.out.println("*\t*\t*\t*\t*\t*\t*\t*");
    }

}
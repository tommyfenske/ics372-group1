package ordersystem;

import java.io.*;
import java.util.Scanner;

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

    /**
     * Constructor function that introduces the program and then starts the command loop
     * until the user is done with the program.
     * @author Tommy Fenske
     */
    public TerminalInterface() {
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
        System.out.println("[3]\tManipulate Incoming Orders");
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
        FileHandler fh = new FileHandler();
        fh.jsonParsing();

        // TODO: When FileHandler and OrderManager have more functionality, pass orders between them
        // TODO: Maybe OrderManager should directly instantiate FileHandler to reduce coupling?
        // ArrayList<Order> newOrders = fh.jsonParsing();
        // OrderManager orderMan.incomingOrders(newOrders);
        System.out.println("JSON Input Finished.");
    }

    /**
     * Connects to OrderManager class to get list all orders.
     * @author Tommy Fenske
     */
    private void listOrders() {
        // TODO: When OrderManager has more functionality, call method to print orders.
        System.out.println("List Orders Started.");

        // OrderManager orderMan.listOrders();

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
        System.out.print("Enter the integer of the command you would like: ");
        int command = getIntInput();

        // Choose what command to run
        switch (command) {
            case 1: // START ORDER
                // orderMan.startOrder();
                break;
            case 2: // DISPLAY ORDER
                // orderMan.displayOrder();
                break;
            case 3: // COMPLETE ORDER
                // orderMan.completeOrder(orderID);
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
     * Prints a line of spaced out stars to make the program look nice.
     * @return void
     * @author Tommy Fenske
     */
    private void printStars() {
        System.out.println("*\t*\t*\t*\t*\t*\t*\t*");
    }

}
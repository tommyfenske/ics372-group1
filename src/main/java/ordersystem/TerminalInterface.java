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
 * 3. Export orders to JSON
 */

public class TerminalInterface {

    /**
     * Constructor function that introduces the program and then prompts the user for a command
     * @author Tommy Fenske
     */
    public TerminalInterface() {
        printStars();
        System.out.println("\nRestaurant Order Tracking System");
        System.out.println("By ICS 372 Group 1\n");
        printStars();
        System.out.println();

        getCommand();
    }

    /**
     * Prints commands available to the user, and gets input on what command to run.
     * @return void
     * @author Tommy Fenske
     */
    public void getCommand() {
        // Declare scanner object
        Scanner myScan = new Scanner(System.in);

        // Output list of commands
        printStars();
        System.out.println("Commands:");
        System.out.println("[1]\tInput Order from JSON File");
        System.out.println("[2]\tList Orders");
        System.out.println("[3]\tManipulate Orders");
        System.out.println("[4]\tExport Orders to JSON File");
        System.out.println();

        // Get integer input from user and store in command variable
        System.out.print("Enter the integer of the command you would like: ");
        int command = myScan.nextInt();

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
            default:
                System.out.println("Invalid Command.");
                break;
        }
    }

    private void jsonInput() {
        System.out.println("JSON Input");
    }

    private void listOrders() {
        System.out.println("List Orders");
    }

    private void manipulateOrders() {
        System.out.println("Manipulate Orders");
    }

    private void exportOrders() {
        System.out.println("Export Orders");
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



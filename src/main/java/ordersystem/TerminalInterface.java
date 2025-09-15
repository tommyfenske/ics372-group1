package ordersystem;

import java.io.*;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

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

        while (!exitProgram) {
            getCommand();
        }
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
        System.out.println("[5]\tExit Program.");
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
            case 5:
                exit();
                break;
            default:
                System.out.println("Invalid Command.");
                break;
        }
    }

    /**
     * Connects to FileHandler class to get JSON Order input files.
     * @author Tommy Fenske
     */
    private void jsonInput() {
        System.out.println("JSON Input");
        fileHandler fh = new fileHandler();
        fh.jsonParsing();
        System.out.println("done.");
    }

    /**
     * Connects to OrderManager class to get list all orders.
     * @author Tommy Fenske
     */
    private void listOrders() {
        System.out.println("List Orders");
    }

    /**
     *
     * @author Tommy Fenske
     */
    private void manipulateOrders() {
        System.out.println("Manipulate Orders");
    }

    /**
     * Connects to ? class to export current orders into a JSON file.
     * @author Tommy Fenske
     */
    private void exportOrders() {
        System.out.println("Export Orders");
    }

    /**
     * Exits the TerminalInterface part of the program.
     * @author Tommy Fenske
     */
    private void exit() {
        System.out.println("Exiting Program.");
        exitProgram = true;
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



package org.example;
import ordersystem.*;
import ordersystem.ExportFile;

//Imports for fx





public class Main {
    public static void main(String[] args) {
        System.out.println("Program Started.");

        //OrderManager orderManager = new OrderManager();
        TerminalInterface terminalInterface = new TerminalInterface();


        //Starting the stage for javaFX


        //orderManager.setTerminalInterface(terminalInterface);

        //ExportFile exporter = new ExportFile(orderManager);

        // Test if ExportFile can talk to OrderManager
        //exporter.testConnection();
    }
}
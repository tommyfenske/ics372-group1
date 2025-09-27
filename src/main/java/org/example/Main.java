package org.example;
import ordersystem.*;
import ordersystem.ExportFile;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program Started.");

        OrderManager orderManager = new OrderManager();
        TerminalInterface terminalInterface = new TerminalInterface();

        orderManager.setTerminalInterface(terminalInterface);

        //ExportFile exporter = new ExportFile(orderManager);

        // Test if ExportFile can talk to OrderManager
        //exporter.testConnection();
    }
}
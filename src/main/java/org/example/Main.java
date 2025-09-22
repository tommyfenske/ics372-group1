package org.example;
import ordersystem.OrderManager;
import ordersystem.ExportFile;

import ordersystem.TerminalInterface;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Program Started.");

        OrderManager orderManager = new OrderManager();
        //TerminalInterface terminalInterface = new TerminalInterface();

        ExportFile exporter = new ExportFile(orderManager);

        // Test if ExportFile can talk to OrderManager
        exporter.testConnection();
    }
}
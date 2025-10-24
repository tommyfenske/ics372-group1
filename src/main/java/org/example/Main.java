package org.example;
import javafx.application.Application;
import ordersystem.*;

//Imports for fx





public class Main {
    public static void main(String[] args) {
        System.out.println("Program Started.");

        Application.launch(GUI.class, args);

        TerminalInterface terminalInterface = new TerminalInterface();





        //Starting the stage for javaFX


        //orderManager.setTerminalInterface(terminalInterface);

        //ExportFile exporter = new ExportFile(orderManager);

        // Test if ExportFile can talk to OrderManager
        //exporter.testConnection();

    }
}
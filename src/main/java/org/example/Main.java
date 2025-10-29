package org.example;
import javafx.application.Application;
import javafx.application.Platform;
import ordersystem.*;

import java.io.File;

//Imports for fx





public class Main {

    public static void main(String[] args) {
        System.out.println("Program Started.");

        Application.launch(GUIController.class, args);
    }

}
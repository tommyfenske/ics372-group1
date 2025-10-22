package ordersystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ordersystem.OrderManager;


/**
 * This class serves as a graphical user interface, it uses Java fx to
 * accomplish this and is connected to the rest of our project
 *
 * @author Ruben
 */

public class GUI extends Application {

    //Private order manager object for functionality

    private OrderManager uiOrderManager = new OrderManager();

    @Override
    public void start(Stage stage) throws Exception {

        Button loadButton = new Button("Import/Load Orders");

        loadButton.setOnAction(e -> {uiOrderManager.fileFromJSON();
        uiOrderManager.printIncomingOrders();});





    }
}

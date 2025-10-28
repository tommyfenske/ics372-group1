package ordersystem;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * This class serves as a graphical user interface, it uses Java fx to
 * accomplish this and is connected to the rest of our project
 *
 * As of 10/267/25 This is now using fxml to make a view in the GUIView.fxml file,
 * and this file GUIController controls all the functions for the view
 *
 *
 */

public class GUIController extends Application {


    /**
     * Creating the buttons, labels and components needed to have
     * our GUIController be responsive in the way that we need it to be
     */


    @FXML private Button importJsonButton;
    @FXML private Button exportJsonButton;
    @FXML private Button loadOrderButton;
    @FXML private Button startOrderButton;
    @FXML private Button completeOrderButton;
    @FXML private Button getOrderButton;
    @FXML private Button showOrderButton;

    @FXML private Label orderStatusLabel;
    @FXML private Label headerLabel;
    @FXML private Label outputLabel;




    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource("GUIView.fxml"));
        Scene scene = new Scene(sceneLoader.load());
        stage.setScene(scene);
        stage.setTitle("Order System GUIController");
        stage.show();

    }


    /**
     * All control functions should go below this comment
     */

    //TODO Finish implementing all the functions needed

    @FXML
    public void jsonInput(){
        outputLabel.setText("Importing...");

    }

    @FXML
    public void exportOrders(){
        outputLabel.setText("Exporting...");

    }

    @FXML
    public void listOrders(){
        outputLabel.setText("Loading/Listing...");

    }


}

package ordersystem;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;


/**
 * This class serves as a graphical user interface, it uses Java fx to
 * accomplish this and is connected to the rest of our project
 *
 * @author Ruben
 */

public class GUI extends Application {

    //Private GUIController object for functionality
    private final GUIController guiController = new GUIController(this);

    /**
     * Creating the buttons, labels and components needed to have
     * our GUI be responsive in the way that we need it to be
     */

    Button importJsonButton = new Button("Import File");
    Button loadOrdersButton = new Button("Load Orders");
    Button startOrderButton = new Button("Start Order");
    Button completeOrderButton = new Button("Complete Order");
    Button getOrderButton = new Button("Get Order");
    Button showOrdersButton = new Button("Show Orders");

    Label orderStatusLabel = new Label("Placeholder");
    Label headerLabel = new Label("Welcome to the Order System!");
    Label outputLabel = new Label();


    private Region topControls(){

        HBox topBar = new HBox(10, importJsonButton, loadOrdersButton, showOrdersButton);
        topBar.setAlignment(Pos.CENTER);
        return topBar;
    }

    private Region orderControls(){

        HBox orderControlBar = new HBox(10, startOrderButton, completeOrderButton, getOrderButton);
        orderControlBar.setAlignment(Pos.CENTER);
        return orderControlBar;

    }

    private Region outputContent(){
        VBox outputSection = new VBox(10,orderStatusLabel,outputLabel);
        outputSection.setAlignment(Pos.BASELINE_CENTER);
        return outputSection;
    }

    @Override
    public void start(Stage stage) throws Exception {


        //Hooking things up
        importJsonButton.setOnAction(e -> guiController.importJsonButtonPressed() );
        loadOrdersButton.setOnAction(e -> guiController.loadOrdersButtonPressed() );
        startOrderButton.setOnAction(e -> guiController.startOrderButtonPressed() );
        completeOrderButton.setOnAction(e -> guiController.completeOrderButtonPressed() );
        getOrderButton.setOnAction(e -> guiController.getOrderButtonPressed() );
        showOrdersButton.setOnAction(e -> guiController.showOrdersButtonPressed());


        VBox rootLayout = new VBox(20, headerLabel, topControls(), orderControls(), outputContent());
        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.setPrefSize(600, 400);


        javafx.scene.Scene scene = new javafx.scene.Scene(rootLayout);
        stage.setScene(scene);
        stage.setTitle("Order System GUI");
        stage.show();

    }


    //TODO Finish implementing all logic for buttons and labels to make it complete

    public void listOrders() {

        //List<Order> startedOrders = uiOrderManager.getStartedOrders();

        //List<Order> incomingOrders = uiOrderManager.getIncomingOrders();

        //List<Order> completedOrders = uiOrderManager.getCompletedOrders();


    }

    public void manipulateOrders(String userAction) {

        switch (userAction){

            case "Start Order":
                orderStatusLabel.setText("Order Started Placeholder");
                break;
            case "Complete Order":
                orderStatusLabel.setText("Order Complete Placeholder");
                break;
            case "Get Order":
                orderStatusLabel.setText("Order Extracted Placeholder");
                break;

        }


    }

    public void exportOrders() {

        outputLabel.setText("Exporting....");

    }

    public void exit() {

        outputLabel.setText("Now Exiting");

    }

    public void jsonInput() {

        outputLabel.setText("Importing....");

    }
}

package ordersystem;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.util.*;


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

    private static OrderManager orderManager;

    /**
     * Creating the buttons, labels and components needed to have
     * our GUIController be responsive in the way that we need it to be
     */
    @FXML private Label outputLabel;

    @FXML private Label selectedOrderLabel;
    @FXML private Label selectedOrderDisplayLabel;
    @FXML private Label orderErrorLabel;
    @FXML private VBox incomingOrderList;
    @FXML private VBox startedOrderList;
    @FXML private VBox completedOrderList;
    //For populating the orderDetails
    @FXML private Label orderDetails;

    public GUIController() {
        // Create save directory and save file if they do not exist
        Saver.setup();
        orderManager = Saver.loadOrderManager();
        orderManager.setGUIController(this);
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource("GUIView.fxml"));
        Scene scene = new Scene(sceneLoader.load());
        //Getting controller
        GUIController controller = sceneLoader.getController();

        //Adding logic for the close function to fix the bug of the thread staying open
        stage.setOnCloseRequest(event -> {
            event.consume(); // This stops fx from auto closing
            controller.exitProgram();
        });

        stage.setScene(scene);
        stage.setTitle("Order System GUIController");
        stage.setMaximized(true);
        stage.show();

        // Instantiate Thread to poll directory for new order files
        OrderManager.setupWatcher();
    }

    // Called AFTER FXML is loaded.
    public void initialize() {
        // Even when the orderErrorLabel style visibility is set to false, it shows when App is loaded.
        // So it needs to be manually set to false here.
        orderErrorLabel.setVisible(false);
        // Update GUI on start
        updateGUIOrders();
    }

    public void stop() throws Exception {
        orderManager.toggleWatcher(false);
        Platform.exit();
        System.exit(0); // To stop Thread from running after Window closes
    }

    /**
     * Defining an actual end program class, to fix the bug of the thread staying open
     */
    public void exitProgram(){
        Platform.runLater(() -> {

            //Adding popups for confirming exit
            Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
            exitAlert.setTitle("Confirming Exit");
            exitAlert.setHeaderText("Are you sure you want to exit the program?");
            exitAlert.setContentText("Current work will be saved in \"" + Saver.getSaveDirectory() + "\\\"");

            Optional<ButtonType> userSelection = exitAlert.showAndWait();

            if (userSelection.isPresent() && userSelection.get() == ButtonType.OK) {
                //For debugging
                System.out.print("Now Exiting Program");
                System.out.println("Order System has been stopped");

                // Save the OrderManager to the save file
                Saver.saveOrderManager(orderManager);

                //User said yes close program

                //Stop the watcher
                Platform.exit();
                System.exit(0); // To stop Thread from running after Window closes
            } else {
                //For debugging
                System.out.print("Exit Canceled");
                //User chose to cancel and not close program
                outputLabel.setText("Exit Canceled");
            }
        });
    }

    /**
     * All control functions should go below this comment
     */
    @FXML
    public void startOrder(){
        // Display error label and return if no order is selected
        if (selectedOrderLabel == null) {
            noSelectionError();
            return;
        }

        // Get order from selected label's user data
        Order selectedOrder = (Order) selectedOrderLabel.getUserData();
        if (selectedOrder.getStatus() == Order.orderStatus.INCOMING) {
            orderManager.startOrder(selectedOrder.getOrderID());
            updateGUIOrders();
            outputLabel.setText("Order Started.");
            orderErrorLabel.setVisible(false);
        } else {
            orderErrorLabel.setVisible(true);
            orderErrorLabel.setText("ERROR: Order needs to be Incoming in order to be Started.");
        }
    }

    @FXML
    public void completeOrder(){
        // Display error label and return if no order is selected
        if (selectedOrderLabel == null) {
            noSelectionError();
            return;
        }

        // Get order from selected label's user data
        Order selectedOrder = (Order) selectedOrderLabel.getUserData();
        // If Order fits criteria to be started
        if (selectedOrder.getStatus() == Order.orderStatus.STARTED) {
            // Update OrderManager
            orderManager.completeOrder(selectedOrder.getOrderID());
            updateGUIOrders();
            outputLabel.setText("Order Completed.");
            orderErrorLabel.setVisible(false);
        } else {
            orderErrorLabel.setVisible(true);
            orderErrorLabel.setText("ERROR: Order needs to be Started in order to be Completed.");
        }
    }

    @FXML
    public void cancelOrder() {
        // Display error label and return if no order is selected
        if (selectedOrderLabel == null) {
            noSelectionError();
            return;
        }

        // Get order from selected label's user data
        Order selectedOrder = (Order) selectedOrderLabel.getUserData();
        // If Order fits criteria to be completed
        if (selectedOrder.getStatus() != Order.orderStatus.COMPLETE) {
            // Cancel order in the OrderManager
            orderManager.cancelOrder(selectedOrder.getOrderID());
            updateGUIOrders();
            outputLabel.setText("Order Canceled.");
            orderErrorLabel.setVisible(false);
            // Remove selected order label reference
            selectedOrderLabel = null;
            selectedOrderDisplayLabel.setText("");
            // Update Order Details Label
            orderDetails.setText("Select an order to see details");
        } else {
            orderErrorLabel.setVisible(true);
            orderErrorLabel.setText("ERROR: Order is already completed and cannot be canceled.");
        }
    }

    @FXML
    public void orderErrorLabelClicked() {
        orderErrorLabel.setVisible(false);
    }

    @FXML
    public void openDataDirectory() throws IOException {
        File dataDir = new File("data");
        //Adding logic to ensure data folder exists
        if(!dataDir.exists()){

            dataDir.mkdirs();
        }
        openDirectory(dataDir);
    }

    @FXML
    public void openTestDirectory() throws IOException {
        File exampleDir = new File("test_orders");
        openDirectory(exampleDir);
    }

    public void openDirectory(File directory) throws IOException {
        // Create directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs();
        }
        // Open Directory
        Desktop desktop = Desktop.getDesktop();
        desktop.open(directory);
    }

    /**
     * Method creates and returns a Label object with text from an Order object's data
     * @param order the Order object to read data from
     * @return the Label object with text value changed and event listener added
     */
    private Label labelFromOrder(Order order) {
        // Create label
        Label myLabel = new Label(order.toString());
        myLabel.setUserData(order);
        myLabel.getStyleClass().add("order-label");

        // Add event listener that verifies it iss a Label object, then calls the orderLabelCLicked() method
        myLabel.setOnMouseClicked(event -> {
            if (event.getSource() instanceof Label) orderLabelClicked( (Label)event.getSource() );
        });

        myLabel.getStyleClass().add("order-label");

        // If added label was previously selected, reselect it
        if (selectedOrderLabel != null) {
            Order selectedOrder = (Order) selectedOrderLabel.getUserData();
            if (selectedOrder.getOrderID() == order.getOrderID()) {
                orderLabelClicked(myLabel);
            }
        }

        return myLabel;
    }

    /**
     * Sets the orderErrorLabel to display an error that no order was selected
     */
    private void noSelectionError() {
        orderErrorLabel.setVisible(true);
        orderErrorLabel.setText("ERROR: No Order selected.");
    }

    /**
     * A function to be called whenever a Label that represents on Order has been clicked on by the mouse.
     * @author Tommy Fenske
     */
    private void orderLabelClicked(Label label) {
        if (! (label.getUserData() instanceof Order) ) return;
        if (selectedOrderLabel != null) selectedOrderLabel.setStyle("");
        selectedOrderLabel = label;
        selectedOrderLabel.setStyle("-fx-background-color: gold;");
        Order order = (Order) label.getUserData();
        selectedOrderDisplayLabel.setText("Selected Order ID: " + order.getOrderID());

        orderDetails.setText(order.displayOrderOneLine());
    }

    /**
     * Adding an update GUI Orders method that will update all the orders
     * and put them in their perspective boxes based on ENUM type
     *
     * @author Ruben
     *
     * Call this method anytime there is ANY change to ensure all is updated properly
     */

    public void updateGUIOrders(){
        incomingOrderList.getChildren().clear();
        startedOrderList.getChildren().clear();
        completedOrderList.getChildren().clear();

        for(Order order: orderManager.getIncomingOrders())
        {
            incomingOrderList.getChildren().add(labelFromOrder(order));
        }

        for(Order order: orderManager.getStartedOrders())
        {
            startedOrderList.getChildren().add(labelFromOrder(order));
        }

        for(Order order: orderManager.getCompletedOrders())
        {
            completedOrderList.getChildren().add(labelFromOrder(order));
        }
    }
}
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
import java.net.URI;
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
    private Label selectedOrderLabel;

    /**
     * Creating the buttons, labels and components needed to have
     * our GUIController be responsive in the way that we need it to be
     */
    @FXML private Button importButton;
    @FXML private Button exportButton;
    @FXML private Button loadOrderButton;
    @FXML private Button startOrderButton;
    @FXML private Button completeOrderButton;
    @FXML private Button getOrderButton;
    @FXML private Button showOrderButton;
    @FXML private Button cancelOrderButton;

    @FXML private Button exitButton;

    @FXML private Label headerLabel;
    @FXML private Label outputLabel;

    @FXML private Label selectedOrderDisplayLabel;
    @FXML private VBox incomingOrderList;
    @FXML private VBox startedOrderList;
    @FXML private VBox completedOrderList;

    public GUIController() {
       orderManager = new OrderManager(this);
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
        stage.show();

        OrderManager.setupWatcher();
    }

    public void stop() throws Exception {

        orderManager.stopWatcher();
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
            exitAlert.setContentText("Current work will be saved in [placeholder]");

            Optional<ButtonType> userSelection = exitAlert.showAndWait();

            if (userSelection.isPresent() && userSelection.get() == ButtonType.OK) {
                //For debugging
                System.out.print("Now Exiting Program");

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

    //TODO Finish implementing all the functions needed

    @FXML
    public void importOrders(){
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

    @FXML
    public void addIncomingOrders(List<Order> orders) throws NullPointerException {
        for (Order o :  orders) {
            incomingOrderList.getChildren().add( labelFromOrder( o ) );
        }


    }

    @FXML
    public void startOrder(){
        if (selectedOrderLabel == null) return;
        Order selectedOrder = (Order) selectedOrderLabel.getUserData();
        if (selectedOrder.getStatus() != Order.orderStatus.INCOMING) return;

        orderManager.startOrder(selectedOrder.getOrderID());
        updateGUIOrders();
        outputLabel.setText("Order Started.");
    }

    @FXML
    public void completeOrder(){
        if (selectedOrderLabel == null) return;
        Order selectedOrder = (Order) selectedOrderLabel.getUserData();
        if (selectedOrder.getStatus() != Order.orderStatus.STARTED) return;

        orderManager.completeOrder(selectedOrder.getOrderID());
        updateGUIOrders();
        outputLabel.setText("Order Completed.");
    }

    @FXML
    public void openDataDirectory() throws IOException {
        // TODO: add exception handling
        File dataDir = new File("data");
        openDirectory(dataDir);
    }

    @FXML
    public void openTestDirectory() throws IOException {
        // TODO: add exception handling
        File exampleDir = new File("test_orders");
        openDirectory(exampleDir);
    }

    public void openDirectory(File directory) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        URI uri = directory.toURI();
        desktop.browse(uri);

        //desktop.browse("");
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

        // Add event listener that verifies it iss a Label object, then calls the orderLabelCLicked() method
        myLabel.setOnMouseClicked(event -> {
            if (event.getSource() instanceof Label) orderLabelClicked( (Label)event.getSource() );
        });

        return myLabel;
    }

    /**
     * A function to be called whenever a Label that represents on Order has been clicked on by the mouse.
     * @author Tommy Fenske
     */
    private void orderLabelClicked(Label label) {
        if (! (label.getUserData() instanceof Order) ) return;
        selectedOrderLabel = label;
        selectedOrderLabel.setStyle("-fx-background-color: gold;");
        Order order = (Order) label.getUserData();
        selectedOrderDisplayLabel.setText("Selected Order ID: " + order.getOrderID());
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

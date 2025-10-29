package ordersystem;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private OrderManager orderManager = new OrderManager(this);

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

    @FXML VBox incomingOrderList;
    @FXML VBox startedOrderList;
    @FXML VBox completedOrderList;


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

    @FXML
    public void addIncomingOrders() {
        List<Item> testList = new ArrayList<>();
        incomingOrderList.getChildren().add( labelFromOrder( new Order("togo", testList) ) );
    }

    /**
     * Method creates and returns a Label object with text from an Order object's data
     * @param order the Order object to read data from
     * @return the Label object with text value changed and event listener added
     */
    private Label labelFromOrder(Order order) {
        // Create label
        Label myLabel = new Label(order.toString());

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
        // Temporary code to prove it works
        System.out.println(label.getText());
    }

}

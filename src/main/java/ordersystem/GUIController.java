package ordersystem;


/**
 *  GUIController is responsible for communicating between the GUI class and the OrderManager class.
 *  It receives input events from the GUI, then calls the appropriate methods on the OrderManager.
 */
public class GUIController {

    // Constructor assigns GUI Object instance reference
    private final GUI gui;
    // Instantiate new OrderManager
    private final OrderManager orderManager = new OrderManager(this);

    public GUIController(GUI gui) {
        this.gui = gui;
    }

    public void importJsonButtonPressed() {
        // TODO: change button name to "Import Orders Button"
        System.out.println("Import JSON Button Pressed.");

        // TODO: connect this method to the File facade class we create later
        orderManager.fileFromJSON();
    }

    public void loadOrdersButtonPressed() {
        System.out.println("Load Orders Button Pressed.");
    }

    public void startOrderButtonPressed() {
        System.out.println("Start Order Button Pressed.");

        // TODO: should this call a method in GUI class to update the text instead?
        gui.orderStatusLabel.setText("Order Started Placeholder");
    }

    public void completeOrderButtonPressed() {
        System.out.println("Complete Order Button Pressed.");

        gui.orderStatusLabel.setText("Order Complete Placeholder");
    }

    public void getOrderButtonPressed() {
        System.out.println("Get Order Button Pressed.");

        gui.orderStatusLabel.setText("Order Extracted Placeholder");
    }

    public void showOrdersButtonPressed() {
        System.out.println("Show Orders Button Pressed.");

        // this method calls print methods for now, until GUI can display lists of orders
        orderManager.printIncomingOrders();
        orderManager.printStartedOrders();
        orderManager.printCompletedOrders();

        // TODO: Once the GUI displays orders, we shouldn't need this method any longer since Orders should be displayed automatically
    }
}

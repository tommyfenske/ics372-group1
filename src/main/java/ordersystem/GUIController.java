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
        // TODO: change button name to "Import Orders Button", since we can import XML too?
        System.out.println("Import JSON Button Pressed.");
        gui.outputLabel.setText("Importing....");

        // TODO: connect this method to the File facade class we create later
        orderManager.fileFromJSON();
    }

    public void loadOrdersButtonPressed() {
        System.out.println("Load Orders Button Pressed.");
        gui.outputLabel.setText("Loading....");
    }

    public void startOrderButtonPressed() {
        System.out.println("Start Order Button Pressed.");

        // TODO: should this call a method in GUI class to update the text instead?
        /* For example:

            gui.updateOrderStatusLabel("Order Started Placeholder");

        This way the gui class is responsible for changes to its own elements.
        Is this better or just extra work?
         */
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

    /**
     *  This method should be called by the OrderManager after orders are added or changed.
     *  Contains pseudocode for now.
     */
    public void updateGUIOrders() {
        // for each order in the order manager,
            // create a corresponding label and add it to the appropriate GUI list element

        System.out.println("Update GUI Orders.");
    }
}

package ordersystem;
import javafx.scene.control.Button;

import java.util.List;




/** * OrderInterface serves as essentially a refactoring of our * TerminalInterface, this is now a generic interface that will be used by * our program to help bridge the layers of it. Has methods/functions that are * used to connect the UI layer to the logic that is being done on the actual * Json Files. * * @author Ruben Vallejo */




public interface OrderInterface {



//Functions that are to be used

//Connecting the UI layer to the orderManager


    void listOrders();

    void manipulateOrders(String userAction);

    void exportOrders();

    void exit();

    void jsonInput();

}
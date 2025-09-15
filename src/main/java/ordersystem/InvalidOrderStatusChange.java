package ordersystem;

/**
 * Exception to handle invalid attempts to change order to a state that is already true.
 * @author Jordan Curtis
 */

public class InvalidOrderStatusChange extends Exception {
    public InvalidOrderStatusChange(String message) {
        super(message);
    }
}

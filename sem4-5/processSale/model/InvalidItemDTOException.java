package processSale.model;

/**
 * An exception to be thrown whenever an <code>ItemDTO</code>
 * is equal to null.
 */
public class InvalidItemDTOException extends Exception {
    /**
     * Creates an instance with a message representing that a null object was passed
     * instead of an object of type ItemDTO.
     */
    public InvalidItemDTOException() {
        super("An error has occurred, please try again.");
    }
}
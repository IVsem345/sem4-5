package processSale.model;

/**
 * An exception to be thrown whenever a <code>quantity</code>
 * is less than 1.
 */
public class InvalidQuantityException extends Exception {
    /**
     * Creates an instance with a message representing for what <code>itemIdentifier</code>
     * an invalid quantity was given.
     * @param itemIdentifier The identifier.
     * @param invalidQuantity The quantity.
     */
    public InvalidQuantityException(String itemIdentifier, int invalidQuantity) {
        super("An invalid quantity of " + invalidQuantity + " was entered.");
    }
}

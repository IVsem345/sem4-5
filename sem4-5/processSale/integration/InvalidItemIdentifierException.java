package processSale.integration;

/**
 * An exception to be thrown whenever an <code>itemIdentifier</code>
 * can not be found in the inventory system.
 */
public class InvalidItemIdentifierException extends Exception {
    private String invalidItemIdentifier;
    private String errorMessage;

    /**
     * Creates an instance with a message representing what <code>itemIdentifier</code>
     * could not be found.
     * @param invalidItemIdentifier identifier that was invalid.
     */
    public InvalidItemIdentifierException(String invalidItemIdentifier) {
        super("Could not find " + invalidItemIdentifier + " in the inventory.");
        this.invalidItemIdentifier = invalidItemIdentifier;
    }

    /**
     * @return The <code>itemIdentifier</code> that was invalid.
     */
    public String getInvalidItemIdentifier() {
        return invalidItemIdentifier;
    }

}

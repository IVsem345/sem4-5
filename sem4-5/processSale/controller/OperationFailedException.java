package processSale.controller;

/**
 * Represents a failure in an operation.
 */
public class OperationFailedException extends Exception {
    /**
     * Creates an instance that represents an error message and type of error.
     * @param message Error message.
     * @param cause Type of error.
     */
    public OperationFailedException(String message, Exception cause) {
        super(message, cause);
    }

}

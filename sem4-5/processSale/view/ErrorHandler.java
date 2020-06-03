package processSale.view;

/**
 * Handles errors and prints to user.
 */
public class ErrorHandler {
    /**
     * Formats and prints error to user.
     * @param message Message to be formatted.
     */
    public void printError(String message) {
        StringBuilder errorMessage = new StringBuilder();
        errorMessage.append("Error: ");
        errorMessage.append(message);
        System.out.println(errorMessage);
    }
}

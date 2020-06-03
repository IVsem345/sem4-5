package processSale.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Prints error information to a file.
 */
public class LogHandler {
    private PrintWriter file;
    private String fileName;

    /**
     * Creates an instance and initiates communication with a file.
     */
    public LogHandler() throws IOException {
        fileName = "error_log.txt";
        file = new PrintWriter(new FileWriter(fileName, true), true);
    }

    /**
     * Builds an error message and prints to file.
     * @param exception Object with information about error.
     */
    public void printError(Exception exception) {
        StringBuilder msgToBeLogged = new StringBuilder();
        msgToBeLogged.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        msgToBeLogged.append(", Exception thrown: " + exception.getMessage());
        file.println(msgToBeLogged.toString());
        exception.printStackTrace(file);
    }

}

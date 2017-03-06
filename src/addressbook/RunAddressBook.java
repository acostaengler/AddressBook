package addressbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class RunAddressBook {

    private static final Logger log = Logger.getLogger(RunAddressBook.class.getName());

    public static void main(String[] args) throws IOException {

        logging();
        log.log(Level.INFO, "Program started");
        new AddressBookMenu();

    }

    private static void logging() {
        String loggingFilePath = "/Users/Katia/AddressBook/logging.properties";
        try (FileInputStream fileInputStream = new FileInputStream(loggingFilePath)) {
            LogManager.getLogManager().readConfiguration(fileInputStream);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Could not log to file", e);
            throw new RuntimeException("Could not load log properties", e);
        }
    }

}
package addressbook;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressBookMenu {

    private static final Logger log = Logger.getLogger(AddressBookMenu.class.getName());

    AddressBookMenu() {
        firstAction();
    }

    private void firstAction(){
        Scanner input = new Scanner(System.in);
        AddressBookOptions addressbookoption = new AddressBookOptions();

        System.out.println("\nWelcome\n");
        addressbookoption.getServerContacts();
        addressbookoption.autoSaving();

        try {
            while (true) {
                String choice = input.next();
                switch (choice) {
                    case "add":
                        log.log(Level.FINE, "User used command 'add'");
                        addressbookoption.addContact(input.next(), input.next(), input.next());
                        break;
                    case "list":
                        log.log(Level.FINE, "User used command 'list'");
                        addressbookoption.showAllContact();
                        break;
                    case "search":
                        System.out.println("\nSearch contact: \n");
                        log.log(Level.FINE, "User used command 'search'");
                        addressbookoption.searchContact(input.next());
                        break;
                    case "quit":
                        addressbookoption.exitProgram();
                        System.out.println("Bye bye!");
                        log.log(Level.FINE, "User used command 'quit'");
                        break;
                    case "help":
                        log.log(Level.FINE, "User used command 'help'");
                        addressbookoption.helpMenu();
                        break;
                    case "delete":
                        System.out.println("\nPlease input ID: \n");
                        log.log(Level.FINE, "User used command 'delete'");
                        addressbookoption.deleteContact(input.next());
                        break;
                    default:
                        System.out.println("\nUnknown command " + choice + " please try again!");
                        log.log(Level.FINE, "Unknown command");

                }
            }
        }catch(Exception e) {
            log.log(Level.SEVERE, "ERROR in program" + e);
        }
    }
}
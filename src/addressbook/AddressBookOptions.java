package addressbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressBookOptions {

    private static final Logger log = Logger.getLogger(AddressBookOptions.class.getName());
    private ArrayList<ContactInformation> contactList = new ArrayList<>();
    private ArrayList<ContactInformation> serverContacts = new ArrayList<>();
    private SaveToFile save =  new SaveToFile();
    private Client catalogue = new Client();


    void getServerContacts(){
        catalogue.getAll(serverContacts);
    }

    void exitProgram(){
        try {
            save.saveList(contactList);
            log.log(Level.INFO,"Exiting the program");
            System.exit(0);
        } catch (IOException e) {
            log.log(Level.SEVERE,"Something went wrong " + e);
        }
    }

    void autoSaving(){
        try {
            save.Autosave(contactList);
        } catch (IOException e) {
            log.log(Level.SEVERE,"Something went wrong " + e);
        }
    }

    void addContact(String firstname, String lastname, String emailaddress) {

        ContactInformation newcontact;

        newcontact = new ContactInformation(firstname, lastname, emailaddress);
        newcontact.setFirstName(firstname);
        newcontact.setLastName(lastname);
        newcontact.setEmailAddress(emailaddress);
        contactList.add(newcontact);
        System.out.println("\nContact successfully added\n");
        log.log(Level.INFO,"Contact got added");
    }

    void showAllContact() {

        int counter = 0;
        for (ContactInformation i : contactList) {
            System.out.println(contactPrintOut(i));
            counter++;
        }
        for (ContactInformation n : serverContacts) {
            if (serverContacts.isEmpty()) {
                System.out.println("could not find contacts");
            }
            else{
                System.out.println(contactPrintOut(n));
                counter++;
            }
        }
        log.log(Level.INFO,"Show all contacts");

        if (counter == 0) {
            System.out.println("\nYou have no contacts in your address book\n");
            log.log(Level.INFO,"No contacts in address book");
        }
    }


    void searchContact(String searchinput) {

        int counter = 0;

        for (ContactInformation n : contactList) {
            if (n.getFirstName().toLowerCase().startsWith(searchinput.toLowerCase())
                    || n.getLastName().toLowerCase().startsWith(searchinput.toLowerCase())
                    || n.getEmailAddress().toLowerCase().startsWith(searchinput.toLowerCase())) {
                System.out.println(contactPrintOut(n));
                counter++;
            }
        }
        for (ContactInformation i : serverContacts) {
            if (i.getFirstName().toLowerCase().startsWith(searchinput.toLowerCase())
                    || i.getLastName().toLowerCase().startsWith(searchinput.toLowerCase())
                    || i.getEmailAddress().toLowerCase().startsWith(searchinput.toLowerCase())) {
                System.out.println(contactPrintOut(i));
                counter++;
            }
        }

        if (counter == 0) {
            System.out.println("\nThis person is not saved in the address book\n");
            log.log(Level.INFO,"Person not saved in the address book");
        }
        else {
            log.log(Level.INFO,"Found contact");
        }
    }

    void helpMenu() {

        System.out.println("\nadd    - Add new contact");
        System.out.println("list   - Show all your contacts");
        System.out.println("search - Search contact");
        System.out.println("delete - Delete a contact\n");
        System.out.println("quit - quit addressbook\n");
        log.log(Level.INFO,"Help menu");


    }

    void deleteContact(String id) {

        for (Iterator<ContactInformation> it = contactList.iterator(); it.hasNext(); ) {
            ContactInformation item = it.next();
            if (id.equalsIgnoreCase(String.valueOf(item.getId()))) {
                it.remove();
            }
        }

        System.out.println("Item removed");
        log.log(Level.INFO,"Contact deleted");
    }

    private String contactPrintOut(ContactInformation i){

        return  "ID: " + i.getId() + "\n" +
                "Firstname: " + i.getFirstName() + "\n" +
                "Lastname: " + i.getLastName() + "\n" +
                "Emailaddress: " + i.getEmailAddress() + "\n";
    }
}
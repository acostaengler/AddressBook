package addressbook;

import java.io.Serializable;
import java.util.UUID;

class ContactInformation implements Serializable {

    private UUID id;
    private String firstname;
    private String lastname;
    private String emailaddress;

    ContactInformation(String firstname, String lastname, String emailaddress){

        this.id = java.util.UUID.randomUUID();
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailaddress = emailaddress;
    }

    UUID getId() {return id;}

    void setID(UUID id){
        this.id = id;
    }

    String getFirstName() {return firstname;}

    void setFirstName(String firstname) {this.firstname = firstname;}

    String getLastName() {
        return lastname;
    }

    void setLastName(String lastname) {
        this.lastname = lastname;
    }

    String getEmailAddress() {
        return emailaddress;
    }

    void setEmailAddress(String mail) {
        this.emailaddress = mail;
    }

    public String toString() {
        return  "ID: " + id + "\n" +
                "Firstname: " + firstname + "\n" +
                "Lastname: " + lastname + "\n" +
                "Emailaddress: " + emailaddress + "\n";
    }
}
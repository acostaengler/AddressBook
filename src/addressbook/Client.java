package addressbook;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private Logger logging = Logger.getLogger(Client.class.getName());


    void getAll(ArrayList<ContactInformation> catalogueList) {

        new Thread(() -> {

            try {
                Socket socket = new Socket();
                try {
                    socket = new Socket("localhost", 61616);
                } catch (Exception e) {
                    System.out.println("Could not load server contacts (Server is offline)\n");
                    logging.log(Level.SEVERE,"could not connect to server");
                }

                OutputStream ostream = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(ostream);

                InputStream istream = socket.getInputStream();
                InputStreamReader istreamReader = new InputStreamReader(istream);

                BufferedReader br = new BufferedReader(istreamReader);
                writer.println("getall");
                writer.flush();
                for (String line = br.readLine(); line != null; line = br.readLine()) {
                    String[] contactsplit = line.split(",");

                    ContactInformation contact = new ContactInformation(contactsplit[1], contactsplit[2], contactsplit[3]);
                    UUID id = UUID.fromString(contactsplit[0]);
                    contact.setID(id);
                    catalogueList.add(contact);
                    logging.log(Level.INFO, "Server contacts successfully loaded");
                }
                ostream.close();
                writer.close();
                istream.close();
                istreamReader.close();
                br.close();

                writer.println("exit");
                writer.flush();
            } catch (Exception e) {
                logging.log(Level.SEVERE, "Something went wrong: " + e);
            }
        }
        ).start();
    }
}



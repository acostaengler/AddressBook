package addressbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveToFile {

    private final Object lock = new Object();
    private Logger log = Logger.getLogger(SaveToFile.class.getName());
    private File file = new File("contactlist.ser");


    void saveList(ArrayList<ContactInformation> listToSave) throws IOException {

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        try {
            synchronized (lock) {
                oos.writeObject(listToSave);
                fos.close();
                oos.close();
            }

        } catch (IOException e) {
            fos.close();
            oos.close();
            log.log(Level.SEVERE,"Error trying to save " +  e);

        }
    }
    void Autosave(ArrayList<ContactInformation> listToSave) throws IOException{

        Thread newthread = new Thread(() -> {
            while (true) {
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(listToSave);
                    log.log(Level.FINE, "Autosaved");
                    fos.close();
                    oos.close();
                } catch (IOException e) {
                    log.log(Level.SEVERE, "Error something went wrong while autosaving:" + e);
                }
            }
        });
        newthread.start();
    }
}
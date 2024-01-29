import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Klasa obsługująca proces logowania uzytkownikow. Przechowuje dane logowania dla uczniów, nauczycieli oraz administratora.
 */
public class Login {
    HashMap<String, String> uczenLogs = new HashMap<String, String>();
    HashMap<String, String> nauczycielLogs = new HashMap<String, String>();
    HashMap<String, String> adminLog = new HashMap<String, String>();

    Login(){
        readUczenLogsFromFile();
        readNauczycielLogsFromFile();
        // Odczyt z pliku wszystkich danych do zalogowania jako uczen
        // lub nauczyciel
        // dane dla Administratora
        adminLog.put("admin", "qd4f6g8j5");
    }

    /**
     * Metoda odczytujaca dane logowania dla uczniow z pliku.
     */
    private void readUczenLogsFromFile() {
        String path = "C:\\Users\\user\\Desktop\\FilesIO\\uczenLogs.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // czytanie dla formatu "email haslo"
                String[] words = line.split(" ");
                if (words.length == 2) {
                    uczenLogs.put(words[0], words[1]);
                }
            }
        } catch
         (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda odczytujaca dane logowania dla nauczycieli z pliku.
     */
    private void readNauczycielLogsFromFile() {
        String path = "C:\\Users\\user\\Desktop\\FilesIO\\nauczycielLogs.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // czytanie dla formatu "email haslo"
                String[] words = line.split(" ");
                if (words.length == 2) {
                    nauczycielLogs.put(words[0], words[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda zapisujaca dane logowania dla ucznia do pliku.
     */
    void writeLogUczen(String email, String haslo){
        String path = "C:\\Users\\user\\Desktop\\FilesIO\\uczenLogs.txt";
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.append(email).append(" ").append(haslo).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda zapisujaca dane logowania dla nauczyciela do pliku.
     */
    void writeLogNauczyciel(String email, String haslo) throws IOException {
        String path = "C:\\Users\\user\\Desktop\\FilesIO\\nauczycielLogs.txt";
        try (FileWriter writer = new FileWriter(path, true)){
            writer.append(email).append(" ").append(haslo).append("\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    protected HashMap<String, String> getUczenLogin(){
        return uczenLogs;
    }

    protected HashMap<String, String> getNauczycielLogin(){
        return nauczycielLogs;
    }

    protected HashMap<String, String> getAdminLog(){
        return adminLog;
    }
}

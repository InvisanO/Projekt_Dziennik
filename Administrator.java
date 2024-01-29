import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentujaca Administratora dziedziczy po klasie Osoba, implementuje interfejs Adminterfejs.
 */
public class Administrator extends Osoba implements AdmInterface{

    /** Parametr przechowujace nazwe administratora. */
    private String username;

    @Override
    public void rozwiazProblem(){
        List<String> problemy = readProblemsFromFile();
        for (String problem : problemy) {
            appendToOutputArea("Rozwiazanie problemu: " + problem);
        }
    }

    @Override
    public List<String> readProblemsFromFile() {
        List<String> problems = new ArrayList<>();
        try (BufferedReader buffer = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\FilesIO\\problems.txt"))) {
            String line;
            while ((line = buffer.readLine()) != null) {
                problems.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (problems.isEmpty()) {
            problems.add("Nie ma problemow.");
        }
        return problems;
    }

    /**
     * Metoda drukujaca informacje o administratorze.
     */
    @Override
    public void wydrukujInfo() {
        appendToOutputArea(username);
    }

    /**
     * Konstruktor domyslny.
     */
    public Administrator(){
        super();

    }

    /**
     * Konstruktor klasy Administrator.
     */
    public Administrator(String username) {
        this.username = username;
    }

    @Override
    public void setZarobkiForNauczyciel(String email, String newZarobki) {
        String path = "C:\\Users\\user\\Desktop\\FilesIO\\zarobki.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(email + " " + newZarobki);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        appendToOutputArea("Nowa pensja wynosi: " + newZarobki);
    }

    /**
     * Metoda wyswietla tekst w paneli wykorzystujac GUI.
     *
     * @param text Tekst do wyswietlenia.
     */
    private void appendToOutputArea(String text) {
        SwingUtilities.invokeLater(() -> StronaGlowna.outputTextArea.append(text + "\n"));
    }
}


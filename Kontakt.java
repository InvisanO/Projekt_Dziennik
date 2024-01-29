import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * Klasa Kontakt umozliwiajaca dodawanie problemow oraz zglaszanie ich. Jest serializowalna.
 */
public class Kontakt implements Serializable {

    /**
     * Parametr problem przechowujacy opis problemu.
     */
    private String problem;

    /**
     * Metoda dodajaca problem do obiektu klasy Kontakt.
     *
     * @param problem Opis zglaszanego problemu.
     */
    public void dodajProblem(String problem){
        this.problem = problem;
    }

    /**
     * Metoda dokonujaca zgloszenia problemu.
     */
    public void zgloszenieProblemu() {
        if (problem.equals("")){
            appendToOutputArea("Opisz problem");
        }
        else {
            saveProblemToFile(problem);
            appendToOutputArea("Problem zgłoszony: " + problem);
        }
    }

    /**
     * Metoda wyswietla tekst w paneli wykorzystujac GUI.
     *
     * @param text Tekst do wyswietlenia.
     */
    private void appendToOutputArea(String text) {
        SwingUtilities.invokeLater(() -> StronaGlowna.outputTextArea.append(text + "\n"));
    }

    /**
     * Metoda zapisujaca problem do pliku.
     *
     * @param problem Opis problemu do zapisu.
     */
    private void saveProblemToFile(String problem) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\user\\Desktop\\FilesIO\\problems.txt", true))) {
            writer.write(problem);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

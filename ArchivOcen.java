import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Klasa ArchivOcen zarzadza archiwum ocen dla roznych przedmiotow.
 */
public class ArchivOcen{

    private Map<String, Double> tabelaOcen;

    /**
     * Konstruktor klasy ArchivOcen, inicjalizuje HashMape ocen.
     */
    public ArchivOcen() {
        tabelaOcen = new HashMap<>();
    }

    /**
     * Sciezka do pliku przechowujacego oceny.
     */
    private String path = "C:\\Users\\user\\Desktop\\FilesIO\\Oceny.txt";

    /**
     * Metoda dodajaca ocene dla danego przedmiotu do archiwum.
     *
     * @param ocena Ocena do dodania.
     * @param przedmiot Przedmiot, dla którego dodawana jest ocena.
     */
    public void dodajOcene(double ocena, String przedmiot) {
        tabelaOcen.put(przedmiot, ocena);

        try (FileWriter writer = new FileWriter(path)) {
            for (Map.Entry<String, Double> entry : tabelaOcen.entrySet()) {
                String line = String.format("%s %.2f%n", entry.getKey(), entry.getValue());
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda wyswietlajaca zarchiwizowane oceny.
     */
    public void printOceny(){
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            appendToOutputArea("Zczytywanie oczen:");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s");
                if (parts.length == 2) {
                    String przedmiot = parts[0];
                    double ocena = Double.parseDouble(parts[1]);
                    appendToOutputArea("\n" + przedmiot + " - " + ocena);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
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

}





import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Klasa Harmonogram wykorzystywana do generowania, odczytu
 * i ustawiania harmonogramu zajec.
 */
public class Harmonogram {

    /**
     * Metoda wyswietla tekst w paneli wykorzystujac GUI.
     *
     * @param text Tekst do wyswietlenia.
     */
    private static void appendToOutputArea(String text) {
        SwingUtilities.invokeLater(() -> StronaGlowna.outputTextArea.append(text + "\n"));
    }

    /**
     * Metoda odczytujaca harmonogram z pliku i wyświetlajaca go w interfejsie graficznym.
     */
    public static void getHarmonogram() {
        try (FileReader reader = new FileReader("C:\\Users\\user\\Desktop\\FilesIO\\Harmonogram.txt");
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                appendToOutputArea(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda ustawiajaca harmonogram zajec.
     *
     * @param harmonogram Lista z danymi harmonogramu.
     */
    public static void setHarmonogram(ArrayList<String> harmonogram) {
        try (FileWriter writer = new FileWriter("C:\\Users\\user\\Desktop\\FilesIO\\Harmonogram.txt")) {
            for (String el : harmonogram) {
                writer.write(el + " ");
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda generujaca przykladowy harmonogram zajęć.
     * Wykorzystuje klase anonimowa dla harmonogramu.
     */
    public static void nowyHarmonogram() {

        // klasa anonimowa
        Harmonogram harmonogram = new Harmonogram(){
            @Override
            ArrayList<String> generateHarmonogram() {
                ArrayList<String> harmonogram = new ArrayList<>();
                harmonogram.add("Przedmiot: Matematyka");
                harmonogram.add("Nauczyciel: Kenny Nilson");
                harmonogram.add("Sala: 101");
                harmonogram.add("Godziny: 8:00 - 10:00");
                harmonogram.add("Dni: Poniedzialek, Piatek");
                return harmonogram;
            }
        };

        setHarmonogram(harmonogram.generateHarmonogram());
    }

    /**
     * Metoda, ktorej celem jest generowanie harmonogramu zajec.
     * Implementowana w klasie anonimowej.
     *
     * @return Lista z danymi harmonogramu.
     */
    ArrayList<String> generateHarmonogram() {
        ArrayList<String> harmonogram = new ArrayList<>();
        return harmonogram;
    }

}





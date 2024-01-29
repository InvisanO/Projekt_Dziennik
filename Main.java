import javax.swing.*;
import java.io.*;

/**
 * Klasa główna aplikacji zawierająca różne funkcje.
 */
public class Main {

    /**
     * Metoda zapisuje obiekt typu osoba do pliku.
     *
     * @param osoba zmienna wykorzystywana do zapisu.
     * @param path sciezka do pliku.
     */
    public static void zapiszObjekt(Osoba osoba, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(osoba.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metoda wyswietla tekst w paneli wykorzystujac GUI.
     *
     * @param text Tekst do wyswietlenia.
     */
    private static void appendToOutputArea(String text) {
        SwingUtilities.invokeLater(() -> StronaGlowna.outputTextArea.append(text + "\n"));
    }


    // nie ma rejestracji dla Admina
    // generic
    /**
     * Metoda wczytuje obiekt z pliku, uzywajac klasy generycznej.
     *
     * @param path sciezka do pliku.
     * @param klasa klasa generyczna reprezentująca typ obiektu.
     * @return obiekt wczytany z pliku.
     * @throws IOException obsluga operacji wejścia, wyjścia.
     */
    public static <T> T wczytajObjekt(String path, Class<T> klasa) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String[] lines = new String[8];  // czytanie z pliku

            for (int i = 0; i < lines.length; i++) {
                lines[i] = reader.readLine();
            }
            int id = Integer.parseInt(lines[1].substring(lines[1].indexOf("-") + 1).trim());
            String imie = lines[2].substring(lines[2].indexOf("-") + 1).trim();
            String nazwisko = lines[3].substring(lines[3].indexOf("-") + 1).trim();
            int telefon = Integer.parseInt(lines[4].substring(lines[4].indexOf("-") + 1).trim());
            String email = lines[5].substring(lines[5].indexOf("-") + 1).trim();
            String haslo = lines[6].substring(lines[6].indexOf("-") + 1).trim();

            if (klasa.equals(Nauczyciel.class)) {
                String przedmiot1 = lines[7].substring(lines[7].indexOf("-") + 1).trim();

                return klasa.cast(new Nauczyciel(id, imie, nazwisko, telefon, email, haslo, przedmiot1));
            } else if (klasa.equals(Uczen.class)) {
                String charak1 = lines[7].substring(lines[7].indexOf("-") + 1).trim();

                return klasa.cast(new Uczen(id, imie, nazwisko, telefon, email, haslo, charak1));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metoda serializuje obiekt typu Uczen i zapisuje go do pliku.
     *
     * @param uczen obiekt do zserializowania.
     */
    public static void serializeUczen(Uczen uczen) {
        try {
            String path1 = "C:\\Users\\user\\Desktop\\FilesIO\\uczenSerializacja.txt";
            FileOutputStream fileOut = new FileOutputStream(path1);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(uczen);
            out.close();
            fileOut.close();
            appendToOutputArea("Objekt zachowany po serializacji");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializuje obiekt typu Uczen z wczesniej zapisanego pliku.
     *
     * @return zdeserializowany obiekt typu Uczen.
     */
    public static Uczen deserializeUczen() {
        Uczen uczen = null;
        try {
            String path1 = "C:\\Users\\user\\Desktop\\FilesIO\\uczenSerializacja.txt";
            FileInputStream fileIn = new FileInputStream(path1);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            uczen = (Uczen) in.readObject();
            in.close();
            fileIn.close();
            appendToOutputArea(uczen.imie);
            appendToOutputArea(uczen.haslo);
            long serialVersionUID = ObjectStreamClass.lookup(uczen.getClass()).getSerialVersionUID();
            appendToOutputArea(String.valueOf(serialVersionUID));


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return uczen;
    }

    /**
     * Glowna metoda aplikacji.
     * Tworzy strone poczatkowa
     */
    public static void main (String[]args) throws IOException, ClassNotFoundException {

        Strona1 stronaPoczatkowa = new Strona1();
    }
}




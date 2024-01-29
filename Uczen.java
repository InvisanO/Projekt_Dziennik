import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.*;

/**
 * Klasa reprezentująca ucznia, dziedziczy po klasie Osoba implementuje Serializacje.
 */
public class Uczen extends Osoba implements Serializable {

    /**
     * Charakterystyka ucznia.
     */
    private String charakterystyka;
    /**
     * Lista przedmiotow ucznia.
     */
    transient private List<String> przedmioty;
    /**
     * Numer seryjny do obslugi serializacji.
     */
    private static final long serialVersionUID = 145722472;
    /**
     * Konstruktor klasy Uczen.
     *
     * @param id              ID ucznia.
     * @param imie            Imię ucznia.
     * @param nazwisko        Nazwisko ucznia.
     * @param telefon         Numer telefonu ucznia.
     * @param email           Adres email ucznia.
     * @param haslo           Haslo ucznia.
     * @param charakterystyka Charakterystyka ucznia.
     */
    public Uczen(int id, String imie, String nazwisko, int telefon, String email, String haslo, String charakterystyka)  {
        super(id, imie, nazwisko, telefon, email, haslo);
        this.charakterystyka = charakterystyka;
        this.przedmioty = new ArrayList<>();
    }

    /**
     * Przeladowanie funkcji abstrakcyjnej klasy Osoba
     */
    @Override
    public void wydrukujInfo() {
        appendToOutputArea("Imie: " + imie + ". Nazwisko: " + nazwisko + ". Charakterystyka: " + charakterystyka + ".");
    }


    public String getImie(){return super.getImie();}

    public String getNazwisko(){return super.getNazwisko();}

    public Integer getTelefon(){return super.getTelefon();};

    public String getEmail(){return super.getEmail();};

    public String getHaslo(){return super.getHaslo();};

    /**
     * Metoda wyswietla tekst w paneli wykorzystujac GUI.
     *
     * @param text Tekst do wyswietlenia.
     */
    private void appendToOutputArea(String text) {
        SwingUtilities.invokeLater(() -> StronaGlowna.outputTextArea.append(text + "\n"));
    }

    public String getCharakterystyka(){
        return this.charakterystyka;
    }

    /**
     * Metoda dodaje przedmiot do listy przedmiotów ucznia.
     *
     * @param przedmiot przedmiot do dodania.
     */
    public void dodajPrzedmiot(String przedmiot) {
        if (przedmiot.equals("")){
            appendToOutputArea("Wpisz przedmiot");
        }else{
            przedmioty.add(przedmiot);
            appendToOutputArea("Dodano przedmiot: " + przedmiot);
        }
    }

    /**
     * Metoda usuwa przedmiot z listy przedmiotów ucznia.
     *
     * @param przedmiot przedmiot do usuniecia.
     */
    public void usunPrzedmiot(String przedmiot) {
        if (przedmiot.equals("")){
            appendToOutputArea("Wpisz przedmiot");
        }else {
            przedmioty.remove(przedmiot);
            appendToOutputArea("Usuniety przedmiot: " + przedmiot);
        }
    }

    /**
     * Metoda wyswietla harmonogram ucznia.
     */
    public void pokazHarmonogram(){
        Harmonogram.getHarmonogram();
    }

    /**
     * Metoda wykonuje zadanie nauczyciela.
     */
    public void wykonajZadanie() {
        // nowe Zadanie
        Nauczyciel.Zadanie zadanieDoWykonania = readZadanieFromFile("C:\\Users\\user\\Desktop\\FilesIO\\zadania.txt");
        if (zadanieDoWykonania != null) {
            // Execute the zadanie
            appendToOutputArea("Zadanie " + zadanieDoWykonania.getTytul() + " wykonane");

            // Additional logic for handling the zadanie execution, if needed
        } else {
            appendToOutputArea("Brak zadania do wykonania");
        }

    }

    /**
     * Metoda zcytuje zadanie z pliku.
     * @param path Sciezka do pliku.
     * @return Zwraca zadanie.
     */
    private Nauczyciel.Zadanie readZadanieFromFile(String path) {
        try (Scanner scanner = new Scanner(new File(path))) {
            //
            if (scanner.hasNextLine()) {
                String[] zadanieInfo = scanner.nextLine().split(",");
                if (zadanieInfo.length == 4) {
                    String tytul = zadanieInfo[0];
                    String termin = zadanieInfo[1];
                    String opis = zadanieInfo[2];
                    int punkty = Integer.parseInt(zadanieInfo[3]);

                    return new Nauczyciel.Zadanie(tytul, termin, opis, punkty);
                }
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metoda toString wypisujaca zawartosci parametrow kalsy Uczen.
     */
    @Override
    public String toString() {
        return "Uczen: " + '\n' +
                "id - " + id + '\n' +
                "imie - " + imie + '\n' +
                "nazwisko - " + nazwisko + '\n' +
                "telefon - " + telefon + '\n' +
                "email - " + email + '\n' +
                "haslo - " + haslo + '\n' +
                "charakterystyka: - " + charakterystyka;
    }
}

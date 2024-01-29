import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Klasa reprezentująca Nauczyciela, dziedziczy po klasie Osoba.
 */
public class Nauczyciel extends Osoba {

    /**
     * Nazwa przedmiotu nauczyciela.
     */
    public String przedmiot;
    /**
     * Lista uczniów przypisanych do nauczyciela.
     */
    public ArrayList<Osoba> uczniowie;
    /**
     * Lista przechowujaca oceny uczniow, nie moge byc serializowane poprzez typ transient).
     */
    transient private ArchivOcen archivOcen;
    /**
     * Dodaje ocenę do archiwum ocen nauczyciela.
     *
     * @param ocena ocena do dodania.
     * @param przedmiot przedmiot, do ktorego przypisana jest ocena.
     */
    public void putOcena(double ocena, String przedmiot) {
        archivOcen.dodajOcene(ocena, przedmiot);
    }
    /**
     * Wyswietla oceny nauczyciela.
     */
    public void wyswietlOceny(){
        archivOcen.printOceny();
    }

    /**
     * Konstruktor klasy Nauczyciel.
     *
     * @param id       ID nauczyciela.
     * @param imie     imię nauczyciela.
     * @param nazwisko nazwisko nauczyciela.
     * @param telefon  numer telefonu nauczyciela.
     * @param email    adres email nauczyciela.
     * @param haslo    haslo nauczyciela.
     * @param przedmiot przedmiot nauczyciela.
     */
    public Nauczyciel(int id, String imie, String nazwisko, int telefon, String email, String haslo, String przedmiot) {
        super(id, imie, nazwisko, telefon, email, haslo);
        this.przedmiot = przedmiot;
        archivOcen = new ArchivOcen();
        uczniowie = new ArrayList<>();
    }

    /**
     * Wewnetrzna klasa Zadanie, reprezentujaca zadanie dodawane przez nauczyciela.
     */
    public static class Zadanie {

        private String tytul;
        private String termin;
        private String opis;
        private int punkty;

        public Zadanie(String tytul, String termin, String opis, int punkty) {
            this.tytul = tytul;
            this.termin = termin;
            this.opis = opis;
            this.punkty = punkty;
        }
        public String getTytul() {
            return tytul;
        }

        public String getTermin() {
            return termin;
        }

        public String getOpis() {
            return opis;
        }

        public int getPunkty() {
            return punkty;
        }

        // usuwanie zadania
        public void delete() {
            this.tytul = null;
            this.termin = null;
            this.opis = null;
            this.punkty = 0;
        }
    }


    /**
     * Metoda dodaje ucznia do kolekcji uczniow nauczyciela.
     *
     * @param email adres email ucznia.
     * @throws IOException obsluga wyjatkow
     */
    public void dodajUcznia(String email) throws IOException {
        Osoba uczen;
        String path = "C:\\Users\\user\\Desktop\\FilesIO\\" + email + ".txt";
        try {
            uczen = Main.wczytajObjekt(path, Uczen.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        uczniowie.add(uczen);
        appendToOutputArea("Nowy uczeń dodany: " + uczen.getImie());
        // wykorzystanie polimorfizmu Zmienna uczen została zadeklarowana jako typ Osoba
        // wydrukujInfo() to metoda abstrakcyjna zdefiniowana w klasie Osoba
        // zostanie wykonana implementacja funkcji wydrukujInfo() w klasie Uczen
        uczen.wydrukujInfo();
    }
    /**
     * Metoda  dodaje nowe zadanie.
     *
     * @param tytul  Tytul zadania.
     * @param termin Termin zadania.
     * @param opis  Opis zadania.
     * @param punkty Punkty za zadanie.
     */
    public void pracaDomowa(String tytul, String termin, String opis, int punkty) {
        Zadanie zadanie = new Zadanie(tytul, termin, opis, punkty);
        appendToOutputArea("Nowe zadanie dodane: " + tytul);
        try (FileWriter writer = new FileWriter("C:\\Users\\user\\Desktop\\FilesIO\\zadania.txt", true)) {
            writer.write(String.format("%s,%s,%s,%d%n", tytul, termin, opis, punkty));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void usunZadanie(Zadanie zadanie) {
        zadanie.delete();
    }


    /**
     * Przeladowanie funkcji abstrakcyjnej klasy Osoba.
     */
    @Override
    public void wydrukujInfo() {
        appendToOutputArea("Imie: " + imie + ". Nazwisko: " + nazwisko + ". Przedmiot: " + przedmiot + ".");
    }

    public String getImie(){return super.getImie();}


    public String getNazwisko(){return super.getNazwisko();}


    public Integer getTelefon(){return super.getTelefon();}

    public String getEmail(){return super.getEmail();}

    public String getHaslo(){return super.getHaslo();}

    /**
     * Metoda wyswietla tekst w paneli wykorzystujac GUI.
     *
     * @param text Tekst do wyswietlenia.
     */
    private void appendToOutputArea(String text) {
        SwingUtilities.invokeLater(() -> StronaGlowna.outputTextArea.append(text + "\n"));
    }

    /**
     * Metoda toString() wypisujaca parametry klasy Nauczyciel.
     */
    @Override
    public String toString() {
        return "Nauczyciel: " + '\n' +
                "id - " + id + '\n' +
                "imie - " + imie + '\n' +
                "nazwisko - " + nazwisko + '\n' +
                "telefon - " + telefon + '\n' +
                "email - " + email + '\n' +
                "haslo - " + haslo + '\n' +
                "przedmiot - " + przedmiot;
    }

}

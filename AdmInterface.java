import java.util.List;

/**
 * Interfejs, definiuje metody zwiazane z funkcjonalnoscią klasy Administrator.
 */
public interface AdmInterface{

    /**
     * Metoda rozwiazujaca zgloszony problem.
     */
    void rozwiazProblem();

    /**
     * Metoda odczytujaca zgloszone problemy z pliku.
     *
     * @return Lista zawierająca zgloszone problemy.
     */
    List<String> readProblemsFromFile();


    /**
     * Metoda ustawiajaca nowe zarobki dla nauczyciela o podanym adresie email.
     *
     * @param email Adres email nauczyciela.
     * @param newZarobki Nowe zarobki dla nauczyciela.
     */
    void setZarobkiForNauczyciel(String email, String newZarobki);


}

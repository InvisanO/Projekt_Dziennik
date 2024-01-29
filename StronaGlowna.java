import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Po logowaniu

/**
 * Klasa reprezentujaca glowny interfejs po zalogowaniu.
 * Dziedziczy po klasie Kontakt
 */
public class StronaGlowna extends Kontakt implements ActionListener {

    JButton logoutButton = new JButton("Wylogowanie");
;   JFrame frame = new JFrame();
    JLabel mainLabel = new JLabel("Strona Glowna");
    JButton solveProblemButton = new JButton("Rozwiaz problem");
    JButton showHarmonogramButton = new JButton("Pokaz Harmonogram");
    JButton sendAssignmentButton = new JButton("Przeslij Zadanie");
    JButton serializeUczenButton = new JButton("Serialize Uczen");
    JButton deserializeUczenButton = new JButton("Deserialize Uczen");
    JTextField problemField = new JTextField(10);
    JButton zglosProblemButton = new JButton("Zglos Problem");
    JTextField przedmiotField = new JTextField(10);
    JButton dodajPrzedmiotButton = new JButton("Dodaj Przedmiot");
    JButton usunPrzedmiotButton = new JButton("Usun Przedmiot");
    JButton viewGradesButton = new JButton("Przegląd Ocen");
    JButton addStudentButton = new JButton("Dodaj Ucznia");
    JButton createAssignmentButton = new JButton("Utwórz Zadanie");
    JLabel pensjaLabel = new JLabel("Pensja");
    JLabel emailLabel = new JLabel("Email");
    JTextField pensjaField = new JTextField();
    JTextField emailField = new JTextField();
    JButton pensjaButton = new JButton("Zmien pensje");
    JScrollPane outputScrollPane = new JScrollPane();
    public static JTextArea outputTextArea;
    JLabel uczOc = new JLabel("Imie, Ocena: ");
    JTextField ocenaField = new JTextField(10);
    JButton putOcenaButton = new JButton("Dodaj ocene");
    JTextField imieUczenField = new JTextField(10);

    JTextField studentEmailField = new JTextField(10);
    JLabel studentNameLabel = new JLabel("Email ucznia:");

    JTextField assignmentTitleField = new JTextField(10);
    JTextField assignmentDueDateField = new JTextField(10);
    JTextField assignmentDescriptionField = new JTextField(10);
    JTextField assignmentPointsField = new JTextField(10);

    JLabel assignmentTitleLabel = new JLabel("Tytuł Zadania:");
    JLabel assignmentDueDateLabel = new JLabel("Termin Zadania:");
    JLabel assignmentDescriptionLabel = new JLabel("Opis Zadania:");
    JLabel assignmentPointsLabel = new JLabel("Punkty Zadania:");


    Uczen uczen;
    Nauczyciel nauczyciel;
    Administrator admin;

    StronaGlowna(String login, Logowanie.typ typ) throws IOException {
        mainLabel.setBounds(340, 30, 250, 35);
        mainLabel.setFont(new Font("Arial",Font.PLAIN, 20));
        mainLabel.setForeground(new Color(60, 229, 60));
        String path = "C:\\Users\\user\\Desktop\\FilesIO\\" + login + ".txt";
        switch (typ) {
            case UCZEN:

                uczen = Main.wczytajObjekt(path, Uczen.class);
                outputTextArea = new JTextArea();
                outputTextArea.setEditable(false);
                outputScrollPane.setBounds(500, 300, 300, 250);
                outputScrollPane.setViewportView(outputTextArea);
                frame.add(outputScrollPane);
                showHarmonogramButton.setBounds(100, 150, 200, 25);
                showHarmonogramButton.addActionListener(this);
                frame.add(showHarmonogramButton);

                sendAssignmentButton.setBounds(100, 250, 200, 25);
                sendAssignmentButton.addActionListener(this);
                frame.add(sendAssignmentButton);

                serializeUczenButton.setBounds(100, 350, 200, 25);
                serializeUczenButton.addActionListener(this);
                frame.add(serializeUczenButton);

                deserializeUczenButton.setBounds(100, 450, 200, 25);
                deserializeUczenButton.addActionListener(this);
                frame.add(deserializeUczenButton);

                problemField.setBounds(400, 150, 200, 25);
                zglosProblemButton.setBounds(620, 150, 200, 25);
                przedmiotField.setBounds(400, 200, 200, 25);
                dodajPrzedmiotButton.setBounds(620, 200, 200, 25);
                usunPrzedmiotButton.setBounds(500, 250, 200, 25);

                frame.add(problemField);
                frame.add(zglosProblemButton);
                frame.add(przedmiotField);
                frame.add(dodajPrzedmiotButton);
                frame.add(usunPrzedmiotButton);

                zglosProblemButton.addActionListener(this);
                dodajPrzedmiotButton.addActionListener(this);
                usunPrzedmiotButton.addActionListener(this);

                break;
            case NAUCZYCIEL:
                nauczyciel = Main.wczytajObjekt(path, Nauczyciel.class);
                outputTextArea = new JTextArea();
                outputTextArea.setEditable(false);
                outputScrollPane.setBounds(500, 150, 300, 300);
                outputScrollPane.setViewportView(outputTextArea);
                frame.add(outputScrollPane);
                uczOc.setBounds(350, 100, 100, 25);
                imieUczenField.setBounds(450, 100, 100, 25);
                ocenaField.setBounds(565, 100, 100, 25);

                frame.add(uczOc);
                frame.add(imieUczenField);
                frame.add(ocenaField);
                frame.add(putOcenaButton);
                putOcenaButton.setBounds(700, 100, 150, 25);
                putOcenaButton.addActionListener(this);

                studentNameLabel.setBounds(100, 150, 150, 25);
                studentEmailField.setBounds(210, 150, 100, 25);
                addStudentButton.setBounds(325, 150, 150, 25);

                assignmentTitleLabel.setBounds(100, 200, 150, 25);
                assignmentDueDateLabel.setBounds(100, 250, 150, 25);
                assignmentDescriptionLabel.setBounds(100, 300, 150, 25);
                assignmentPointsLabel.setBounds(100, 350, 150, 25);

                assignmentTitleField.setBounds(210, 200, 100, 25);
                assignmentDueDateField.setBounds(210, 250, 100, 25);
                assignmentDescriptionField.setBounds(210, 300, 100, 25);
                assignmentPointsField.setBounds(210, 350, 100, 25);

                createAssignmentButton.setBounds(100, 400, 150, 25);
                viewGradesButton.setBounds(100, 100, 200, 25);

                frame.add(viewGradesButton);
                frame.add(studentNameLabel);
                frame.add(studentEmailField);
                frame.add(addStudentButton);
                frame.add(assignmentTitleLabel);
                frame.add(assignmentDueDateLabel);
                frame.add(assignmentDescriptionLabel);
                frame.add(assignmentPointsLabel);
                frame.add(assignmentTitleField);
                frame.add(assignmentDueDateField);
                frame.add(assignmentDescriptionField);
                frame.add(assignmentPointsField);
                frame.add(createAssignmentButton);

                viewGradesButton.addActionListener(this);
                addStudentButton.addActionListener(this);
                createAssignmentButton.addActionListener(this);

                break;
            case ADMIN:
                admin = new Administrator("Admin");
                outputTextArea = new JTextArea();
                outputTextArea.setEditable(false);
                outputScrollPane.setBounds(500, 150, 300, 300);
                outputScrollPane.setViewportView(outputTextArea);
                frame.add(outputScrollPane);
                solveProblemButton.setBounds(100, 150, 200, 30);
                solveProblemButton.addActionListener(this);
                solveProblemButton.setFocusable(false);
                pensjaLabel.setBounds(100, 250, 200, 30);
                emailLabel.setBounds(100, 300, 200, 30);
                pensjaField.setBounds(200, 250, 200, 30);
                emailField.setBounds(200, 300, 200, 30);
                pensjaButton.setBounds(100, 400, 200, 30);
                pensjaButton.addActionListener(this);
                frame.add(pensjaLabel);
                frame.add(pensjaField);
                frame.add(emailField);
                frame.add(emailLabel);
                frame.add(pensjaButton);
                frame.add(solveProblemButton);
                break;
        }

        logoutButton.addActionListener(this);
        logoutButton.setBounds(600, 30, 200, 25);
        logoutButton.setFocusable(false);
        frame.add(logoutButton);
        frame.setBackground(new Color(114, 111, 111));
        frame.add(mainLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    /**
     * Metoda obslugująca akcję po wykonywane po przyciskach.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == showHarmonogramButton) {
            Harmonogram.nowyHarmonogram();
            uczen.pokazHarmonogram();
        } else if (e.getSource() == sendAssignmentButton) {
            uczen.wykonajZadanie();
        }else if (e.getSource() == serializeUczenButton) {
            Main.serializeUczen(uczen);
        } else if (e.getSource() == deserializeUczenButton) {
            Main.deserializeUczen();
        } else if (e.getSource() == zglosProblemButton) {
            String problem = problemField.getText();
            // wykorzystanie dziedziczenia klasy Kontakt
            dodajProblem(problem);
            zgloszenieProblemu();

        } else if (e.getSource() == dodajPrzedmiotButton) {
            String przedmiot = przedmiotField.getText();
            uczen.dodajPrzedmiot(przedmiot);
        } else if (e.getSource() == usunPrzedmiotButton) {
            String przedmiot = przedmiotField.getText();
            uczen.usunPrzedmiot(przedmiot);
        }if (e.getSource() == viewGradesButton) {
            nauczyciel.wyswietlOceny();
        } else if (e.getSource() == addStudentButton) {
            String studentEmail = studentEmailField.getText();
            try {
                nauczyciel.dodajUcznia(studentEmail);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == createAssignmentButton) {
            String title = assignmentTitleField.getText();
            String dueDate = assignmentDueDateField.getText();
            String description = assignmentDescriptionField.getText();
            int points = Integer.parseInt(assignmentPointsField.getText());
            nauczyciel.pracaDomowa(title, dueDate, description, points);
        }  else if (e.getSource() == putOcenaButton) {
            double ocena = Double.parseDouble(ocenaField.getText());
            String imie = imieUczenField.getText();
            nauczyciel.putOcena(ocena, imie);
        }else if (e.getSource() == solveProblemButton){
            admin.rozwiazProblem();
        } else if (e.getSource() == pensjaButton){
            String email = emailField.getText();
            String pensja = pensjaField.getText();
            admin.setZarobkiForNauczyciel(email, pensja);
        }else if (e.getSource() == logoutButton){
            frame.dispose();
            Strona1 stronaPoczatkowa = new Strona1();
        }
    }

}



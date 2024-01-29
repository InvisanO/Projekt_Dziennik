import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

/**
 * Klasa reprezentująca interfejs rejestracji nauczyciela. Umozliwia wprowadzanie danych, walidację i zapis do plików.
 */
public class RejestracjaNauczyciel implements ActionListener {

    JLabel mainLabel = new JLabel("Rejestracja jako Nauczyciel");
    JButton button;
    HashMap<String, String> logDlaNauczyciel = new HashMap<String, String>();
    JFrame frame = new JFrame();
    JLabel imieLabel;
    JTextField imieField;
    JLabel przedmiotLabel;
    JTextField przedmiot1Field;
    JLabel nazwiskoLabel;
    JTextField nazwiskoField;
    JLabel telefonLabel;
    JTextField telefonField;
    JLabel idLabel;
    JTextField idField;
    JLabel hasloLabel;
    JPasswordField hasloField;
    JLabel emailLabel;
    JTextField emailField;
    Login loginData;

    RejestracjaNauczyciel(Login log) {
        mainLabel.setBounds(280, 25, 250, 35);
        mainLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        mainLabel.setForeground(new Color(6, 1, 23));
        button = new JButton("Zatwierdz");
        emailField = new JTextField();
        imieField = new JTextField();
        przedmiot1Field = new JTextField();
        nazwiskoField = new JTextField();
        telefonField = new JTextField();
        idField = new JTextField();
        hasloField = new JPasswordField();
        loginData = log;
        imieLabel = new JLabel("Imie:");

        button.setBounds(425, 200, 150, 50);
        button.addActionListener(this);
        imieLabel.setBounds(50, 100, 75, 25);
        imieField.setBounds(125, 100, 200, 25);
        imieField.setFont(new Font("Arial", Font.BOLD, 12));
        nazwiskoLabel = new JLabel("Nazwisko");
        nazwiskoLabel.setBounds(50, 150, 75, 25);
        nazwiskoField.setBounds(125, 150, 200, 25);
        nazwiskoField.setFont(new Font("Arial", Font.BOLD, 12));
        przedmiotLabel = new JLabel("Przedmioty:");
        przedmiotLabel.setBounds(50, 400, 150, 25);
        przedmiot1Field.setBounds(150, 400, 160, 25);
        przedmiot1Field.setFont(new Font("Arial", Font.BOLD, 12));

        telefonLabel = new JLabel("Telefon:");
        telefonLabel.setBounds(50, 250, 75, 25);
        telefonField.setBounds(125, 250, 200, 25);
        telefonField.setFont(new Font("Arial", Font.BOLD, 12));
        idLabel = new JLabel("Id");
        idLabel.setBounds(50, 300, 75, 25);
        idField.setBounds(125, 300, 200, 25);
        idField.setFont(new Font("Arial", Font.BOLD, 12));
        emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 350, 75, 25);
        emailField.setBounds(125, 350, 200, 25);
        emailField.setFont(new Font("Arial", Font.BOLD, 12));
        hasloLabel = new JLabel("Haslo");
        hasloLabel.setBounds(50, 200, 75, 25);
        hasloField.setBounds(125, 200, 200, 25);
        hasloField.setFont(new Font("Arial", Font.BOLD, 12));
        //button.setFocusable(false);

        frame.add(imieField);
        frame.add(nazwiskoField);
        frame.add(przedmiot1Field);
        frame.add(telefonField);
        frame.add(idField);
        frame.add(emailField);
        frame.add(hasloField);
        frame.add(imieLabel);
        frame.add(nazwiskoLabel);
        frame.add(przedmiotLabel);
        frame.add(telefonLabel);
        frame.add(idLabel);
        frame.add(emailLabel);
        frame.add(hasloLabel);
        frame.add(button);
        frame.add(mainLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
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

        if (e.getSource() == button){
            String przedmiot1 = null;
            String przedmiot2 = null;
            String przedmiot3 = null;
            int id = 0;
            String imie = null;
            String nazwisko = null;
            int telefon = 0;
            String email = null;
            String haslo = null;
            imie = imieField.getText();
            nazwisko = nazwiskoField.getText();
            email = emailField.getText();
            haslo = String.valueOf(hasloField.getPassword());
            telefon = Integer.parseInt(telefonField.getText());
            id = Integer.parseInt(idField.getText());
            przedmiot1 = przedmiot1Field.getText();
            logDlaNauczyciel = loginData.getNauczycielLogin();

            if (!logDlaNauczyciel.containsKey(email)){
                if (!imie.isEmpty()){
                    if (!nazwisko.isEmpty()){
                        if (!email.isEmpty()){
                            if (!haslo.isEmpty()){
                                if (telefon != 0){
                                    if (id != 0){
                                        if (!przedmiot1.isEmpty()){
                                            Nauczyciel newNauczyciel = new Nauczyciel(id, imie, nazwisko, telefon, email,
                                                    haslo, przedmiot1);
                                            String path = "C:\\Users\\user\\Desktop\\FilesIO\\" + email + ".txt";
                                            Main.zapiszObjekt(newNauczyciel, path);
                                            try {
                                                loginData.writeLogNauczyciel(email, haslo);
                                            } catch (IOException ex) {
                                                throw new RuntimeException(ex);
                                            }
                                            frame.dispose();
                                            Login newLog = new Login();
                                            Logowanie log = new Logowanie(newLog);
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Nie wskazano pole Przedmioty", "Niepowodzenie", JOptionPane.WARNING_MESSAGE);
                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Nie wskazano pole Id", "Niepowodzenie", JOptionPane.WARNING_MESSAGE);
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Nie wskazano pole Telefon", "Niepowodzenie", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Nie wskazano pole Haslo", "Niepowodzenie", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Nie wskazano pole Email", "Niepowodzenie", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Nie wskazano pole Nazwisko", "Niepowodzenie", JOptionPane.WARNING_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Nie wskazano pole Imie", "Niepowodzenie", JOptionPane.WARNING_MESSAGE);
                }

            }else{
                JOptionPane.showMessageDialog(null, "Uzytkownik juz jest zarejestrowany! " +
                        "Sprobuj zalogowac sie", "Niepowodzenie", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}



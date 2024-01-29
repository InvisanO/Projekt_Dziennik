import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

/**
 * Klasa obslugujaca proces logowania uzytkownika.
 */
public class Logowanie implements ActionListener {

    HashMap<String, String> logDlaUczen = new HashMap<String, String>();
    HashMap<String, String> logDlaNauczyciel = new HashMap<String, String>();
    HashMap<String, String> logDlaAdmin = new HashMap<String, String>();

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Zatwierdz");
    JButton anulujButton = new JButton("Anuluj");
    JTextField loginField = new JTextField();
    JPasswordField hasloField = new JPasswordField();
    JLabel loginLabel = new JLabel("Wpish email:");
    JLabel hasloLabel = new JLabel("Wpish haslo:");
    JLabel label = new JLabel();
    JLabel comboBoxLabel = new JLabel();
    JLabel mainLabel = new JLabel("Strona logowania");
    JComboBox<String> comboBox;
    Login loginData;

    enum typ {
        UCZEN, NAUCZYCIEL, ADMIN
    }

    public Logowanie(Login log) {
        loginData = log;

        mainLabel.setBounds(125, 10, 250, 35);
        mainLabel.setFont(new Font(null, Font.PLAIN, 20));
        mainLabel.setForeground(Color.DARK_GRAY);
        loginLabel.setBounds(50, 160, 75, 25);
        hasloLabel.setBounds(50, 240, 75, 25);
        comboBoxLabel.setBounds(50, 100, 100, 25);
        label.setBounds(125, 330, 250, 35);
        label.setFont(new Font(null, Font.ITALIC, 20));
        comboBoxLabel.setText("Logowanie jako:");

        loginField.setBounds(125, 160, 200, 25);
        loginField.setFont(new Font("Arial", Font.BOLD, 12));
        hasloField.setBounds(125, 240, 200, 25);

        loginButton.setBounds(125, 300, 100, 25);
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);
        anulujButton.setBounds(225, 300, 100, 25);
        anulujButton.addActionListener(this);
        anulujButton.setFocusable(false);

        String[] users = {"Uczen", "Nauczyciel", "Admin"};
        comboBox = new JComboBox(users);
        comboBox.setBounds(200, 100, 125, 25);
        comboBox.addActionListener(this);
        comboBox.setEditable(true);


        frame.add(mainLabel);
        frame.add(comboBoxLabel);
        frame.add(comboBox);
        frame.add(loginButton);
        frame.add(anulujButton);
        frame.add(label);
        frame.add(loginField);
        frame.add(hasloField);
        frame.add(loginLabel);
        frame.add(hasloLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    /**
     * Metoda obslugująca akcję po wykonywane po przyciskach.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==anulujButton){
            loginField.setText("");
            hasloField.setText("");
        }
        if(e.getSource()==loginButton){
            String login = loginField.getText();
            String haslo = String.valueOf(hasloField.getPassword());
            if ("Uczen".equals(String.valueOf(comboBox.getSelectedItem()))){
                logDlaUczen = loginData.getUczenLogin();
                if (logDlaUczen.containsKey(login)) {
                    if (logDlaUczen.get(login).equals(haslo)) {
                        label.setForeground(Color.GREEN);
                        // wyswietlanie napisu

                        label.setText("Udane logowanie");
                        JOptionPane.showMessageDialog(null, label, "Sukces", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        try {
                            StronaGlowna strona = new StronaGlowna(login, typ.UCZEN);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        label.setForeground(Color.RED);
                        label.setText("Nieprawidlowe haslo");
                    }
                } else {
                    label.setForeground(Color.RED);
                    label.setText("Nieprawidlowy email");
                }
            }
            else if ("Nauczyciel".equals(String.valueOf(comboBox.getSelectedItem()))){
                logDlaNauczyciel = loginData.getNauczycielLogin();
                if (logDlaNauczyciel.containsKey(login)) {
                    if (logDlaNauczyciel.get(login).equals(haslo)) {
                        label.setForeground(Color.GREEN);
                        // wyswietlanie napisu
                        label.setText("Udane logowanie");
                        JOptionPane.showMessageDialog(null, label, "Sukces", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        try {
                            StronaGlowna strona = new StronaGlowna(login, typ.NAUCZYCIEL);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        label.setForeground(Color.RED);
                        label.setText("Nieprawidlowe haslo");
                    }
                } else {
                    label.setForeground(Color.RED);
                    label.setText("Nieprawidlowy email");
                }
            }
            else if ("Admin".equals(String.valueOf(comboBox.getSelectedItem()))){
                logDlaAdmin = loginData.getAdminLog();
                if (logDlaAdmin.containsKey(login)) {
                    if (logDlaAdmin.get(login).equals(haslo)) {
                        label.setForeground(Color.GREEN);
                        // wyswietlanie napisu

                        label.setText("Udane logowanie");
                        JOptionPane.showMessageDialog(null, label, "Sukces", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        try {
                            StronaGlowna strona = new StronaGlowna(login, typ.ADMIN);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        label.setForeground(Color.RED);
                        label.setText("Nieprawidlowe haslo");
                    }
                } else {
                    label.setForeground(Color.RED);
                    label.setText("Nieprawidlowy email");
                }
            }
            else{
                System.out.println("Nie istnieje takiego parametru!");
            }
        }
    }
}

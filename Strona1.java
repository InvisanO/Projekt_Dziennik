import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Klasa reprezentująca poczatkową strone aplikacji z opcjami rejestracji dla ucznia, nauczyciela oraz logowania.
 */
public class Strona1 implements ActionListener {
    // POCZATKOWA STRONA DLA WYBORU:
    // REJESTRACJA DLA UCZNIA
    // REJESTRACJA DLA NAUCZYCIELA
    // LOGOWANIE
    JFrame frame;
    JLabel label;
    JButton button;
    JRadioButton regNButton;
    JRadioButton regUButton;
    JRadioButton logButton;
    ButtonGroup buttonGroup;

    Strona1(){
        frame = new JFrame();
        label = new JLabel("Wybierz opcje:");
        button = new JButton("Zatwierdz");
        regNButton = new JRadioButton();
        regUButton = new JRadioButton();
        logButton = new JRadioButton();
        buttonGroup = new ButtonGroup();
        label.setBounds(150, 30, 250, 35);
        label.setFont(new Font("Arial",Font.PLAIN, 20));
        label.setForeground(new Color(92, 0, 255));

        button.setBounds(150, 325, 100, 25);
        button.addActionListener(this);
        button.setFocusable(false);

        regUButton.setText("Rejestracja Dla Ucznia");
        regNButton.setText("Rejestracja Dla Nauczyciela");
        logButton.setText("Logowanie");

        buttonGroup.add(regNButton);
        buttonGroup.add(regUButton);
        buttonGroup.add(logButton);

        regUButton.addActionListener(this);
        regNButton.addActionListener(this);
        logButton.addActionListener(this);
        regUButton.setBounds(75, 100, 225, 75);
        regNButton.setBounds(75, 175, 225, 75);
        logButton.setBounds(75, 250, 225, 75);
        logButton.setSelected(true);

        frame.add(regNButton);
        frame.add(regUButton);
        frame.add(logButton);
        frame.add(button);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
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
            if (regUButton.isSelected()) {
                frame.dispose();
                RejestracjaUczen regU = new RejestracjaUczen();
            }
            else if (regNButton.isSelected()) {
                frame.dispose();
                Login log = new Login();
                RejestracjaNauczyciel regN = new RejestracjaNauczyciel(log);
            }
            else if (logButton.isSelected()) {
                frame.dispose();
                Login log = new Login();
                Logowanie logowanie = new Logowanie(log);
            }
        }
    }
}

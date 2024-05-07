package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.Patient;

public class PatientLoginGUI extends JFrame implements ActionListener {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Patient patient;

    public PatientLoginGUI() {
        setTitle("Patient Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("Email:");
        panel.add(emailLabel);

        emailField = new JTextField();
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton);

        add(panel);

        patient = new Patient(); // Create Patient object
    }

    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        boolean loginSuccess = patient.patientLogin(email, password);
        if (loginSuccess) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            // Open the main patient interface window here
        } else {
            JOptionPane.showMessageDialog(this, "Login failed. Please check your email and password.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PatientLoginGUI loginGUI = new PatientLoginGUI();
            loginGUI.setVisible(true);
        });
    }
}

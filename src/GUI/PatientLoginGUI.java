package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

/**
 * Represents a graphical user interface for patient login.
 */
public class PatientLoginGUI extends JFrame implements ActionListener {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Patient patient;

    /**
     * Constructs a PatientLoginGUI object.
     */
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

    /**
     * Handles the actionPerformed event for login button.
     * @param e The ActionEvent object.
     */
    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        boolean loginSuccess = patient.patientLogin(email, password);
        if (loginSuccess) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            // Open the main patient interface window here
            openMainPatientInterface();
        } else {
            JOptionPane.showMessageDialog(this, "Login failed. Please check your email and password.");
        }
    }

    /**
     * Opens the main patient interface window.
     */
    private void openMainPatientInterface() {
        // Code to open the main patient interface window
        // Replace this with your implementation
    }

    /**
     * Main method for testing purposes.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PatientLoginGUI loginGUI = new PatientLoginGUI();
            loginGUI.setVisible(true);
        });
    }
}

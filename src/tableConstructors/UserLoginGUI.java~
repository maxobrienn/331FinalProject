import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.Patient;
import tableConstructors.InsuranceCompany;

public class UserLoginGUI extends JFrame implements ActionListener {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Object user; // This will hold either Patient or InsuranceCompany

    public UserLoginGUI(Object user) {
        this.user = user; // Assign the passed user object

        setTitle(user instanceof Patient ? "Patient Login" : "Insurance Company Login");
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
    }

    public void actionPerformed(ActionEvent e) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        boolean loginSuccess = false;
        if (user instanceof Patient) {
            loginSuccess = ((Patient) user).patientLogin(email, password);
        } else if (user instanceof InsuranceCompany) {
            loginSuccess = ((InsuranceCompany) user).insuranceCompanyLogin(email, password);
        }

        if (loginSuccess) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            // Open the appropriate main interface window here
        } else {
            JOptionPane.showMessageDialog(this, "Login failed. Please check your email and password.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create instances of Patient and InsuranceCompany to pass to UserLoginGUI
            Patient patient = new Patient();
            InsuranceCompany insuranceCompany = new InsuranceCompany();

            // Create the appropriate login GUI for each user type
            UserLoginGUI patientLoginGUI = new UserLoginGUI(patient);
            UserLoginGUI insuranceLoginGUI = new UserLoginGUI(insuranceCompany);

            // Make the login GUIs visible
            patientLoginGUI.setVisible(true);
            insuranceLoginGUI.setVisible(true);
        });
    }
}

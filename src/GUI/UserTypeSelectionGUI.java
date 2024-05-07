/**
 * Authors: Matt DeRosa, Max O’Brien, Ellie Smith, Mason Meyer, Evan Quinn
 * 
 * This class represents a graphical user interface (GUI) for selecting user types.
 * This class should be ran to interact with our database.
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

public class UserTypeSelectionGUI extends JFrame implements ActionListener {
    private JButton patientButton;
    private JButton insuranceCompanyButton;
    private JButton doctorButton;
    private JButton pharmacyButton;
    private JButton pharmacyEmployeeButton;
    private JButton supplierButton;

    /**
     * Constructs a UserTypeSelectionGUI object.
     */
    public UserTypeSelectionGUI() {
        setTitle("User Type Selection");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // 3 rows, 2 columns, with 5px horizontal and vertical gap

        // Create buttons
        patientButton = createButton("Patient");
        doctorButton = createButton("Doctor");
        insuranceCompanyButton = createButton("Insurance Company");
        pharmacyButton = createButton("Pharmacy");
        pharmacyEmployeeButton = createButton("Pharmacy Employee");
        supplierButton = createButton("Supplier");

        // Add buttons to panel
        panel.add(patientButton);
        panel.add(doctorButton);
        panel.add(insuranceCompanyButton);
        panel.add(pharmacyButton);
        panel.add(pharmacyEmployeeButton);
        panel.add(supplierButton);

        add(panel);
    }

    // Method to create a JButton with specified text and ActionListener
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.addActionListener(this);
        return button;
    }

    /**
     * Performs actions when buttons are clicked.
     * @param e The ActionEvent object.
     */
    public void actionPerformed(ActionEvent e) {
        String userType = "";
        if (e.getSource() == patientButton) {
            userType = "Patient";
        } else if (e.getSource() == doctorButton) {
            userType = "Doctor";
        } else if (e.getSource() == insuranceCompanyButton) {
            userType = "Insurance Company";
        } else if (e.getSource() == pharmacyButton) {
            userType = "Pharmacy";
        } else if (e.getSource() == pharmacyEmployeeButton) {
            userType = "Pharmacy Employee";
        } else if (e.getSource() == supplierButton) {
            userType = "Supplier";
        }

        if (!userType.isEmpty()) {
            openLoginGUI(userType);
            setVisible(false); // Hide the user type selection window
        }
    }

    /**
     * Opens the login GUI based on the selected user type.
     * @param userType The type of user selected.
     */
    private void openLoginGUI(String userType) {
        Object user = null;
        switch (userType) {
            case "Patient":
                user = new Patient();
                break;
            case "Doctor":
                user = new Doctor();
                break;
            case "Insurance Company":
                user = new InsuranceCompany();
                break;
            case "Pharmacy":
                user = new Pharmacy();
                break;
            case "Pharmacy Employee":
                user = new PharmacyEmployee();
                break;
            case "Supplier":
                user = new Supplier();
                break;
            default:
                break;
        }

        if (user != null) {
            UserLoginGUI loginGUI = new UserLoginGUI(user);
            loginGUI.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserTypeSelectionGUI userTypeSelectionGUI = new UserTypeSelectionGUI();
            userTypeSelectionGUI.setVisible(true);
        });
    }
}

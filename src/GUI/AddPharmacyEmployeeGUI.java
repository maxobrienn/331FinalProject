package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tableConstructors.*;

// Author: Matt
public class AddPharmacyEmployeeGUI extends JFrame {

    private JTextField employeeIdField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField ssnField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JTextField positionField;
    private JTextField pharmacyIdField;
    private JTextField passwordField;

    public AddPharmacyEmployeeGUI() {
        setTitle("Add New Pharmacy Employee"); // Setting the title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define what happens when the window is closed

        // Create the main panel
        JPanel panel = new JPanel(new GridBagLayout()); // Create a new panel with a GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints(); // GridBagConstraints for layout management
        gbc.anchor = GridBagConstraints.WEST; // Set the anchor to the west
        gbc.insets = new Insets(10, 10, 10, 10); // Set margins between components

        // Last Name
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Last Name:"), gbc); // Add label for last name
        lastNameField = new JTextField(20); // Create text field for last name
        gbc.gridx = 1;
        panel.add(lastNameField, gbc); // Add text field for last name

        // First Name
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("First Name:"), gbc); // Add label for first name
        firstNameField = new JTextField(20); // Create text field for first name
        gbc.gridx = 1;
        panel.add(firstNameField, gbc); // Add text field for first name

        // SSN
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("SSN:"), gbc); // Add label for SSN
        ssnField = new JTextField(20); // Create text field for SSN
        gbc.gridx = 1;
        panel.add(ssnField, gbc); // Add text field for SSN

        // Phone Number
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Phone Number:"), gbc); // Add label for phone number
        phoneNumberField = new JTextField(20); // Create text field for phone number
        gbc.gridx = 1;
        panel.add(phoneNumberField, gbc); // Add text field for phone number

        // Email
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Email:"), gbc); // Add label for email
        emailField = new JTextField(20); // Create text field for email
        gbc.gridx = 1;
        panel.add(emailField, gbc); // Add text field for email

        // Position
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Position:"), gbc); // Add label for position
        positionField = new JTextField(20); // Create text field for position
        gbc.gridx = 1;
        panel.add(positionField, gbc); // Add text field for position

        // Pharmacy ID
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Pharmacy ID:"), gbc); // Add label for pharmacy ID
        pharmacyIdField = new JTextField(20); // Create text field for pharmacy ID
        gbc.gridx = 1;
        panel.add(pharmacyIdField, gbc); // Add text field for pharmacy ID

        // Password
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc); // Add label for password
        passwordField = new JTextField(20); // Create text field for password
        gbc.gridx = 1;
        panel.add(passwordField, gbc); // Add text field for password

        // Add Button
        JButton addButton = new JButton("Add Pharmacy Employee"); // Create add button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc); // Add button to panel

        // Action Listener for Add Button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPharmacyEmployee(); // Call method to add pharmacy employee
            }
        });

        // Add panel to frame
        add(panel); // Add panel to JFrame
        pack(); // Pack the components of the window
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Make JFrame visible
    }

    // Method to add a new pharmacy employee
    private void addPharmacyEmployee() {
        PharmacyEmployee pharmacyEmployee = new PharmacyEmployee(); // Create new PharmacyEmployee object
        pharmacyEmployee.setLastName(lastNameField.getText()); // Set last name
        pharmacyEmployee.setFirstName(firstNameField.getText()); // Set first name
        pharmacyEmployee.setSsn(ssnField.getText()); // Set SSN
        pharmacyEmployee.setPhoneNumber(phoneNumberField.getText()); // Set phone number
        pharmacyEmployee.setEmail(emailField.getText()); // Set email
        pharmacyEmployee.setPosition(positionField.getText()); // Set position
        pharmacyEmployee.setPharmacyId(pharmacyIdField.getText()); // Set pharmacy ID
        pharmacyEmployee.setPassword(passwordField.getText()); // Set password

        pharmacyEmployee.addPharmacyEmployee(pharmacyEmployee); // Call method to add pharmacy employee
    }

    // Main method to start the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddPharmacyEmployeeGUI());
    }
}

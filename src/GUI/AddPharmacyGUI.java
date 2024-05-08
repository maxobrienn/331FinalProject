package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tableConstructors.*;

// Author: Matt
public class AddPharmacyGUI extends JFrame {

    private JTextField pharmacyNameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipCodeField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JTextField passwordField;

    public AddPharmacyGUI() {
        setTitle("Add New Pharmacy"); // Setting the title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define what happens when the window is closed

        // Create the main panel
        JPanel panel = new JPanel(new GridBagLayout()); // Create a new panel with a GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints(); // GridBagConstraints for layout management
        gbc.anchor = GridBagConstraints.WEST; // Set the anchor to the west
        gbc.insets = new Insets(10, 10, 10, 10); // Set margins between components

        // Pharmacy Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Pharmacy Name:"), gbc); // Add label for pharmacy name
        pharmacyNameField = new JTextField(20); // Create text field for pharmacy name
        gbc.gridx = 1;
        panel.add(pharmacyNameField, gbc); // Add text field for pharmacy name

        // Street
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Street:"), gbc); // Add label for street
        streetField = new JTextField(20); // Create text field for street
        gbc.gridx = 1;
        panel.add(streetField, gbc); // Add text field for street

        // City
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("City:"), gbc); // Add label for city
        cityField = new JTextField(20); // Create text field for city
        gbc.gridx = 1;
        panel.add(cityField, gbc); // Add text field for city

        // State
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("State:"), gbc); // Add label for state
        stateField = new JTextField(20); // Create text field for state
        gbc.gridx = 1;
        panel.add(stateField, gbc); // Add text field for state

        // Zip Code
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("ZIP Code:"), gbc); // Add label for zip code
        zipCodeField = new JTextField(20); // Create text field for zip code
        gbc.gridx = 1;
        panel.add(zipCodeField, gbc); // Add text field for zip code

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

        // Password
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc); // Add label for password
        passwordField = new JTextField(20); // Create text field for password
        gbc.gridx = 1;
        panel.add(passwordField, gbc); // Add text field for password

        // Add Button
        JButton addButton = new JButton("Add Pharmacy"); // Create add button
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc); // Add button to panel

        // Action Listener for Add Button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPharmacy(); // Call method to add pharmacy
            }
        });

        // Add panel to frame
        add(panel); // Add panel to JFrame
        pack(); // Pack the components of the window
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true); // Make JFrame visible
    }

    // Method to add a new pharmacy
    private void addPharmacy() {
        Pharmacy pharmacy = new Pharmacy(); // Create new Pharmacy object
        pharmacy.setPharmacyName(pharmacyNameField.getText()); // Set pharmacy name
        pharmacy.setStreet(streetField.getText()); // Set street
        pharmacy.setCity(cityField.getText()); // Set city
        pharmacy.setState(stateField.getText()); // Set state
        pharmacy.setZipCode(zipCodeField.getText()); // Set zip code
        pharmacy.setPhoneNumber(phoneNumberField.getText()); // Set phone number
        pharmacy.setEmail(emailField.getText()); // Set email
        pharmacy.setPassword(passwordField.getText()); // Set password

        pharmacy.addPharmacy(pharmacy); // Call method to add pharmacy
    }

    // Main method to start the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddPharmacyGUI());
    }
}

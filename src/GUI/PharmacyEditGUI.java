/**
 * Authors: Matt DeRosa, Max O’Brien, Ellie Smith, Mason Meyer, Evan Quinn
 * 
 * This class represents a graphical user interface (GUI) for editing pharmacy information.
 * It allows editing of various fields such as pharmacy name, address, and contact details.
 * The GUI also provides a button to save changes and return to the pharmacy menu.
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tableConstructors.*;

public class PharmacyEditGUI extends JFrame {

    private Pharmacy pharmacy;
    private JTextField pharmacyIdField;
    private JTextField pharmacyNameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipCodeField;
    private JTextField phoneNumberField;
    private JTextField passwordField;
    private JTextField emailField;

    /**
     * Constructs a PharmacyEditGUI object.
     * @param pharmacy The pharmacy whose information is being edited.
     */
    public PharmacyEditGUI(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;

        setTitle("Update Pharmacy Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adjust window size to show all fields
        setSize(500, 450); // Increased height to accommodate the "Return to PharmacyMenu" button
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
        Color labelColor = Color.BLUE;

        addRow(panel, gbc, "Pharmacy ID:", pharmacy.getPharmacyId(), labelFont, labelColor);
        pharmacyNameField = addEditableRow(panel, gbc, "Pharmacy Name:", pharmacy.getPharmacyName(), labelFont, labelColor);
        streetField = addEditableRow(panel, gbc, "Street:", pharmacy.getStreet(), labelFont, labelColor);
        cityField = addEditableRow(panel, gbc, "City:", pharmacy.getCity(), labelFont, labelColor);
        stateField = addEditableRow(panel, gbc, "State:", pharmacy.getState(), labelFont, labelColor);
        zipCodeField = addEditableRow(panel, gbc, "ZIP Code:", pharmacy.getZipCode(), labelFont, labelColor);
        phoneNumberField = addEditableRow(panel, gbc, "Phone Number:", pharmacy.getPhoneNumber(), labelFont, labelColor);
        passwordField = addEditableRow(panel, gbc, "Password:", pharmacy.getPassword(), labelFont, labelColor);
        emailField = addEditableRow(panel, gbc, "Email:", pharmacy.getEmail(), labelFont, labelColor);

        JButton saveButton = new JButton("Save");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(saveButton, gbc);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });

        // Add "Return to PharmacyMenu" button
        JButton returnButton = new JButton("Return to PharmacyMenu");
        gbc.gridy++;
        panel.add(returnButton, gbc);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the action to return to PharmacyMenu
                dispose(); // Close the current GUI
                // Assuming you have a method to open the PharmacyMenu GUI
                openPharmacyMenu();
            }
        });

        add(panel);
        setVisible(true);
    }

    // Method to open the PharmacyMenu GUI
    private void openPharmacyMenu() {
        // Code to open the PharmacyMenu GUI
        // Replace this with your implementation to open the PharmacyMenu
    }

    private void addRow(JPanel panel, GridBagConstraints gbc, String labelText, String valueText, Font labelFont, Color labelColor) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label, gbc);

        JLabel value = new JLabel(valueText);
        value.setFont(labelFont);
        gbc.gridx = 1;
        panel.add(value, gbc);
    }

    private JTextField addEditableRow(JPanel panel, GridBagConstraints gbc, String labelText, String valueText, Font labelFont, Color labelColor) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);
        label.setForeground(labelColor);
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(label, gbc);

        JTextField textField = new JTextField(valueText, 15);
        textField.setFont(labelFont);
        gbc.gridx = 1;
        panel.add(textField, gbc);

        return textField;
    }

    private void saveChanges() {
        // Update pharmacy information based on text field values
        pharmacy.setPharmacyName(pharmacyNameField.getText());
        pharmacy.setStreet(streetField.getText());
        pharmacy.setCity(cityField.getText());
        pharmacy.setState(stateField.getText());
        pharmacy.setZipCode(zipCodeField.getText());
        pharmacy.setPhoneNumber(phoneNumberField.getText());
        pharmacy.setEmail(emailField.getText());

        // Call the method to update pharmacy information
        pharmacy.updatePharmacyInfo(
                pharmacyNameField.getText(),
                streetField.getText(),
                cityField.getText(),
                stateField.getText(),
                zipCodeField.getText(),
                phoneNumberField.getText(),
                emailField.getText()
        );

        // Display updated pharmacy information
        System.out.println("Updated Pharmacy Information:");
        System.out.println(pharmacy.toString());
    }

    public static void main(String[] args) {
        // Create a sample pharmacy object
        Pharmacy pharmacy = new Pharmacy("PH001", "ABC Pharmacy", "456 Maple St", "Anytown", "NY", "12345", "555-123-4567", "password123", "abc@example.com");

        // Set look and feel to Nimbus
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and display the GUI
        SwingUtilities.invokeLater(() -> new PharmacyEditGUI(pharmacy));
    }
}

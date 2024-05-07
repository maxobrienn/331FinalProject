package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

public class PharmacyEmployeeEditGUI extends JFrame {

    private PharmacyEmployee employee;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField positionField;

    public PharmacyEmployeeEditGUI(PharmacyEmployee employee) {
        this.employee = employee;

        setTitle("Update Pharmacy Employee Information");
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

        addRow(panel, gbc, "Employee ID:", employee.getEmployeeId(), labelFont, labelColor);
        phoneNumberField = addEditableRow(panel, gbc, "Phone Number:", employee.getPhoneNumber(), labelFont, labelColor);
        emailField = addEditableRow(panel, gbc, "Email:", employee.getEmail(), labelFont, labelColor);
        lastNameField = addEditableRow(panel, gbc, "Last Name:", employee.getLastName(), labelFont, labelColor);
        firstNameField = addEditableRow(panel, gbc, "First Name:", employee.getFirstName(), labelFont, labelColor);
        positionField = addEditableRow(panel, gbc, "Position:", employee.getPosition(), labelFont, labelColor);

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
        // Update employee information based on text field values
        employee.setPhoneNumber(phoneNumberField.getText());
        employee.setEmail(emailField.getText());
        employee.setLastName(lastNameField.getText());
        employee.setFirstName(firstNameField.getText());
        employee.setPosition(positionField.getText());

        // Call the method to update employee information
        employee.updateEmployeeInfo(
                employee.getPhoneNumber(),
                employee.getEmail(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getPosition()

        );

        // Display updated employee information
        System.out.println("Updated Pharmacy Employee Information:");
        System.out.println(employee.toString());
    }

    public static void main(String[] args) {
        // Create a sample employee object
        PharmacyEmployee employee = new PharmacyEmployee("EMP001", "Doe", "John", "123-45-6789", "555-123-4567", "john.doe@example.com", "Pharmacist", "PH001", "password123");

        // Set look and feel to Nimbus
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create and display the GUI
        SwingUtilities.invokeLater(() -> new PharmacyEmployeeEditGUI(employee));
    }
}

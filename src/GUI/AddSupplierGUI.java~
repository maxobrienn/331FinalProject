package tableConstructors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import tableConstructors.*;

public class AddSupplierGUI extends JFrame {

    private JTextField supplierNameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipCodeField;
    private JTextField phoneNumberField;
    private JTextField emailField;
    private JTextField passwordField;

    public AddSupplierGUI() {
        setTitle("Add New Supplier");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the main panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Supplier Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Supplier Name:"), gbc);
        supplierNameField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(supplierNameField, gbc);

        // Street
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Street:"), gbc);
        streetField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(streetField, gbc);

        // City
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("City:"), gbc);
        cityField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(cityField, gbc);

        // State
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("State:"), gbc);
        stateField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(stateField, gbc);

        // Zip Code
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("ZIP Code:"), gbc);
        zipCodeField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(zipCodeField, gbc);

        // Phone Number
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Phone Number:"), gbc);
        phoneNumberField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(phoneNumberField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Password:"), gbc);
        passwordField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Add Button
        JButton addButton = new JButton("Add Supplier");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(addButton, gbc);

        // Action Listener for Add Button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSupplier();
            }
        });

        // Add panel to frame
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to add a new supplier
    private void addSupplier() {
        Supplier supplier = new Supplier();
                supplier.setSupplierName(supplierNameField.getText());
                supplier.setStreet(streetField.getText());
                supplier.setCity(cityField.getText());
                supplier.setState(stateField.getText());
                supplier.setZipCode(zipCodeField.getText());
                supplier.setPhoneNumber(phoneNumberField.getText());
                supplier.setEmail(emailField.getText());
                supplier.setPassword(passwordField.getText());
                
                supplier.addSupplier(supplier);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddSupplierGUI());
    }
}

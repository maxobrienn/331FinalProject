package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.Supplier;
import tableConstructors.*;

public class SupplierEditGUI extends JFrame {

    private Supplier supplier;

    private JTextField nameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField phoneField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public SupplierEditGUI(Supplier supplier) {
        this.supplier = supplier;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Update Supplier Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300); // Adjusted size for supplier information
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel(new GridLayout(0, 2));

        // Add labels and text fields for updating supplier information
        panel.add(new JLabel("Name:"));
        nameField = new JTextField(supplier.getSupplierName());
        panel.add(nameField);

        panel.add(new JLabel("Street:"));
        streetField = new JTextField(supplier.getStreet());
        panel.add(streetField);

        panel.add(new JLabel("City:"));
        cityField = new JTextField(supplier.getCity());
        panel.add(cityField);

        panel.add(new JLabel("State:"));
        stateField = new JTextField(supplier.getState());
        panel.add(stateField);

        panel.add(new JLabel("ZIP Code:"));
        zipField = new JTextField(supplier.getZipCode());
        panel.add(zipField);

        panel.add(new JLabel("Phone Number:"));
        phoneField = new JTextField(supplier.getPhoneNumber());
        panel.add(phoneField);

        panel.add(new JLabel("Email:"));
        emailField = new JTextField(supplier.getEmail());
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(supplier.getPassword());
        panel.add(passwordField);

        // Add a button to update the supplier information
        JButton updateButton = new JButton("Update Information");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateSupplierInformation();
            }
        });
        panel.add(updateButton);

        // Add a button to return to the main menu
        JButton returnButton = new JButton("Return to MainMenu");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnToMainMenu();
            }
        });
        panel.add(returnButton);

        add(panel);
        setVisible(true);
    }

    private void updateSupplierInformation() {
        String name = nameField.getText();
        String street = streetField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String zip = zipField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        // Update supplier information
        supplier.setSupplierName(name);
        supplier.setStreet(street);
        supplier.setCity(city);
        supplier.setState(state);
        supplier.setZipCode(zip);
        supplier.setPhoneNumber(phone);
        supplier.setEmail(email);
        supplier.setPassword(password);

        // Call method to update supplier information in the database
        supplier.editSupplierInfo(supplier.getSupplierId(), name, street, city, state, zip, phone, email);

        JOptionPane.showMessageDialog(this, "Supplier information updated successfully.");
    }

    private void returnToMainMenu() {
        // Close this window and return to the main menu
        dispose();
    }

    public static void main(String[] args) {
        // Create a sample supplier object
        Supplier supplier = new Supplier("SUP001", "ABC Supplier", "456 Supplier St", "SupplierCity", "SupplierState", "12345", "123-456-7890", "password", "supplier@email.com");

        // Create and display the GUI
        SwingUtilities.invokeLater(() -> new SupplierEditGUI(supplier));
    }
}

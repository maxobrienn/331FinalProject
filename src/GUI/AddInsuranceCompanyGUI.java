package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import tableConstructors.*;

// Authors: Matt
public class AddInsuranceCompanyGUI extends JFrame {
  
  private JTextField insuranceNameField;
  private JTextField streetField;
  private JTextField cityField;
  private JTextField stateField;
  private JTextField zipCodeField;
  private JTextField phoneNumberField;
  private JTextField emailField;
  private JPasswordField passwordField;
  private JTextField percentField;
  
  public AddInsuranceCompanyGUI() {
    setTitle("Add New Insurance Company"); // Setting the title of the window
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define what happens when the window is closed
    setSize(400, 500); // Set the size of the window
    setLocationRelativeTo(null); // Center the window on the screen
    
    JPanel panel = new JPanel(new GridBagLayout()); // Create a new panel with a GridBagLayout
    GridBagConstraints gbc = new GridBagConstraints(); // GridBagConstraints for layout management
    gbc.anchor = GridBagConstraints.WEST; // Set the anchor to the west
    
    // Set margins between components
    gbc.insets = new Insets(10, 10, 10, 10);
    
    // Add labels and text fields for insurance company information
    
    // Insurance Name
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(new JLabel("Insurance Name:"), gbc); // Add label for insurance name
    insuranceNameField = new JTextField(20); // Create text field for insurance name
    gbc.gridx = 1;
    panel.add(insuranceNameField, gbc); // Add text field for insurance name
    
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
    
    // ZIP Code
    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("ZIP Code:"), gbc); // Add label for ZIP code
    zipCodeField = new JTextField(20); // Create text field for ZIP code
    gbc.gridx = 1;
    panel.add(zipCodeField, gbc); // Add text field for ZIP code
    
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
    passwordField = new JPasswordField(20); // Create password field
    gbc.gridx = 1;
    panel.add(passwordField, gbc); // Add password field
    
    // Percentage
    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Percentage:"), gbc); // Add label for percentage
    percentField = new JTextField(20); // Create text field for percentage
    gbc.gridx = 1;
    panel.add(percentField, gbc); // Add text field for percentage
    
    // Add a button to add the insurance company
    JButton addButton = new JButton("Add Insurance Company");
    gbc.gridx = 0;
    gbc.gridy++;
    gbc.gridwidth = 2;
    panel.add(addButton, gbc); // Add button to panel
    
    // ActionListener for the add button
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          addNewInsuranceCompany(); // Call method to add new insurance company
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(AddInsuranceCompanyGUI.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    
    add(panel); // Add panel to JFrame
    setVisible(true); // Make JFrame visible
  }
  
  // Method to add a new insurance company
  private void addNewInsuranceCompany() throws SQLException {
    // Get data from text fields
    String insuranceName = insuranceNameField.getText();
    String street = streetField.getText();
    String city = cityField.getText();
    String state = stateField.getText();
    String zipCode = zipCodeField.getText();
    String phoneNumber = phoneNumberField.getText();
    String email = emailField.getText();
    String password = new String(passwordField.getPassword());
    BigDecimal percent = new BigDecimal(percentField.getText());
    
    // Create a new InsuranceCompany object
    InsuranceCompany newInsuranceCompany = new InsuranceCompany();
    newInsuranceCompany.setInsuranceName(insuranceName);
    newInsuranceCompany.setStreet(street);
    newInsuranceCompany.setCity(city);
    newInsuranceCompany.setState(state);
    newInsuranceCompany.setZipCode(zipCode);
    newInsuranceCompany.setPhoneNumber(phoneNumber);
    newInsuranceCompany.setEmail(email);
    newInsuranceCompany.setPassword(password);
    newInsuranceCompany.setPercent(percent);
    
    // Call the addInsuranceCompany method directly from the InsuranceCompany class
    newInsuranceCompany.addInsuranceCompany(newInsuranceCompany);
    
    // Display a success message
    JOptionPane.showMessageDialog(this, "Insurance Company added successfully!");
  }
  
  // Main method to start the GUI
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new AddInsuranceCompanyGUI());
  }
}

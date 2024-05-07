package tableConstructors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import tableConstructors.*;

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
    setTitle("Add New Insurance Company");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 500);
    setLocationRelativeTo(null);
    
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 10, 10, 10);
    
    // Add labels and text fields for insurance company information
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(new JLabel("Insurance Name:"), gbc);
    insuranceNameField = new JTextField(20);
    gbc.gridx = 1;
    panel.add(insuranceNameField, gbc);
    
    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Street:"), gbc);
    streetField = new JTextField(20);
    gbc.gridx = 1;
    panel.add(streetField, gbc);
    
    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("City:"), gbc);
    cityField = new JTextField(20);
    gbc.gridx = 1;
    panel.add(cityField, gbc);
    
    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("State:"), gbc);
    stateField = new JTextField(20);
    gbc.gridx = 1;
    panel.add(stateField, gbc);
    
    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("ZIP Code:"), gbc);
    zipCodeField = new JTextField(20);
    gbc.gridx = 1;
    panel.add(zipCodeField, gbc);
    
    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Phone Number:"), gbc);
    phoneNumberField = new JTextField(20);
    gbc.gridx = 1;
    panel.add(phoneNumberField, gbc);
    
    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Email:"), gbc);
    emailField = new JTextField(20);
    gbc.gridx = 1;
    panel.add(emailField, gbc);
    
    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Password:"), gbc);
    passwordField = new JPasswordField(20);
    gbc.gridx = 1;
    panel.add(passwordField, gbc);
    
    gbc.gridx = 0;
    gbc.gridy++;
    panel.add(new JLabel("Percentage:"), gbc);
    percentField = new JTextField(20);
    gbc.gridx = 1;
    panel.add(percentField, gbc);
    
    // Add a button to add the insurance company
    JButton addButton = new JButton("Add Insurance Company");
    gbc.gridx = 0;
    gbc.gridy++;
    gbc.gridwidth = 2;
    panel.add(addButton, gbc);
    
    addButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          addNewInsuranceCompany();
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(AddInsuranceCompanyGUI.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    
    add(panel);
    setVisible(true);
  }
  
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
  
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new AddInsuranceCompanyGUI());
  }
}

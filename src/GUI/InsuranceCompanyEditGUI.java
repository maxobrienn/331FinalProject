/*
 * Authors: Matt, Max, Mason, Ellie, Evan
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import tableConstructors.*;

public class InsuranceCompanyEditGUI extends JFrame {
  
  private InsuranceCompany insuranceCompany;
  private JTextField phoneNumberField;
  private JTextField emailField;
  private JTextField streetField;
  private JTextField cityField;
  private JTextField stateField;
  private JTextField zipCodeField;
  private JTextField insuranceNameField;
  private JTextField percentField;
  
  public InsuranceCompanyEditGUI(InsuranceCompany insuranceCompany) {
    this.insuranceCompany = insuranceCompany;
    
    setTitle("Update Insurance Company Information");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Adjust window size to show all fields
    setSize(500, 450);
    setLocationRelativeTo(null); // Center the window
    
    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 10, 10, 10);
    
    Font labelFont = new Font(Font.SANS_SERIF, Font.BOLD, 14);
    Color labelColor = Color.BLUE;
    
    addRow(panel, gbc, "Insurance ID:", insuranceCompany.getInsuranceId(), labelFont, labelColor);
    phoneNumberField = addEditableRow(panel, gbc, "Phone Number:", insuranceCompany.getPhoneNumber(), labelFont, labelColor);
    emailField = addEditableRow(panel, gbc, "Email:", insuranceCompany.getEmail(), labelFont, labelColor);
    streetField = addEditableRow(panel, gbc, "Street:", insuranceCompany.getStreet(), labelFont, labelColor);
    cityField = addEditableRow(panel, gbc, "City:", insuranceCompany.getCity(), labelFont, labelColor);
    stateField = addEditableRow(panel, gbc, "State:", insuranceCompany.getState(), labelFont, labelColor);
    zipCodeField = addEditableRow(panel, gbc, "ZIP Code:", insuranceCompany.getZipCode(), labelFont, labelColor);
    insuranceNameField = addEditableRow(panel, gbc, "Insurance Name:", insuranceCompany.getInsuranceName(), labelFont, labelColor);
    percentField = addEditableRow(panel, gbc, "Percent:", insuranceCompany.getPercent().toString(), labelFont, labelColor);
    
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
    
    JButton returnButton = new JButton("Return to InsuranceMenu");
    gbc.gridy++;
    panel.add(returnButton, gbc);
    
    returnButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
        openInsuranceMenu();
      }
    });
    
    add(panel);
    setVisible(true);
  }
  
  private void openInsuranceMenu() {
    // Code to open the InsuranceMenu GUI
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
    insuranceCompany.setPhoneNumber(phoneNumberField.getText());
    insuranceCompany.setEmail(emailField.getText());
    insuranceCompany.setStreet(streetField.getText());
    insuranceCompany.setCity(cityField.getText());
    insuranceCompany.setState(stateField.getText());
    insuranceCompany.setZipCode(zipCodeField.getText());
    insuranceCompany.setInsuranceName(insuranceNameField.getText());
 
    BigDecimal percent = new BigDecimal(percentField.getText());
    insuranceCompany.setPercent(percent);
    
    insuranceCompany.updateInsuranceCompanyInfo(phoneNumberField.getText(), emailField.getText(), 
                                                streetField.getText(), cityField.getText(), 
                                                stateField.getText(),
                                                zipCodeField.getText(), 
                                                insuranceNameField.getText(), percent);
   
  }
  
  public static void main(String[] args) {
    // Create a sample insurance company object
    InsuranceCompany testInsuranceCompany = new InsuranceCompany(
            "INS001", // Insurance ID
            "Insurance Name", // Insurance Name
            "1234 Insurance St", // Street
            "Insurance City", // City
            "NY", // State
            "12345", // ZIP Code
            "123-456-7890", // Phone Number
            "insurance@email.com", // Email
            "password", // Password
            new BigDecimal("15.5") // Percent
        );
    
    // Set look and feel to Nimbus
    try {
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    // Create and display the GUI
    SwingUtilities.invokeLater(() -> new InsuranceCompanyEditGUI(testInsuranceCompany));
  }
}

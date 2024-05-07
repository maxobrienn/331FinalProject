package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import tableConstructors.*;

public class GetUnpaidBalanceForInsuranceCompanyGUI extends JFrame {
  private JTextField insuranceIdField;
  private JButton getBalanceButton;
  private JLabel balanceLabel;
  private JButton returnToMenuButton; // New button for returning to the menu
  private PharmacyEmployee pharmacyEmployee;
  
  public GetUnpaidBalanceForInsuranceCompanyGUI(PharmacyEmployee pharmacyEmployee) {
    this.pharmacyEmployee = pharmacyEmployee;
    setTitle("Get Unpaid Balance for Insurance Company");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300, 150);
    setLocationRelativeTo(null); // Center the window on the screen
    
    // Initialize components
    insuranceIdField = new JTextField(10);
    getBalanceButton = new JButton("Get Balance");
    balanceLabel = new JLabel();
    returnToMenuButton = new JButton("Return to Menu"); // Initialize the button
    
    // Create panel to hold components
    JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5)); // Adjust grid layout for new button
    panel.add(new JLabel("Insurance ID:"));
    panel.add(insuranceIdField);
    panel.add(new JLabel()); // Empty label for spacing
    panel.add(getBalanceButton);
    panel.add(new JLabel()); // Empty label for spacing
    panel.add(returnToMenuButton); // Add the return to menu button
    
    // Add panel and balance label to the frame
    add(panel, BorderLayout.CENTER);
    add(balanceLabel, BorderLayout.SOUTH);
    
    // Attach event listeners
    getBalanceButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        getUnpaidBalance();
      }
    });
    
    // Attach event listener to the return to menu button
    returnToMenuButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        returnToMenu();
      }
    });
  }
  
  private void getUnpaidBalance() {
    String insuranceId = insuranceIdField.getText().trim();
    double unpaidBalance = pharmacyEmployee.GetUnpaidBalanceForInsuranceCompany(insuranceId);
    if (unpaidBalance >= 0) {
      balanceLabel.setText("Unpaid Balance: $" + unpaidBalance);
    } else {
      balanceLabel.setText("Failed to retrieve balance.");
    }
  }
  
  // Method to handle returning to the PharmacyEmployeeMenu
  private void returnToMenu() {
    // Close the current window
    dispose();
  }
  
  public static void main(String[] args) {
    PharmacyEmployee pharmacyEmployee = new PharmacyEmployee();
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        GetUnpaidBalanceForInsuranceCompanyGUI gui = new GetUnpaidBalanceForInsuranceCompanyGUI(pharmacyEmployee);
        gui.setVisible(true);
      }
    });
  }
}

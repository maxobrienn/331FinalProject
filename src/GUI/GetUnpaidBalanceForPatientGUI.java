package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import tableConstructors.*;

//Author Max
public class GetUnpaidBalanceForPatientGUI extends JFrame {
  private JTextField patientIdField;
  private JButton getBalanceButton;
  private JLabel balanceLabel;
  private JButton returnToMenuButton; // New button for returning to the menu
  private PharmacyEmployee pharmacyEmployee;
  
  public GetUnpaidBalanceForPatientGUI(PharmacyEmployee pharmacyEmployee) {
    this.pharmacyEmployee = pharmacyEmployee;
    setTitle("Get Unpaid Balance for Patient");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(300, 150);
    setLocationRelativeTo(null); // Center the window on the screen
    
    // Initialize components
    patientIdField = new JTextField(10);
    getBalanceButton = new JButton("Get Balance");
    balanceLabel = new JLabel();
    returnToMenuButton = new JButton("Return to Menu"); // Initialize the button
    
    // Create panel to hold components
    JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5)); // Adjust grid layout for new button
    panel.add(new JLabel("Patient ID:"));
    panel.add(patientIdField);
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
    String patientId = patientIdField.getText().trim();
    double unpaidBalance = pharmacyEmployee.GetUnpaidBalanceForPatient(patientId);
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
        GetUnpaidBalanceForPatientGUI gui = new GetUnpaidBalanceForPatientGUI(pharmacyEmployee);
        gui.setVisible(true);
      }
    });
  }
}

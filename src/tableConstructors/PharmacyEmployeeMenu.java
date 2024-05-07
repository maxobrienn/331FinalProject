package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

public class PharmacyEmployeeMenu extends JFrame {
  
    private PharmacyEmployee employee;
  
    public PharmacyEmployeeMenu(PharmacyEmployee employee) {
        this.employee = employee;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("PHARMACY EMPLOYEE: Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 300);
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel panel = new JPanel(new GridLayout(8, 1, 10, 10));

        // Buttons for different functionalities
        JButton viewProfileButton = new JButton("View Profile");
        JButton editProfileButton = new JButton("Edit Profile");
        JButton viewInventoryButton = new JButton("View Inventory");
        JButton viewPrescriptionsButton = new JButton("View All Prescriptions");
        JButton requestMedicationButton = new JButton("Request Medication From Supplier");
        JButton updateMedicationButton = new JButton("Update Medication Supply");
        JButton fillPrescriptionsButton = new JButton("Fill Prescription");
        JButton viewPatientUnpaidBalanceButton = new JButton("View Patient’s Total Unpaid Balance");
        JButton viewInsuranceUnpaidBalanceButton = new JButton("View Insurance Company’s Total Unpaid Balance");

        // Add action listeners to buttons
        viewProfileButton.addActionListener(e -> viewProfile());
        editProfileButton.addActionListener(e -> editProfile());
        viewInventoryButton.addActionListener(e -> viewInventory());
        viewPrescriptionsButton.addActionListener(e -> viewPrescriptions());
        requestMedicationButton.addActionListener(e -> requestMedication());
        updateMedicationButton.addActionListener(e -> updateMedication());
        fillPrescriptionsButton.addActionListener(e -> fillPrescriptions());
        viewPatientUnpaidBalanceButton.addActionListener(e -> viewPatientUnpaidBalance());
        viewInsuranceUnpaidBalanceButton.addActionListener(e -> viewInsuranceUnpaidBalance());

        // Add buttons to the panel
        panel.add(viewProfileButton);
        panel.add(editProfileButton);
        panel.add(viewInventoryButton);
        panel.add(viewPrescriptionsButton);
        panel.add(requestMedicationButton);
        panel.add(updateMedicationButton);
        panel.add(fillPrescriptionsButton);
        panel.add(viewPatientUnpaidBalanceButton);
        panel.add(viewInsuranceUnpaidBalanceButton);

        // Add panel to frame
        add(panel);
    }

        // Method to handle viewing profile
    private void viewProfile() {
      // Your logic for viewing profile
    }
    
    // Method to handle editing profile
    private void editProfile() {
      // Your logic for editing profile
    }
    
    // Method to handle viewing inventory
    private void viewInventory() {
      PharmacyViewMedicationGUI medicationInventoryGUI = new PharmacyViewMedicationGUI(employee);
      medicationInventoryGUI.setVisible(true);
    }
    
    // Method to handle viewing prescriptions and unpaid balances
    private void viewPrescriptions() {
      // Your logic for viewing prescriptions and unpaid balances
    }
    
    // Method to handle requesting medication from supplier
    private void requestMedication() {
      // Your logic for requesting medication from supplier
    }
    
    // Method to handle updating medication supply
    private void updateMedication() {
      // Your logic for updating medication supply
    }
    
    // Method to handle filling prescriptions
    private void fillPrescriptions() {
      // Your logic for filling prescriptions
    }
    
    // Method to handle viewing patient total unpaid balance
    private void viewPatientUnpaidBalance() {
      GetUnpaidBalanceForPatientGUI patientTotalBalanceGUI = new GetUnpaidBalanceForPatientGUI(employee);
      patientTotalBalanceGUI.setVisible(true);
    }
    
    // Method to handle viewing insurance company's total unpaid balance
    private void viewInsuranceUnpaidBalance() {
      GetUnpaidBalanceForInsuranceCompanyGUI insuranceTotalBalanceGUI = new GetUnpaidBalanceForInsuranceCompanyGUI(employee);
      insuranceTotalBalanceGUI.setVisible(true);
    }
    
    public static void main(String[] args) {
      // For testing purposes, create and display PharmacyEmployeeMenu
      PharmacyEmployee employee = new PharmacyEmployee();
      SwingUtilities.invokeLater(() -> new PharmacyEmployeeMenu(employee).setVisible(true));
    }
}

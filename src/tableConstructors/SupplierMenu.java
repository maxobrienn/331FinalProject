package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SupplierMenu extends JFrame {
  
  private Supplier supplier;
  
  public SupplierMenu(Supplier supplier) {
    this.supplier = supplier;
    initializeUI();
  }
  
  private void initializeUI() {
    setTitle("Supplier Menu");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 300);
    setLocationRelativeTo(null); // Center the window on the screen
    
    JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10)); // Adjusted grid layout to accommodate buttons
    
    // Buttons for viewing profile and updating info
    JButton viewProfileButton = new JButton("View Profile");
    JButton updateInfoButton = new JButton("Update Info");
    
    // Buttons for adding and removing medications
    JButton addMedicationButton = new JButton("Add Medication");
    JButton removeMedicationButton = new JButton("Remove Medication");
    
    // Add action listeners to buttons
    viewProfileButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        viewProfile();
      }
    });
    
    updateInfoButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        updateInfo();
      }
    });
    
    addMedicationButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        addMedication();
      }
    });
    
    removeMedicationButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        removeMedication();
      }
    });
    
    // Add buttons to panel
    panel.add(viewProfileButton);
    panel.add(updateInfoButton);
    panel.add(addMedicationButton);
    panel.add(removeMedicationButton);
    
    // Add panel to frame
    add(panel);
  }

    
    private void viewProfile() {
      SupplierInfoGUI viewGUI = new SupplierInfoGUI(supplier);
      viewGUI.setVisible(true);
    }
    
    private void updateInfo() {
      SupplierEditGUI editGUI = new SupplierEditGUI(supplier);
      editGUI.setVisible(true);
    }
    
    private void addMedication() {
      SupplierAddMedicationGUI addGUI = new SupplierAddMedicationGUI(supplier);
      addGUI.setVisible(true);
    }
    
    private void removeMedication() {
      SupplierRemoveMedicationGUI removeGUI = new SupplierRemoveMedicationGUI(supplier);
      removeGUI.setVisible(true);
    }
    
    public static void main(String[] args) {
        // For testing purposes, you can create and display the menu GUI like this:
        // Supplier supplier = new Supplier();
        // SwingUtilities.invokeLater(() -> new SupplierMenu(supplier).setVisible(true));
    }
}

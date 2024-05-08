package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

public class PharmacyMenu extends JFrame {
  private Pharmacy pharmacy;
  
  public PharmacyMenu(Pharmacy pharmacy) {
    this.pharmacy = pharmacy;
    initializeUI();
  }
  
  private void initializeUI() {
    setTitle("Pharmacy Menu");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 150);
    
    // Calculate center coordinates of the screen
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
    int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);
    
    // Set location of the window to the center of the screen
    setLocation(centerX, centerY);
    
    JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
    
    JButton viewProfileButton = new JButton("View Profile");
    JButton updateInfoButton = new JButton("Update Info");
    
    viewProfileButton.addActionListener(e -> displayProfile());
    updateInfoButton.addActionListener(e -> updateInfo());
    
    panel.add(viewProfileButton);
    panel.add(updateInfoButton);
    
    add(panel);
  }
  
  private void displayProfile() {
    SwingUtilities.invokeLater(() -> new PharmacyViewGUI(pharmacy));
  }
  
  private void updateInfo() {
    SwingUtilities.invokeLater(() -> new PharmacyEditGUI(pharmacy));
  }
  
  public static void main(String[] args) {
    // Example usage:
    // Pharmacy pharmacy = new Pharmacy();
    // new PharmacyMenu(pharmacy).setVisible(true);
  }
}

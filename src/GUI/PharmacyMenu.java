/**
 * Authors: Matt DeRosa, Max O’Brien, Ellie Smith, Mason Meyer, Evan Quinn
 * 
 * This class represents a graphical user interface (GUI) for pharmacy menus.
 * It provides options to view pharmacy profiles and update pharmacy information.
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

public class PharmacyMenu extends JFrame {
    private Pharmacy pharmacy;
    
    /**
     * Constructs a PharmacyMenu object.
     * @param pharmacy The pharmacy associated with this menu.
     */
    public PharmacyMenu(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
        initializeUI();
    }
    
    /**
     * Initializes the user interface.
     */
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
    
    /**
     * Displays the pharmacy profile GUI.
     */
    private void displayProfile() {
        SwingUtilities.invokeLater(() -> new PharmacyViewGUI(pharmacy));
    }
    
    /**
     * Displays the pharmacy edit GUI for updating information.
     */
    private void updateInfo() {
        SwingUtilities.invokeLater(() -> new PharmacyEditGUI(pharmacy));
    }
    
    /**
     * The entry point of the application.
     * Example usage:
     * // Pharmacy pharmacy = new Pharmacy();
     * // new PharmacyMenu(pharmacy).setVisible(true);
     */
    public static void main(String[] args) {
        // Example usage:
        // Pharmacy pharmacy = new Pharmacy();
        // new PharmacyMenu(pharmacy).setVisible(true);
    }
}

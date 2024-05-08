/**
 * Authors: Matt DeRosa, Max O’Brien, Ellie Smith, Mason Meyer, Evan Quinn
 * 
 * This class represents a graphical user interface (GUI) for viewing pharmacy information.
 * It displays various details about a pharmacy and provides an option to return to the pharmacy menu.
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.Pharmacy;

public class PharmacyViewGUI extends JFrame {

    private Pharmacy pharmacy;

    /**
     * Constructs a PharmacyViewGUI object.
     * @param pharmacy The pharmacy whose information is being displayed.
     */
    public PharmacyViewGUI(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
        initializeUI();
    }

    /**
     * Initializes the user interface.
     */
    private void initializeUI() {
        setTitle("Pharmacy Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350); // Increased height to accommodate the new button

        // Calculate center coordinates of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);

        // Set location of the window to the center of the screen
        setLocation(centerX, centerY);

        JPanel panel = new JPanel(new GridLayout(0, 2));

        // Add pharmacy information labels and values to the panel
        panel.add(new JLabel("Pharmacy ID:"));
        panel.add(new JLabel(pharmacy.getPharmacyId()));
        panel.add(new JLabel("Pharmacy Name:"));
        panel.add(new JLabel(pharmacy.getPharmacyName()));
        panel.add(new JLabel("Street:"));
        panel.add(new JLabel(pharmacy.getStreet()));
        panel.add(new JLabel("City:"));
        panel.add(new JLabel(pharmacy.getCity()));
        panel.add(new JLabel("State:"));
        panel.add(new JLabel(pharmacy.getState()));
        panel.add(new JLabel("ZIP Code:"));
        panel.add(new JLabel(pharmacy.getZipCode()));
        panel.add(new JLabel("Email:"));
        panel.add(new JLabel(pharmacy.getEmail()));
        panel.add(new JLabel("Phone Number:"));
        panel.add(new JLabel(pharmacy.getPhoneNumber()));

        // Add a button to return to the PharmacyMenu
        JButton returnButton = new JButton("Return to PharmacyMenu");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnToPharmacyMenu();
            }
        });
        panel.add(returnButton);

        add(panel);
        setVisible(true);
    }

    /**
     * Handles the action to return to the PharmacyMenu.
     */
    private void returnToPharmacyMenu() {
        // Close this window and return to the PharmacyMenu
        dispose();
    }

    /**
     * The entry point of the application.
     * Example usage:
     * // Create a sample pharmacy object
     * // Pharmacy pharmacy = new Pharmacy("PH001", "ABC Pharmacy", "456 Maple St", "Anytown", "NY", "12345", "555-123-4567", "password123", "abc@example.com");
     * // Create and display the GUI
     * // SwingUtilities.invokeLater(() -> new PharmacyViewGUI(pharmacy));
     */
    public static void main(String[] args) {
        // Create a sample pharmacy object
        Pharmacy pharmacy = new Pharmacy("PH001", "ABC Pharmacy", "456 Maple St", "Anytown", "NY", "12345", "555-123-4567", "password123", "abc@example.com");

        // Create and display the GUI
        SwingUtilities.invokeLater(() -> new PharmacyViewGUI(pharmacy));
    }
}

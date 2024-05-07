package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.Supplier;
import tableConstructors.*;

public class SupplierInfoGUI extends JFrame {

    private Supplier supplier;

    public SupplierInfoGUI(Supplier supplier) {
        this.supplier = supplier;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Supplier Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300); // Adjusted size for supplier information
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel(new GridLayout(0, 2));

        // Add supplier information labels and values to the panel
        panel.add(new JLabel("Supplier ID:"));
        panel.add(new JLabel(supplier.getSupplierId()));
        panel.add(new JLabel("Supplier Name:"));
        panel.add(new JLabel(supplier.getSupplierName()));
        panel.add(new JLabel("Street:"));
        panel.add(new JLabel(supplier.getStreet()));
        panel.add(new JLabel("City:"));
        panel.add(new JLabel(supplier.getCity()));
        panel.add(new JLabel("State:"));
        panel.add(new JLabel(supplier.getState()));
        panel.add(new JLabel("ZIP Code:"));
        panel.add(new JLabel(supplier.getZipCode()));
        panel.add(new JLabel("Email:"));
        panel.add(new JLabel(supplier.getEmail()));
        panel.add(new JLabel("Phone Number:"));
        panel.add(new JLabel(supplier.getPhoneNumber()));

        // Add a button to return to the MainMenu
        JButton returnButton = new JButton("Return to MainMenu");
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnToMainMenu();
            }
        });
        panel.add(returnButton);

        add(panel);
        setVisible(true);
    }

    private void returnToMainMenu() {
        // Close this window and return to the MainMenu
        dispose();
    }

    public static void main(String[] args) {
        // Create a sample supplier object
        Supplier supplier = new Supplier("SUP001", "ABC Supplier", "456 Supplier St", "SupplierCity", "SupplierState", "12345", "123-456-7890", "password", "supplier@email.com");

        // Create and display the GUI
        SwingUtilities.invokeLater(() -> new SupplierInfoGUI(supplier));
    }
}

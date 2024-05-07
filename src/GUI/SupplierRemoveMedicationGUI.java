package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

public class SupplierRemoveMedicationGUI extends JFrame {
    private Supplier supplier;

    public SupplierRemoveMedicationGUI(Supplier supplier) {
        this.supplier = supplier;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Remove Medication");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel nameLabel = new JLabel("Medication Name:");
        JTextField nameField = new JTextField();
        JButton removeButton = new JButton("Remove Medication");

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String medicationName = nameField.getText();
                boolean removed = supplier.removeMedication(medicationName);
                if (removed) {
                    JOptionPane.showMessageDialog(null, "Medication removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Medication not found.");
                }
                dispose();
            }
        });
        
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(removeButton);

        add(panel);
    }
}

package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

public class SupplierAddMedicationGUI extends JFrame {
    private Supplier supplier;

    public SupplierAddMedicationGUI(Supplier supplier) {
        this.supplier = supplier;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Add Medication");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        JLabel nameLabel = new JLabel("Medication Name:");
        JTextField nameField = new JTextField();
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();
        JButton addButton = new JButton("Add Medication");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String medicationName = nameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                supplier.addMedication(medicationName, quantity);
                JOptionPane.showMessageDialog(null, "Medication added successfully.");
                dispose();
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(addButton);

        add(panel);
    }
}

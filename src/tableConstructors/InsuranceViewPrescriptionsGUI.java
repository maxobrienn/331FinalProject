package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

public class InsuranceViewPrescriptionsGUI extends JFrame {
    private JButton returnToMenuButton;

    public InsuranceViewPrescriptionsGUI() {
        setTitle("Insurance View Prescriptions");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize components
        returnToMenuButton = new JButton("Return to Menu");

        // Add components to the layout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(returnToMenuButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Attach event listeners
        returnToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button click event
                returnToMenu();
            }
        });

        // Set the size of the window
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void returnToMenu() {
        // Close the current window
        dispose();
    }

    public static void main(String[] args) {
        // Create and display the GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InsuranceViewPrescriptionsGUI gui = new InsuranceViewPrescriptionsGUI();
                gui.setVisible(true);
            }
        });
    }
}

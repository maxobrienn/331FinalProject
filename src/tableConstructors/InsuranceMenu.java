package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

public class InsuranceMenu extends JFrame {

    public InsuranceMenu() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Insurance Company Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Calculate center coordinates of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);

        // Set location of the window to the center of the screen
        setLocation(centerX, centerY);

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));

        JButton makePaymentButton = new JButton("Make Payment");
        JButton viewCoveredPatientsButton = new JButton("View Covered Patients");

        makePaymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makePayment();
            }
        });

        viewCoveredPatientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewCoveredPatients();
            }
        });

        panel.add(makePaymentButton);
        panel.add(viewCoveredPatientsButton);

        add(panel);
    }

    private void makePayment() {
        // This is where you can implement the logic to make a payment
        // For demonstration purpose, let's display a message
        JOptionPane.showMessageDialog(this, "Make Payment functionality will be implemented here.");
    }

    private void viewCoveredPatients() {
        // This is where you can implement the logic to view covered patients
        // For demonstration purpose, let's display a message
        JOptionPane.showMessageDialog(this, "View Covered Patients functionality will be implemented here.");
    }

    public static void main(String[] args) {
        // Create and display the menu GUI
        SwingUtilities.invokeLater(() -> new InsuranceMenu());
    }
}

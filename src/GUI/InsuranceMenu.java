package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

/**
 * This class represents a graphical user interface for the Insurance Company menu.
 */
public class InsuranceMenu extends JFrame {

    private InsuranceCompany insurance;

    /**
     * Constructs a new InsuranceMenu with the given InsuranceCompany object.
     * @param insurance The InsuranceCompany object associated with this menu.
     */
    public InsuranceMenu(InsuranceCompany insurance) {
        this.insurance = insurance;
        initializeUI();
    }

    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        setTitle("Insurance Company Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10)); // Increased grid rows to accommodate new buttons

        // Buttons for making payment and viewing covered patients' prescriptions
        JButton makePaymentButton = new JButton("Make Payment");
        JButton viewCoveredPatientsButton = new JButton("View Covered Patients' Prescriptions");

        // Buttons for viewing profile and updating info
        JButton viewProfileButton = new JButton("View Profile");
        JButton updateInfoButton = new JButton("Update Info");

        // Add action listeners to buttons
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

        // Add buttons to panel
        panel.add(makePaymentButton);
        panel.add(viewCoveredPatientsButton);
        panel.add(viewProfileButton);
        panel.add(updateInfoButton);

        // Add panel to frame
        add(panel);
    }

    /**
     * Opens the InsurancePaymentGUI for making payment.
     */
    private void makePayment() {
        InsurancePaymentGUI paymentGUI = new InsurancePaymentGUI(insurance);
        paymentGUI.setVisible(true);
    }

    /**
     * Opens the InsuranceViewPrescriptionsGUI for viewing covered patients' prescriptions.
     */
    private void viewCoveredPatients() {
        InsuranceViewPrescriptionsGUI view = new InsuranceViewPrescriptionsGUI(insurance);
        view.setVisible(true);
    }

    /**
     * Opens the InsuranceCompanyViewInfoGUI for viewing insurance company profile.
     */
    private void viewProfile() {
        InsuranceCompanyViewInfoGUI view = new InsuranceCompanyViewInfoGUI(insurance);
        view.setVisible(true);
    }
    
    /**
     * Opens the InsuranceCompanyEditGUI for updating insurance company information.
     */
    private void updateInfo() {
        InsuranceCompanyEditGUI view = new InsuranceCompanyEditGUI(insurance);
        view.setVisible(true);
    }

    /**
     * Main method to create and display the InsuranceMenu.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // For testing purposes, create and display InsuranceMenu
        InsuranceCompany insurance = new InsuranceCompany();
        SwingUtilities.invokeLater(() -> new InsuranceMenu(insurance).setVisible(true));
    }
}

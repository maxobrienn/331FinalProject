package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;

public class InsuranceMenu extends JFrame {

    private InsuranceCompany insurance;

    public InsuranceMenu(InsuranceCompany insurance) {
        this.insurance = insurance;
        initializeUI();
    }

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

    private void makePayment() {
        // Create and display InsurancePaymentGUI
        InsurancePaymentGUI paymentGUI = new InsurancePaymentGUI(insurance);
        paymentGUI.setVisible(true);
    }

    private void viewCoveredPatients() {
        InsuranceViewPrescriptionsGUI view = new InsuranceViewPrescriptionsGUI(insurance);
        view.setVisible(true);
    }

    private void viewProfile() {
      InsuranceCompanyViewInfoGUI view = new InsuranceCompanyViewInfoGUI(insurance);
        view.setVisible(true);
    }
    
    private void updateInfo() {
      InsuranceCompanyEditGUI view = new InsuranceCompanyEditGUI(insurance);
        view.setVisible(true);
    }

    public static void main(String[] args) {
        // For testing purposes, create and display InsuranceMenu
        InsuranceCompany insurance = new InsuranceCompany();
        SwingUtilities.invokeLater(() -> new InsuranceMenu(insurance).setVisible(true));
    }
}

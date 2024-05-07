package tableConstructors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import tableConstructors.*;

public class InsuranceCompanyViewInfoGUI extends JFrame {

    private InsuranceCompany insuranceCompany;

    public InsuranceCompanyViewInfoGUI(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Insurance Company Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400); // Increased height to accommodate the new button

        // Calculate center coordinates of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);

        // Set location of the window to the center of the screen
        setLocation(centerX, centerY);

        JPanel panel = new JPanel(new GridLayout(0, 2));

        // Add insurance company information labels and values to the panel
        panel.add(new JLabel("Insurance ID:"));
        panel.add(new JLabel(insuranceCompany.getInsuranceId()));
        panel.add(new JLabel("Insurance Name:"));
        panel.add(new JLabel(insuranceCompany.getInsuranceName()));
        panel.add(new JLabel("Street:"));
        panel.add(new JLabel(insuranceCompany.getStreet()));
        panel.add(new JLabel("City:"));
        panel.add(new JLabel(insuranceCompany.getCity()));
        panel.add(new JLabel("State:"));
        panel.add(new JLabel(insuranceCompany.getState()));
        panel.add(new JLabel("ZIP Code:"));
        panel.add(new JLabel(insuranceCompany.getZipCode()));
        panel.add(new JLabel("Phone Number:"));
        panel.add(new JLabel(insuranceCompany.getPhoneNumber()));
        panel.add(new JLabel("Email:"));
        panel.add(new JLabel(insuranceCompany.getEmail()));
        panel.add(new JLabel("Percent:"));
        panel.add(new JLabel(insuranceCompany.getPercent().toString()));

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
        // Create a sample insurance company object
        InsuranceCompany insuranceCompany = new InsuranceCompany("INS001", "Health Insure Inc.",
                "1234 Insurance St", "Metropolis", "NY", "54321",
                "insurance@example.com", "123-456-7890", "password", new BigDecimal("15.5"));

        // Create and display the GUI
        SwingUtilities.invokeLater(() -> new InsuranceCompanyViewInfoGUI(insuranceCompany));
    }
}

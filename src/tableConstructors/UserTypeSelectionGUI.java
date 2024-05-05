import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.Patient;
import tableConstructors.InsuranceCompany;


public class UserTypeSelectionGUI extends JFrame implements ActionListener {
    private JButton patientButton;
    private JButton insuranceCompanyButton;
    private JButton doctorButton;
    private JButton pharmacyButton;
    private JButton pharmacyEmployeeButton;
    private JButton supplierButton;

    public UserTypeSelectionGUI() {
        setTitle("User Type Selection");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        patientButton = new JButton("Patient");
        patientButton.addActionListener(this);
        panel.add(patientButton);

        doctorButton = new JButton("Doctor");
        doctorButton.addActionListener(this);
        panel.add(doctorButton);
        
        insuranceCompanyButton = new JButton("Insurance Company");
        insuranceCompanyButton.addActionListener(this);
        panel.add(insuranceCompanyButton);
        
        pharmacyButton = new JButton("Pharmacy");
        pharmacyButton.addActionListener(this);
        panel.add(pharmacyButton);
        
        pharmacyEmployeeButton = new JButton("Pharmacy Employee");
        pharmacyEmployeeButton.addActionListener(this);
        panel.add(pharmacyEmployeeButton);
        
        supplierButton = new JButton("Supplier");
        supplierButton.addActionListener(this);
        panel.add(supplierButton);
        
        add(panel);
    }
    
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == patientButton) {
        openLoginGUI("Patient");
      } else if (e.getSource() == doctorButton) {
        openLoginGUI("Doctor");
      } else if (e.getSource() == insuranceCompanyButton) {
        openLoginGUI("Insurance Company");
      }
      else if (e.getSource() == pharmacyButton) {
        openLoginGUI("Pharmacy");
      }
      else if (e.getSource() == pharmacyEmployeeButton) {
        openLoginGUI("Pharmacy Employee");
      }
      else if (e.getSource() == supplierButton) {
        openLoginGUI("Supplier");
      }
      setVisible(false); // Hide the user type selection window
    }
    
    private void openLoginGUI(String userType) {
      if (userType.equals("Patient")) {
            Patient patient = new Patient();
            UserLoginGUI patientLoginGUI = new UserLoginGUI(patient);
            patientLoginGUI.setVisible(true);
        } else if (userType.equals("Insurance Company")) {
            InsuranceCompany insuranceCompany = new InsuranceCompany();
            UserLoginGUI insuranceLoginGUI = new UserLoginGUI(insuranceCompany);
            insuranceLoginGUI.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserTypeSelectionGUI userTypeSelectionGUI = new UserTypeSelectionGUI();
            userTypeSelectionGUI.setVisible(true);
        });
    }
}

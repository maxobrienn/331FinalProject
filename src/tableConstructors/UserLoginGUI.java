package tableConstructors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.*;
//import tableConstructors.Patient;
//import tableConstructors.InsuranceCompany;
//import tableConstructors.Doctor;
//import tableConstructors.Pharmacy;
//import tableConstructors.PharmacyEmployee;
//import tableConstructors.Supplier;

public class UserLoginGUI extends JFrame implements ActionListener {
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;
    private Object user;

    public UserLoginGUI(Object user) {
        this.user = user;

        String userType = "";
        if (user instanceof Patient) {
            userType = "Patient";
        } else if (user instanceof InsuranceCompany) {
            userType = "Insurance Company";
        } else if (user instanceof Doctor) {
            userType = "Doctor";
        } else if (user instanceof Pharmacy) {
            userType = "Pharmacy";
        } else if (user instanceof PharmacyEmployee) {
            userType = "Pharmacy Employee";
        } else if (user instanceof Supplier) {
            userType = "Supplier";
        }

        setTitle(userType + " Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel idLabel = new JLabel("ID:");
        panel.add(idLabel);

        idField = new JTextField();
        panel.add(idField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton);

        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });
        panel.add(createAccountButton);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        String id = idField.getText();
        String password = new String(passwordField.getPassword());

        boolean loginSuccess = false;
        if (user instanceof Patient) {
            loginSuccess = ((Patient) user).patientLogin(id, password);
        } else if (user instanceof Doctor) {
            loginSuccess = ((Doctor) user).doctorLogin(id, password);
        } else if (user instanceof InsuranceCompany) {
            loginSuccess = ((InsuranceCompany) user).insuranceCompanyLogin(id, password);
        } else if (user instanceof Pharmacy) {
            loginSuccess = ((Pharmacy) user).pharmacyLogin(id, password);
        } else if (user instanceof PharmacyEmployee) {
            loginSuccess = ((PharmacyEmployee) user).pharmacyEmployeeLogin(id, password);
        } else if (user instanceof Supplier) {
            loginSuccess = ((Supplier) user).supplierLogin(id, password);
        }

        if (loginSuccess) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            openMainMenu(user); // Redirect to the main menu
            this.dispose(); // Close the login window
        } else {
          JOptionPane.showMessageDialog(this, "Login failed. Please check your ID and password.");
        }
    }
    
    private void createAccount() {
        // Redirect patients to the AddPatientGUI for account creation
        if (user instanceof Patient) {
            AddPatientGUI addPatientGUI = new AddPatientGUI();
            addPatientGUI.setVisible(true);
            this.dispose(); // Close the login window
        } else {
            // For other user types, display a message indicating that account creation is not supported
            JOptionPane.showMessageDialog(this, "Account creation is not supported for this user type.");
        }
    }

    // Method to open the main menu based on the user type
    private void openMainMenu(Object user) {
      String id = idField.getText();
      if (user instanceof Patient) {
        PatientMenu patientMenu = new PatientMenu(((Patient) user).displayPatientInfo(id));
        patientMenu.setVisible(true);
      } 
      else if (user instanceof Doctor) {
        DoctorMenu doctorMenu = new DoctorMenu(((Doctor) user).displayDoctorInfo(id));
        doctorMenu.setVisible(true);
      } 
      // change this to take insurance company object similar to patient
      else if (user instanceof InsuranceCompany) {
        InsuranceMenu insuranceCompanyMenu = new InsuranceMenu();
        insuranceCompanyMenu.setVisible(true);
      } 
//      else if (user instanceof Pharmacy) {
//        PharmacyMenu pharmacyMenu = new PharmacyMenu();
//        pharmacyMenu.setVisible(true);
//      } 
//      else if (user instanceof PharmacyEmployee) {
//        PharmacyEmployeeMenu pharmacyEmployeeMenu = new PharmacyEmployeeMenu();
//        pharmacyEmployeeMenu.setVisible(true);
//      } 
//      else if (user instanceof Supplier) {
//        SupplierMenu supplierMenu = new SupplierMenu();
//        supplierMenu.setVisible(true);
//      }
        
    }

    public static void main(String[] args) {
        // For testing purposes, the main method can be used to create instances of UserLoginGUI
        SwingUtilities.invokeLater(() -> {
            // Example usage:
            // Patient patient = new Patient();
            // UserLoginGUI patientLoginGUI = new UserLoginGUI(patient);
            // patientLoginGUI.setVisible(true);
        });
    }
}

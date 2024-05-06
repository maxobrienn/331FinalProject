import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import tableConstructors.Patient;

public class PatientMenu extends JFrame {
  private Patient patient;
  
  public PatientMenu(Patient patient) {
    this.patient = patient;
    initializeUI();
  }
  
  private void initializeUI() {
    setTitle("Patient Menu");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 300);
    
    // Calculate center coordinates of the screen
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
    int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);
    
    // Set location of the window to the center of the screen
    setLocation(centerX, centerY);
    
    JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
    
    JButton viewProfileButton = new JButton("View Profile");
    JButton updateInfoButton = new JButton("Update Info");
    JButton viewPrescriptionButton = new JButton("View Prescriptions");
    JButton viewDiagnosesButton = new JButton("View Diagnoses");
    JButton viewDoctorsButton = new JButton("View Doctors");
    
    viewProfileButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        displayProfile();
      }
    });
    
    updateInfoButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        updateInfo();
            }
        });

        viewPrescriptionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPrescriptions();
            }
        });

        viewDiagnosesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewDiagnoses();
            }
        });

        viewDoctorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewDoctors();
            }
        });

        panel.add(viewProfileButton);
        panel.add(updateInfoButton);
        panel.add(viewPrescriptionButton);
        panel.add(viewDiagnosesButton);
        panel.add(viewDoctorsButton);
        
        add(panel);
    }
    
    private void displayProfile() {
      SwingUtilities.invokeLater(() -> new PatientInfoGUI(patient));
    }
    
    private void updateInfo() {
      // Open a dialog to update patient information
      // Example: new UpdateInfoDialog(patient).setVisible(true);
    }
    
    private void viewPrescriptions() {
      // Display patient prescription balances
      // Example: patient.viewPrescriptionBalances();
    }
    
    private void viewDiagnoses() {
      // Display patient diagnoses
      // Example: patient.viewDiagnoses();
    }

    private void viewDoctors() {
        // Display list of doctors
        // Example: patient.viewDoctorList();
    }

    public static void main(String[] args) {
        // Example usage:
        // Patient patient = new Patient();
        // new PatientMenu(patient).setVisible(true);
    }
}

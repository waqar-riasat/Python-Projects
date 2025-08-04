package embassysystemgui;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

// Applicant class to store information of each applicant
class Applicant {
    private String name;
    private String passportNumber;
    private String applicationStatus; 
    private boolean interviewScheduled;
    private String interviewDate;
    private String interviewTime;

    public Applicant(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.applicationStatus = "Pending";
        this.interviewScheduled = false;
        this.interviewDate = "";
        this.interviewTime = "";
    }

    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public boolean isInterviewScheduled() {
        return interviewScheduled;
    }

    public void scheduleInterview(String date, String time) {
        this.interviewScheduled = true;
        this.interviewDate = date;
        this.interviewTime = time;
        this.applicationStatus = "Interview Scheduled";
    }

    public void approve() {
        this.applicationStatus = "Approved";
    }

    public void reject() {
        this.applicationStatus = "Rejected";
    }

    public String getInterviewDetails() {
        return "Interview Date: " + interviewDate + ", Time: " + interviewTime;
    }
}

public class EmbassySystemGUI extends JFrame {
    private String registeredEmail;
    private String registeredPassword;
    private Map<String, Applicant> applicantMap;
    private List<Applicant> applicants;
    private Queue<Applicant> interviewQueue;
    private LinkedList<Applicant> waitingList;

    public EmbassySystemGUI() {
        applicantMap = new HashMap<>();
        applicants = new ArrayList<>();
        interviewQueue = new LinkedList<>();
        waitingList = new LinkedList<>();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("US Embassy System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Setting up custom JPanel for the background image
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for centering
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20); // Add padding

        // Create label and center it
        JLabel label = new JLabel("Welcome to the US Embassy System");
        label.setFont(new Font("Times new roman", Font.BOLD, 30));  
        label.setForeground(Color.BLACK);  
        backgroundPanel.add(label, gbc);

        // Create "Start" button, style it and center it
        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(160, 50));  // Increase size of button
        startButton.setFont(new Font("Arial", Font.BOLD, 18));  // Increase font size of button text
        startButton.setBackground(new Color(139, 69, 19));  // Brown background
        startButton.setForeground(Color.WHITE);  // White text
        startButton.addActionListener(e -> welcomeScreen());

        gbc.gridy = 1; // Move button down in the layout
        backgroundPanel.add(startButton, gbc);

        setContentPane(backgroundPanel);
    }

    public void welcomeScreen() {
        JFrame welcomeFrame = new JFrame("Welcome Screen");
        welcomeFrame.setSize(500, 400);
        welcomeFrame.setLocationRelativeTo(null);

        BackgroundPanel welcomeBackgroundPanel = new BackgroundPanel(); // Background for welcome screen
        welcomeBackgroundPanel.setLayout(new BoxLayout(welcomeBackgroundPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Welcome to the US Embassy System");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Times new roman", Font.BOLD, 25));  
        welcomeBackgroundPanel.add(label);

        JButton registerButton = new JButton("Register");
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));  // Increased font size
        registerButton.addActionListener(e -> {
            welcomeFrame.dispose();
            showRegistrationForm();
        });

        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));  // Increased font size
        loginButton.addActionListener(e -> {
            welcomeFrame.dispose();
            showLoginForm();
        });

        welcomeBackgroundPanel.add(registerButton);
        welcomeBackgroundPanel.add(Box.createVerticalStrut(10));
        welcomeBackgroundPanel.add(loginButton);

        welcomeFrame.setContentPane(welcomeBackgroundPanel);
        welcomeFrame.setVisible(true);
    }

    private void showRegistrationForm() {
        JFrame registrationFrame = new JFrame(" User Registration Form");
        registrationFrame.setSize(500, 450);
        registrationFrame.setLocationRelativeTo(null);

        BackgroundPanel registrationBackgroundPanel = new BackgroundPanel(); // Background for registration form
        registrationBackgroundPanel.setLayout(new GridLayout(5, 2));

        // Increased font size and bold for labels
        Font labelFont = new Font("Times new roman", Font.BOLD, 17);  // Increased font size and bold

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setFont(labelFont);
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email ID:");
        emailLabel.setFont(labelFont);
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        JPasswordField passwordField = new JPasswordField();

        JLabel passportLabel = new JLabel("Passport Number:");
        passportLabel.setFont(labelFont);
        JTextField passportField = new JTextField();

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String passport = passportField.getText();

            if (!name.isEmpty() && !email.isEmpty() && !passport.isEmpty()) {
                this.registeredEmail = email;
                this.registeredPassword = password;

                Applicant applicant = new Applicant(name, passport);
                applicants.add(applicant);
                applicantMap.put(passport, applicant);

                JOptionPane.showMessageDialog(registrationFrame, "Registration successful! Please log in.");
                registrationFrame.dispose();
                showLoginForm();
            } else {
                JOptionPane.showMessageDialog(registrationFrame, "Please fill all fields.");
            }
        });

        registrationBackgroundPanel.add(nameLabel);
        registrationBackgroundPanel.add(nameField);
        registrationBackgroundPanel.add(emailLabel);
        registrationBackgroundPanel.add(emailField);
        registrationBackgroundPanel.add(passwordLabel);
        registrationBackgroundPanel.add(passwordField);
        registrationBackgroundPanel.add(passportLabel);
        registrationBackgroundPanel.add(passportField);
        registrationBackgroundPanel.add(registerButton);

        registrationFrame.setContentPane(registrationBackgroundPanel);
        registrationFrame.setVisible(true);
    }

    private void showLoginForm() {
        JFrame loginFrame = new JFrame(" User Login Form");
        loginFrame.setSize(550, 400);
        loginFrame.setLocationRelativeTo(null);

        BackgroundPanel loginBackgroundPanel = new BackgroundPanel(); // Background for login form
        loginBackgroundPanel.setLayout(new GridLayout(3, 2));

        JPanel panel = new JPanel(new GridLayout(4, 2, 15, 15)); 

        JLabel emailLabel = new JLabel("Email ID:");
        emailLabel.setFont(new Font("Times new roman", Font.BOLD, 17));
        JTextField emailField = new JTextField();
        
      JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Times new roman", Font.BOLD, 17));
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (email.equals(registeredEmail) && password.equals(registeredPassword)) {
                JOptionPane.showMessageDialog(loginFrame, "Login successful!");
                loginFrame.dispose();
                showMainMenu();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid email or password. Please try again.");
            }
        });

        loginBackgroundPanel.add(emailLabel);
        loginBackgroundPanel.add(emailField);
        loginBackgroundPanel.add(passwordLabel);
        loginBackgroundPanel.add(passwordField);
        loginBackgroundPanel.add(loginButton);

        loginFrame.setContentPane(loginBackgroundPanel);
        loginFrame.setVisible(true);
    }

    private void showMainMenu() {
        JFrame menuFrame = new JFrame("Admin Page");
        menuFrame.setSize(400, 600);
        menuFrame.setLocationRelativeTo(null);

        BackgroundPanel menuBackgroundPanel = new BackgroundPanel(); // Background for menu screen
        menuBackgroundPanel.setLayout(new BoxLayout(menuBackgroundPanel, BoxLayout.Y_AXIS));

        // Create and style buttons
        JButton scheduleButton = new JButton("Schedule Interview");
        styleMenuButton(scheduleButton);
        scheduleButton.addActionListener(e -> showScheduleInterviewForm());

        JButton approveVisaButton = new JButton("Approve Visa");
        styleMenuButton(approveVisaButton);
        approveVisaButton.addActionListener(e -> approveVisa());

        JButton rejectVisaButton = new JButton("Reject Visa");
        styleMenuButton(rejectVisaButton);
        rejectVisaButton.addActionListener(e -> rejectVisa());

        JButton moveToWaitingListButton = new JButton("Move to Waiting List");
        styleMenuButton(moveToWaitingListButton);
        moveToWaitingListButton.addActionListener(e -> moveToWaitingList());

        JButton showQueueButton = new JButton("Show Queue");
        styleMenuButton(showQueueButton);
        showQueueButton.addActionListener(e -> showQueue());

        JButton showWaitingListButton = new JButton("Show Waiting List");
        styleMenuButton(showWaitingListButton);
        showWaitingListButton.addActionListener(e -> showWaitingList());

        JButton exitButton = new JButton("Exit");
        styleMenuButton(exitButton);
        exitButton.addActionListener(e -> System.exit(0));

        menuBackgroundPanel.add(scheduleButton);
        menuBackgroundPanel.add(Box.createVerticalStrut(10));
        menuBackgroundPanel.add(approveVisaButton);
        menuBackgroundPanel.add(Box.createVerticalStrut(10));
        menuBackgroundPanel.add(rejectVisaButton);
        menuBackgroundPanel.add(Box.createVerticalStrut(10));
        menuBackgroundPanel.add(moveToWaitingListButton);
        menuBackgroundPanel.add(Box.createVerticalStrut(10));
        menuBackgroundPanel.add(showQueueButton);
        menuBackgroundPanel.add(Box.createVerticalStrut(10));
        menuBackgroundPanel.add(showWaitingListButton);
        menuBackgroundPanel.add(Box.createVerticalStrut(10));
        menuBackgroundPanel.add(exitButton);

        menuFrame.setContentPane(menuBackgroundPanel);
        menuFrame.setVisible(true);
    }

    // Styling method for menu buttons
    private void styleMenuButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18));  // Increased font size
        button.setBackground(new Color(139, 69, 19));  // Brown background
        button.setForeground(Color.WHITE);  // White text
        button.setPreferredSize(new Dimension(250, 50)); // Adjust button size
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center button
    }

    private void approveVisa() {
        String passportNumber = JOptionPane.showInputDialog(this, "Enter passport number of applicant to approve:");
        Applicant applicant = applicantMap.get(passportNumber);

        if (applicant != null && !applicant.getApplicationStatus().equals("Approved") && !applicant.getApplicationStatus().equals("Rejected")) {
            applicant.approve();
            JOptionPane.showMessageDialog(this, "Visa approved for " + applicant.getName());
        } else {
            JOptionPane.showMessageDialog(this, "Applicant not found or visa already processed.");
        }
    }

    private void rejectVisa() {
        String passportNumber = JOptionPane.showInputDialog(this, "Enter passport number of applicant to reject:");
        Applicant applicant = applicantMap.get(passportNumber);

        if (applicant != null && !applicant.getApplicationStatus().equals("Approved") && !applicant.getApplicationStatus().equals("Rejected")) {
            applicant.reject();
            JOptionPane.showMessageDialog(this, "Visa rejected for " + applicant.getName());
        } else {
            JOptionPane.showMessageDialog(this, "Applicant not found or visa already processed.");
        }
    }

    private void moveToWaitingList() {
        String passportNumber = JOptionPane.showInputDialog(this, "Enter passport number of applicant to move to waiting list:");
        Applicant applicant = applicantMap.get(passportNumber);

        if (applicant != null && !applicant.isInterviewScheduled()) {
            waitingList.add(applicant);
            JOptionPane.showMessageDialog(this, "Applicant moved to waiting list: " + applicant.getName());
        } else {
            JOptionPane.showMessageDialog(this, "Applicant not found or already scheduled for an interview.");
        }
    }

    private void showQueue() {
        if (interviewQueue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No applicants in the interview queue.");
        } else {
            StringBuilder queueList = new StringBuilder("Interview Queue:\n");
            for (Applicant applicant : interviewQueue) {
                queueList.append(applicant.getName()).append(" - ").append(applicant.getPassportNumber()).append("\n");
            }
            JOptionPane.showMessageDialog(this, queueList.toString());
        }
    }

    private void showWaitingList() {
        if (waitingList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No applicants in the waiting list.");
        } else {
            StringBuilder waitingListDetails = new StringBuilder("Waiting List:\n");
            for (Applicant applicant : waitingList) {
                waitingListDetails.append(applicant.getName()).append(" - ").append(applicant.getPassportNumber()).append("\n");
            }
            JOptionPane.showMessageDialog(this, waitingListDetails.toString());
        }
    }

    private void showScheduleInterviewForm() {
        String passportNumber = JOptionPane.showInputDialog(this, "Enter passport number of applicant to schedule an interview:");

        Applicant applicant = applicantMap.get(passportNumber);
        if (applicant != null && !applicant.isInterviewScheduled()) {
            String interviewDate = JOptionPane.showInputDialog(this, "Enter interview date (YYYY-MM-DD):");
            String interviewTime = JOptionPane.showInputDialog(this, "Enter interview time (HH:MM):");

            applicant.scheduleInterview(interviewDate, interviewTime);
            interviewQueue.add(applicant);
            JOptionPane.showMessageDialog(this, "Interview scheduled for " + applicant.getName() + " on " + interviewDate + " at " + interviewTime);
        } else {
            JOptionPane.showMessageDialog(this, "Applicant not found or already scheduled for an interview.");
        }
    }
    
    
// Custom JPanel class for background image
class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel() {
        try {
            // Replace with the full path to your image file
            backgroundImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Hp\\Downloads\backgroung.jpeg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmbassySystemGUI app = new EmbassySystemGUI();
            app.setVisible(true);
        });
    }
}

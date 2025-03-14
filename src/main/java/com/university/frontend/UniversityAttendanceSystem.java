package main.java.com.university.frontend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class UniversityAttendanceSystem {
    // HashMap to store user credentials (username -> password)
    private static HashMap<String, String> userCredentials = new HashMap<>();
    // HashMap to store attendance data (student username -> (date -> status))
    private static HashMap<String, HashMap<String, String>> attendanceData = new HashMap<>();
    // HashMap to store student information (username -> {name, index number})
    private static HashMap<String, String[]> studentInfo = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UniversityAttendanceSystem::createFrontPage);
    }

    // Front Page
    private static void createFrontPage() {
        JFrame frame = new JFrame("University Attendance System - Front Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Increased size for better responsiveness
        frame.setLocationRelativeTo(null);

        // Main panel with background image
        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            private BufferedImage backgroundImage;

            {
                try {
                    backgroundImage = ImageIO
                            .read(UniversityAttendanceSystem.class.getResource("/images/background.jpg")); // Replace
                                                                                                           // with your
                                                                                                           // image path
                } catch (IOException e) {

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
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 20, 20, 20); // Increased padding for better spacing

        // Title Label
        JLabel titleLabel = new JLabel("University Attendance System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Larger font size
        titleLabel.setForeground(Color.WHITE); // White text for better contrast
        gbc.gridy++;
        mainPanel.add(titleLabel, gbc);

        // Login as Lecturer Button
        JButton lecturerButton = new JButton("Login as Lecturer");
        lecturerButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Larger font size
        lecturerButton.setBackground(new Color(30, 144, 255)); // Modern blue color
        lecturerButton.setForeground(Color.WHITE); // White text
        lecturerButton.setFocusPainted(false); // Remove focus border
        lecturerButton.setPreferredSize(new Dimension(250, 50)); // Larger button size
        gbc.gridy++;
        mainPanel.add(lecturerButton, gbc);

        // Login as Student Button
        JButton studentButton = new JButton("Login as Student");
        studentButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Larger font size
        studentButton.setBackground(new Color(30, 144, 255)); // Modern blue color
        studentButton.setForeground(Color.WHITE); // White text
        studentButton.setFocusPainted(false); // Remove focus border
        studentButton.setPreferredSize(new Dimension(250, 50)); // Larger button size
        gbc.gridy++;
        mainPanel.add(studentButton, gbc);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Lecturer Button Action
        lecturerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the front page
                createLecturerLoginGUI(); // Open the lecturer login page
            }
        });

        // Student Button Action
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the front page
                createStudentLoginGUI(); // Open the student login page
            }
        });
    }

    // Lecturer Login Page
    private static void createLecturerLoginGUI() {
        JFrame frame = new JFrame("University Attendance System - Lecturer Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        // Main panel with background image
        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            private BufferedImage backgroundImage;

            {
                try {
                    backgroundImage = ImageIO
                            .read(UniversityAttendanceSystem.class.getResource("/images/background.jpg"));
                } catch (IOException e) {
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
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        // Center panel to hold login box
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        // Login box
        JPanel loginBox = new JPanel();
        loginBox.setPreferredSize(new Dimension(450, 500));
        loginBox.setBackground(Color.WHITE);
        loginBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        loginBox.setLayout(new GridBagLayout());
        GridBagConstraints gbcLogin = new GridBagConstraints();
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 0;
        gbcLogin.anchor = GridBagConstraints.CENTER;
        gbcLogin.insets = new Insets(5, 5, 5, 5);

        // Add logo
        JLabel logo = new JLabel();
        try {
            BufferedImage image = ImageIO.read(UniversityAttendanceSystem.class.getResource("/images/UMaT logo.jpg"));
            Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gbcLogin.gridy++;
        loginBox.add(logo, gbcLogin);

        JLabel title = new JLabel("University of Mines and Technology (UMaT)");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        gbcLogin.gridy++;
        loginBox.add(title, gbcLogin);

        JLabel subtitle = new JLabel("Lecturer Login");
        subtitle.setFont(new Font("Arial", Font.BOLD, 16));
        gbcLogin.gridy++;
        loginBox.add(subtitle, gbcLogin);

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(350, 35));
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(350, 35));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton signInButton = new JButton("Sign In");
        signInButton.setBackground(new Color(30, 144, 255));
        signInButton.setForeground(Color.WHITE);
        signInButton.setFocusPainted(false);

        JLabel signUpLabel = new JLabel("Don't have an account? Register now");
        signUpLabel.setForeground(Color.BLUE);
        signUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        JLabel termsLabel = new JLabel("Terms");
        termsLabel.setForeground(Color.BLUE);
        termsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel plansLabel = new JLabel("Plans");
        plansLabel.setForeground(Color.BLUE);
        plansLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel contactLabel = new JLabel("Contact Us");
        contactLabel.setForeground(Color.BLUE);
        contactLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        footerPanel.add(termsLabel);
        footerPanel.add(plansLabel);
        footerPanel.add(contactLabel);

        gbcLogin.gridy++;
        loginBox.add(usernameField, gbcLogin);
        gbcLogin.gridy++;
        loginBox.add(passwordField, gbcLogin);
        gbcLogin.gridy++;
        loginBox.add(signInButton, gbcLogin);
        gbcLogin.gridy++;
        loginBox.add(signUpLabel, gbcLogin);
        gbcLogin.gridy++;
        loginBox.add(footerPanel, gbcLogin);

        centerPanel.add(loginBox, new GridBagConstraints());
        mainPanel.add(centerPanel, gbc);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Sign-In Button Action
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Sign In Successful!");
                    frame.dispose(); // Close the lecturer login window
                    createLecturerDashboard(username); // Open the lecturer dashboard
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Username or Password", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Sign-Up Label Action
        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.dispose(); // Close the lecturer login window
                createLecturerSignUpGUI(); // Open the lecturer sign-up window
            }
        });
    }

    // Lecturer Sign-Up Page
    private static void createLecturerSignUpGUI() {
        JFrame frame = new JFrame("University Attendance System - Lecturer Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        // Main panel with background image
        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            private BufferedImage backgroundImage;

            {
                try {
                    backgroundImage = ImageIO.read(new File("background.jpg"));
                } catch (IOException e) {
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
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        // Center panel to hold sign-up box
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        // Sign-up box
        JPanel signUpBox = new JPanel();
        signUpBox.setPreferredSize(new Dimension(450, 500));
        signUpBox.setBackground(Color.WHITE);
        signUpBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        signUpBox.setLayout(new GridBagLayout());
        GridBagConstraints gbcSignUp = new GridBagConstraints();
        gbcSignUp.gridx = 0;
        gbcSignUp.gridy = 0;
        gbcSignUp.anchor = GridBagConstraints.CENTER;
        gbcSignUp.insets = new Insets(5, 5, 5, 5);

        // Add logo
        JLabel logo = new JLabel();
        try {
            BufferedImage image = ImageIO.read(new File("UMaT logo.jpg"));
            Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gbcSignUp.gridy++;
        signUpBox.add(logo, gbcSignUp);

        JLabel title = new JLabel("University of Mines and Technology (UMaT)");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        gbcSignUp.gridy++;
        signUpBox.add(title, gbcSignUp);

        JLabel subtitle = new JLabel("Lecturer Sign Up");
        subtitle.setFont(new Font("Arial", Font.BOLD, 16));
        gbcSignUp.gridy++;
        signUpBox.add(subtitle, gbcSignUp);

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(350, 35));
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(350, 35));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new Color(30, 144, 255));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);

        JLabel signInLabel = new JLabel("Already have an account? Sign in");
        signInLabel.setForeground(Color.BLUE);
        signInLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        JLabel termsLabel = new JLabel("Terms");
        termsLabel.setForeground(Color.BLUE);
        termsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel plansLabel = new JLabel("Plans");
        plansLabel.setForeground(Color.BLUE);
        plansLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel contactLabel = new JLabel("Contact Us");
        contactLabel.setForeground(Color.BLUE);
        contactLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        footerPanel.add(termsLabel);
        footerPanel.add(plansLabel);
        footerPanel.add(contactLabel);

        gbcSignUp.gridy++;
        signUpBox.add(usernameField, gbcSignUp);
        gbcSignUp.gridy++;
        signUpBox.add(passwordField, gbcSignUp);
        gbcSignUp.gridy++;
        signUpBox.add(signUpButton, gbcSignUp);
        gbcSignUp.gridy++;
        signUpBox.add(signInLabel, gbcSignUp);
        gbcSignUp.gridy++;
        signUpBox.add(footerPanel, gbcSignUp);

        centerPanel.add(signUpBox, new GridBagConstraints());
        mainPanel.add(centerPanel, gbc);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Sign-Up Button Action
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Username and Password cannot be empty!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (userCredentials.containsKey(username)) {
                    JOptionPane.showMessageDialog(frame, "Username already exists!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    userCredentials.put(username, password);
                    JOptionPane.showMessageDialog(frame, "Sign Up Successful!");
                    frame.dispose(); // Close the sign-up window
                    createLecturerLoginGUI(); // Redirect to lecturer login page
                }
            }
        });

        // Sign-In Label Action
        signInLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.dispose(); // Close the sign-up window
                createLecturerLoginGUI(); // Open the lecturer login window
            }
        });
    }

    // Lecturer Dashboard
    private static void createLecturerDashboard(String username) {
        JFrame frame = new JFrame("University Attendance System - Lecturer Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Adjusted size for better layout
        frame.setLocationRelativeTo(null);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240)); // Light gray background
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Welcome message at the top
        JLabel welcomeLabel = new JLabel("Welcome, Lecturer", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(30, 144, 255)); // Modern blue color
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Content panel in the center
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(240, 240, 240)); // Light gray background

        // Left Panel: Navigation (Smaller and single color)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(30, 144, 255)); // Single color for the sidebar
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // Reduced padding

        // Navigation buttons (Single color for all buttons)
        JButton homeButton = createNavButton("Home", new Color(30, 144, 255));
        JButton classesButton = createNavButton("Classes", new Color(30, 144, 255));
        JButton attendanceButton = createNavButton("Attendance", new Color(30, 144, 255));
        JButton assessmentsButton = createNavButton("Assessments", new Color(30, 144, 255));
        JButton settingsButton = createNavButton("Settings", new Color(30, 144, 255));
        JButton logoutButton = createNavButton("Logout", new Color(30, 144, 255)); // Same color for logout

        // Add buttons to left panel
        leftPanel.add(homeButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing
        leftPanel.add(classesButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(attendanceButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(assessmentsButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(settingsButton);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(logoutButton);

        // Right Panel: Statistics (Horizontal Layout)
        JPanel rightPanel = new JPanel(new GridLayout(1, 3, 20, 20)); // Horizontal layout
        rightPanel.setBackground(new Color(255, 255, 255)); // White background
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Classes Section
        JPanel classesPanel = createStatPanel("Classes", "5 Active", new Color(30, 144, 255));
        // Attendance Section
        JPanel attendancePanel = createStatPanel("Attendance", "85% Recorded", new Color(30, 144, 255));
        // Assessments Section
        JPanel assessmentsPanel = createStatPanel("Assessments", "3 Pending", new Color(30, 144, 255));

        // Add sections to the right panel
        rightPanel.add(classesPanel);
        rightPanel.add(attendancePanel);
        rightPanel.add(assessmentsPanel);

        // Add left and right panels to the content panel
        contentPanel.add(leftPanel, BorderLayout.WEST);
        contentPanel.add(rightPanel, BorderLayout.CENTER);

        // Add content panel to the main panel
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Add main panel to the frame
        frame.add(mainPanel);
        frame.setVisible(true);

        // Logout Button Action
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the dashboard
                createFrontPage(); // Open the front page
            }
        });

        // Home Button Action
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Home");
            }
        });

        // Classes Button Action
        classesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "View Classes");
            }
        });

        // Attendance Button Action
        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "View Attendance");
            }
        });

        // Assessments Button Action
        assessmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "View Assessments");
            }
        });

        // Settings Button Action
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "View Settings");
            }
        });
    }

    // Helper method to create navigation buttons
    private static JButton createNavButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14)); // Smaller font size
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        button.setAlignmentX(Component.LEFT_ALIGNMENT); // Align text to the left
        button.setPreferredSize(new Dimension(120, 40)); // Smaller button size
        return button;
    }

    // Helper method to create statistic panels
    private static JPanel createStatPanel(String title, String value, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(color);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel);

        // Value
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        valueLabel.setForeground(Color.WHITE);
        panel.add(valueLabel);

        return panel;
    }

    // Student Login Page
    private static void createStudentLoginGUI() {
        JFrame frame = new JFrame("University Attendance System - Student Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        // Main panel with background image
        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            private BufferedImage backgroundImage;

            {
                try {
                    backgroundImage = ImageIO.read(new File("background.jpg"));
                } catch (IOException e) {
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
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        // Center panel to hold login box
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        // Login box
        JPanel loginBox = new JPanel();
        loginBox.setPreferredSize(new Dimension(450, 500));
        loginBox.setBackground(Color.WHITE);
        loginBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        loginBox.setLayout(new GridBagLayout());
        GridBagConstraints gbcLogin = new GridBagConstraints();
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 0;
        gbcLogin.anchor = GridBagConstraints.CENTER;
        gbcLogin.insets = new Insets(5, 5, 5, 5);

        // Add logo
        JLabel logo = new JLabel();
        try {
            BufferedImage image = ImageIO.read(new File("UMaT logo.jpg"));
            Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gbcLogin.gridy++;
        loginBox.add(logo, gbcLogin);

        JLabel title = new JLabel("University of Mines and Technology (UMaT)");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        gbcLogin.gridy++;
        loginBox.add(title, gbcLogin);

        JLabel subtitle = new JLabel("Student Login");
        subtitle.setFont(new Font("Arial", Font.BOLD, 16));
        gbcLogin.gridy++;
        loginBox.add(subtitle, gbcLogin);

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(350, 35));
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(350, 35));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton signInButton = new JButton("Sign In");
        signInButton.setBackground(new Color(30, 144, 255));
        signInButton.setForeground(Color.WHITE);
        signInButton.setFocusPainted(false);

        JLabel signUpLabel = new JLabel("Don't have an account? Register now");
        signUpLabel.setForeground(Color.BLUE);
        signUpLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        JLabel termsLabel = new JLabel("Terms");
        termsLabel.setForeground(Color.BLUE);
        termsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel plansLabel = new JLabel("Plans");
        plansLabel.setForeground(Color.BLUE);
        plansLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel contactLabel = new JLabel("Contact Us");
        contactLabel.setForeground(Color.BLUE);
        contactLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        footerPanel.add(termsLabel);
        footerPanel.add(plansLabel);
        footerPanel.add(contactLabel);

        gbcLogin.gridy++;
        loginBox.add(usernameField, gbcLogin);
        gbcLogin.gridy++;
        loginBox.add(passwordField, gbcLogin);
        gbcLogin.gridy++;
        loginBox.add(signInButton, gbcLogin);
        gbcLogin.gridy++;
        loginBox.add(signUpLabel, gbcLogin);
        gbcLogin.gridy++;
        loginBox.add(footerPanel, gbcLogin);

        centerPanel.add(loginBox, new GridBagConstraints());
        mainPanel.add(centerPanel, gbc);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Sign-In Button Action
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(frame, "Sign In Successful!");
                    frame.dispose(); // Close the student login window
                    createStudentDashboard(username); // Open the student dashboard
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Username or Password", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Sign-Up Label Action
        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.dispose(); // Close the student login window
                createStudentSignUpGUI(); // Open the student sign-up window
            }
        });
    }

    // Student Sign-Up Page
    private static void createStudentSignUpGUI() {
        JFrame frame = new JFrame("University Attendance System - Student Sign Up");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);

        // Main panel with background image
        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            private BufferedImage backgroundImage;

            {
                try {
                    backgroundImage = ImageIO.read(new File("background.jpg"));
                } catch (IOException e) {
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
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        // Center panel to hold sign-up box
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        // Sign-up box
        JPanel signUpBox = new JPanel();
        signUpBox.setPreferredSize(new Dimension(450, 500));
        signUpBox.setBackground(Color.WHITE);
        signUpBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        signUpBox.setLayout(new GridBagLayout());
        GridBagConstraints gbcSignUp = new GridBagConstraints();
        gbcSignUp.gridx = 0;
        gbcSignUp.gridy = 0;
        gbcSignUp.anchor = GridBagConstraints.CENTER;
        gbcSignUp.insets = new Insets(5, 5, 5, 5);

        // Add logo
        JLabel logo = new JLabel();
        try {
            BufferedImage image = ImageIO.read(new File("UMaT logo.jpg"));
            Image scaledImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gbcSignUp.gridy++;
        signUpBox.add(logo, gbcSignUp);

        JLabel title = new JLabel("University of Mines and Technology (UMaT)");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        gbcSignUp.gridy++;
        signUpBox.add(title, gbcSignUp);

        JLabel subtitle = new JLabel("Student Sign Up");
        subtitle.setFont(new Font("Arial", Font.BOLD, 16));
        gbcSignUp.gridy++;
        signUpBox.add(subtitle, gbcSignUp);

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(350, 35));
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(350, 35));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new Color(30, 144, 255));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);

        JLabel signInLabel = new JLabel("Already have an account? Sign in");
        signInLabel.setForeground(Color.BLUE);
        signInLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        JLabel termsLabel = new JLabel("Terms");
        termsLabel.setForeground(Color.BLUE);
        termsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel plansLabel = new JLabel("Plans");
        plansLabel.setForeground(Color.BLUE);
        plansLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel contactLabel = new JLabel("Contact Us");
        contactLabel.setForeground(Color.BLUE);
        contactLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        footerPanel.add(termsLabel);
        footerPanel.add(plansLabel);
        footerPanel.add(contactLabel);

        gbcSignUp.gridy++;
        signUpBox.add(usernameField, gbcSignUp);
        gbcSignUp.gridy++;
        signUpBox.add(passwordField, gbcSignUp);
        gbcSignUp.gridy++;
        signUpBox.add(signUpButton, gbcSignUp);
        gbcSignUp.gridy++;
        signUpBox.add(signInLabel, gbcSignUp);
        gbcSignUp.gridy++;
        signUpBox.add(footerPanel, gbcSignUp);

        centerPanel.add(signUpBox, new GridBagConstraints());
        mainPanel.add(centerPanel, gbc);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Sign-Up Button Action
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Username and Password cannot be empty!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else if (userCredentials.containsKey(username)) {
                    JOptionPane.showMessageDialog(frame, "Username already exists!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    userCredentials.put(username, password);
                    JOptionPane.showMessageDialog(frame, "Sign Up Successful!");
                    frame.dispose(); // Close the sign-up window
                    createStudentLoginGUI(); // Redirect to student login page
                }
            }
        });

        // Sign-In Label Action
        signInLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.dispose(); // Close the sign-up window
                createStudentLoginGUI(); // Open the student login window
            }
        });
    }

    // Student Dashboard
    private static void createStudentDashboard(String username) {
        JFrame frame = new JFrame("University Attendance System - Student Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Dashboard content
        JPanel contentPanel = new JPanel(new GridLayout(1, 1, 10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Attendance Report Section
        JPanel reportPanel = new JPanel(new BorderLayout());
        reportPanel.setBorder(BorderFactory.createTitledBorder("Your Attendance Report"));

        // Table for attendance report
        String[] columnNames = { "Date", "Status" };
        HashMap<String, String> studentAttendance = attendanceData.getOrDefault(username, new HashMap<>());

        Object[][] data = new Object[studentAttendance.size()][2];
        int i = 0;
        for (String date : studentAttendance.keySet()) {
            data[i][0] = date;
            data[i][1] = studentAttendance.get(date);
            i++;
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable reportTable = new JTable(model);
        reportTable.setFont(new Font("Arial", Font.PLAIN, 14));
        reportTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(reportTable);
        reportPanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(reportPanel);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Navigation and Logout Section
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 16));
        logoutButton.setBackground(new Color(255, 69, 0));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);

        navPanel.add(logoutButton);

        mainPanel.add(navPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Logout Button Action
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the dashboard
                createFrontPage(); // Open the front page
            }
        });
    }
}
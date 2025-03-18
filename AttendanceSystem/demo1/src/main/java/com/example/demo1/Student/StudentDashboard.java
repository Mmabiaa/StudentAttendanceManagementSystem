package com.example.demo1.Student;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class StudentDashboard extends Application {

    private static final String STUDENT_INFO_FILE = "demo1/Student.json";

    @Override
    public void start(Stage primaryStage) {
        start(primaryStage, null);
    }

    public void start(Stage primaryStage, String username) {
        // Root Layout
        HBox root = new HBox();

        // Sidebar
        VBox sidebar = createSidebar(primaryStage);

        // Main Content
        VBox mainContent = createMainContent(username);

        root.getChildren().addAll(sidebar, mainContent);
        Scene scene = new Scene(root, 900, 500);

        primaryStage.setTitle("Student Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createSidebar(Stage primaryStage) {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 250px;");

        Label dashboardTitle = new Label("Student Portal");
        dashboardTitle.setTextFill(Color.WHITE);
        dashboardTitle.setFont(new Font(20));

        Button homeBtn = createSidebarButton("Home", primaryStage);
        Button attendanceBtn = createSidebarButton("Attendance", primaryStage);
        Button assessmentBtn = createSidebarButton("Assessments", primaryStage);
        Button settingsBtn = createSidebarButton("Settings", primaryStage);
        Button logoutBtn = createSidebarButton("Logout", primaryStage);

        sidebar.getChildren().addAll(dashboardTitle, homeBtn, attendanceBtn, assessmentBtn, settingsBtn, logoutBtn);
        return sidebar;
    }

    private VBox createMainContent(String username) {
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));
        mainContent.setStyle("-fx-background-color: #f4f4f4; -fx-pref-width: 600px;");

        Label welcomeText = new Label("Welcome, Student");
        welcomeText.setFont(new Font(24));
        welcomeText.setTextFill(Color.web("#2c3e50"));

        Label studentIDLabel = new Label("Student ID: N/A");
        studentIDLabel.setFont(new Font(14));
        studentIDLabel.setTextFill(Color.web("#777777"));

        Label emailLabel = new Label("Email: N/A");
        emailLabel.setFont(new Font(14));
        emailLabel.setTextFill(Color.web("#777777"));

        Label courseLabel = new Label("Course: N/A");
        courseLabel.setFont(new Font(14));
        courseLabel.setTextFill(Color.web("#777777"));

        // Load student info if username is available
        if (username != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                File file = new File(STUDENT_INFO_FILE);
                if (file.exists()) {
                    StudentLogin.Student[] students = mapper.readValue(file, StudentLogin.Student[].class);
                    for (StudentLogin.Student student : students) {
                        if (student.getUsername().equals(username)) {
                            welcomeText.setText("Welcome, " + student.getFullName());
                            studentIDLabel.setText("Student ID: " + student.getUsername());
                            emailLabel.setText("Email: " + student.getEmail());
                            courseLabel.setText("Course: Computer Science and Engineering"); // Set actual course if available
                            break;
                        }
                    }
                } else {
                    showErrorAlert("File Not Found", "Could not find " + STUDENT_INFO_FILE);
                }
            } catch (IOException e) {
                showErrorAlert("IO Exception", "Could not read " + STUDENT_INFO_FILE);
            }
        }

        HBox overview = new HBox(20);
        overview.setPadding(new Insets(10));
        overview.getChildren().addAll(createCard("Attendance", "85% Recorded"), createCard("Assessments", "3 Pending"),
                createCard("Pending Work", "3"));

        mainContent.getChildren().addAll(welcomeText, studentIDLabel, emailLabel, courseLabel, overview);
        return mainContent;
    }

    private Button createSidebarButton(String text, Stage primaryStage) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #34495e; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white;"));

        // Define Navigation Actions
        button.setOnAction(e -> {
            switch (text) {
                case "Home":
                    new StudentDashboard().start(primaryStage);
                    break;
                case "Attendance":
                    new StudentAttendanceView().start(primaryStage);
                    break;
                case "Assessments":
                    new StudentAssessmentView().start(primaryStage);
                    break;
                case "Settings":
                    new StudentSettingsView().start(primaryStage);
                    break;
                case "Logout":
                    primaryStage.close(); // Close the application
                    break;
            }
        });

        return button;
    }

    private VBox createCard(String title, String value) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setStyle(
                "-fx-background-color: white; -fx-border-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);");

        Label titleLabel = new Label(title);
        titleLabel.setFont(new Font(16));
        titleLabel.setTextFill(Color.web("#2c3e50"));

        Label valueLabel = new Label(value);
        valueLabel.setFont(new Font(18));
        valueLabel.setTextFill(Color.web("#2980b9"));

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

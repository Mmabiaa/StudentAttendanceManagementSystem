package com.example.demo1.Lecturer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LecturerDashboard extends Application {

    private static final String LECTURER_INFO_FILE = "lecturer_info.json";
    private static final String JSON_NAME_KEY = "name";
    private static final String JSON_EMAIL_KEY = "email";
    private static final String JSON_STAFF_ID_KEY = "staffId";

    private String lecturerName = "Lecturer"; // Default name
    private String lecturerEmail = "";
    private String lecturerStaffId = "";

    @Override
    public void start(Stage primaryStage) {
        // Load lecturer info from file
        loadLecturerInfo();

        // Sidebar
        VBox sidebar = createSidebar(primaryStage);

        // Main Content
        VBox mainContent = createDashboardContent();

        // Root Layout
        HBox root = new HBox();
        root.getChildren().addAll(sidebar, mainContent);

        Scene scene = new Scene(root, 900, 500);
        primaryStage.setTitle("Lecturer Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadLecturerInfo() {
        try (FileReader reader = new FileReader(LECTURER_INFO_FILE)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject root = new JSONObject(tokener);

            lecturerName = root.optString(JSON_NAME_KEY, "Lecturer");
            lecturerEmail = root.optString(JSON_EMAIL_KEY, "");
            lecturerStaffId = root.optString(JSON_STAFF_ID_KEY, "");

        } catch (FileNotFoundException e) {
            System.err.println("Lecturer info file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading lecturer info: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error processing JSON: " + e.getMessage());
        }
    }
    private VBox createSidebar(Stage primaryStage) {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 250px;");

        Label dashboardTitle = new Label("Dashboard");
        dashboardTitle.setTextFill(Color.WHITE);
        dashboardTitle.setFont(new Font(20));

        Button homeBtn = createSidebarButton("Home", primaryStage);
        Button classesBtn = createSidebarButton("Classes", primaryStage);
        Button attendanceBtn = createSidebarButton("Attendance", primaryStage);
        Button assessmentsBtn = createSidebarButton("Assessments", primaryStage);
        Button settingsBtn = createSidebarButton("Settings", primaryStage);
        Button logoutBtn = createSidebarButton("Logout", primaryStage);

        sidebar.getChildren().addAll(dashboardTitle, homeBtn, classesBtn, attendanceBtn, assessmentsBtn, settingsBtn,
                logoutBtn);
        return sidebar;
    }

    private VBox createDashboardContent() {
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));
        mainContent.setStyle("-fx-background-color: #f4f4f4; -fx-pref-width: 600px;");

        Label welcomeText = new Label("Welcome, " + lecturerName);
        welcomeText.setFont(new Font(24));
        welcomeText.setTextFill(Color.web("#2c3e50"));

        Label emailLabel = new Label("Email: " + lecturerEmail);
        emailLabel.setFont(new Font(14));
        emailLabel.setTextFill(Color.web("#777777"));

        Label staffIdLabel = new Label("Staff ID: " + lecturerStaffId);
        staffIdLabel.setFont(new Font(14));
        staffIdLabel.setTextFill(Color.web("#777777"));

        HBox overview = new HBox(20);
        overview.setPadding(new Insets(10));
        overview.getChildren().addAll(createCard("Classes", "5 Active"), createCard("Attendance", "85% Recorded"),
                createCard("Assessments", "3 Pending"));

        mainContent.getChildren().addAll(welcomeText, emailLabel, staffIdLabel, overview);
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
                    new LecturerDashboard().start(primaryStage);
                    break;
                case "Classes":
                    new ClassManagement().start(primaryStage);
                    break;
                case "Attendance":
                    new AttendanceManagement().start(primaryStage);
                    break;
                case "Assessments":
                    new AssessmentManagement().start(primaryStage);
                    break;
                case "Settings":
                    new LecturerSettings().start(primaryStage);
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

    public static void main(String[] args) {
        launch(args);
    }
}

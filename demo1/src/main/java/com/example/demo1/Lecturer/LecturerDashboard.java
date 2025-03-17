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

public class LecturerDashboard extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Sidebar
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 250px;");

        Label dashboardTitle = new Label("Dashboard");
        dashboardTitle.setTextFill(Color.WHITE);
        dashboardTitle.setFont(new Font(20));

        Button homeBtn = createSidebarButton("Home");
        Button classesBtn = createSidebarButton("Classes");
        Button attendanceBtn = createSidebarButton("Attendance");
        Button assessmentsBtn = createSidebarButton("Assessments");
        Button settingsBtn = createSidebarButton("Settings");
        Button logoutBtn = createSidebarButton("Logout");

        // Button actions to navigate between pages
        homeBtn.setOnAction(e -> new AssessmentManagement().start(new Stage()));
        classesBtn.setOnAction(e -> new ClassManagement().start(new Stage()));
        attendanceBtn.setOnAction(e -> new AttendanceManagement().start(new Stage()));
        assessmentsBtn.setOnAction(e -> new AssessmentManagement().start(new Stage()));
        settingsBtn.setOnAction(e -> new LecturerSettings().start(new Stage()));

        sidebar.getChildren().addAll(dashboardTitle, homeBtn, classesBtn, attendanceBtn, assessmentsBtn, settingsBtn, logoutBtn);

        // Main Content
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));
        mainContent.setStyle("-fx-background-color: #f4f4f4; -fx-pref-width: 600px;");

        Label welcomeText = new Label("Welcome, Lecturer");
        welcomeText.setFont(new Font(24));
        welcomeText.setTextFill(Color.web("#2c3e50"));

        HBox overview = new HBox(20);
        overview.setPadding(new Insets(10));
        overview.getChildren().addAll(createCard("Classes", "5 Active"), createCard("Attendance", "85% Recorded"), createCard("Assessments", "3 Pending"));

        mainContent.getChildren().addAll(welcomeText, overview);

        // Root Layout
        HBox root = new HBox();
        root.getChildren().addAll(sidebar, mainContent);

        Scene scene = new Scene(root, 900, 500);
        primaryStage.setTitle("Lecturer Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createSidebarButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #34495e; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white;"));
        return button;
    }

    private VBox createCard(String title, String value) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: white; -fx-border-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);");

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
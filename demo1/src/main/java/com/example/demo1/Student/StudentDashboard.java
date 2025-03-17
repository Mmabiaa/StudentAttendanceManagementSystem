package com.example.demo1.Student;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentDashboard extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Sidebar (Navigation)
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 200px;");

        Label title = new Label("Student Portal");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        Button homeBtn = createSidebarButton("Home");
        Button attendanceBtn = createSidebarButton("Attendance");
        Button assessmentBtn = createSidebarButton("Assessments");
        Button settingsBtn = createSidebarButton("Settings");
        Button logoutBtn = createSidebarButton("Logout");

        sidebar.getChildren().addAll(title, homeBtn, attendanceBtn, assessmentBtn, settingsBtn, logoutBtn);

        // Main Content
        VBox mainContent = new VBox(15);
        mainContent.setPadding(new Insets(20));
        mainContent.setAlignment(Pos.TOP_LEFT);

        Label welcomeLabel = new Label("Welcome, Student");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Overview Section
        HBox overview = new HBox(20);
        overview.setAlignment(Pos.CENTER_LEFT);

        VBox attendanceCard = createCard("Attendance", "85%");
        VBox assessmentsCard = createCard("Assessments", "View your latest scores");
        VBox pendingCard = createCard("Pending Work", "3 Pending");

        overview.getChildren().addAll(attendanceCard, assessmentsCard, pendingCard);

        // Registered Courses
        Label coursesLabel = new Label("Your Registered Courses");
        coursesLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        VBox coursesList = new VBox(5);
        coursesList.getChildren().addAll(
                createCourseLabel("Programming with Java"),
                createCourseLabel("Computer Graphics"),
                createCourseLabel("Advanced Database"),
                createCourseLabel("Digital Design System")
        );

        mainContent.getChildren().addAll(welcomeLabel, overview, coursesLabel, coursesList);

        // Layout
        HBox root = new HBox(sidebar, mainContent);
        Scene scene = new Scene(root, 800, 500);

        primaryStage.setTitle("Student Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to create sidebar buttons
    private Button createSidebarButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setPrefWidth(180);
        return button;
    }

    // Helper method to create dashboard cards
    private VBox createCard(String title, String value) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color: white; -fx-border-radius: 8px; -fx-border-color: #ccc; -fx-padding: 15px;");
        card.setAlignment(Pos.CENTER);
        card.setPrefSize(180, 100);

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #2c3e50; -fx-font-weight: bold;");

        Label valueLabel = new Label(value);
        valueLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #2980b9; -fx-font-weight: bold;");

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }

    // Helper method to create course labels
    private Label createCourseLabel(String courseName) {
        Label label = new Label(courseName);
        label.setStyle("-fx-font-size: 14px; -fx-text-fill: #2c3e50;");
        return label;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
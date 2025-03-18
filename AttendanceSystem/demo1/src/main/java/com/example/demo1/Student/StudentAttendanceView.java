package com.example.demo1.Student;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentAttendanceView extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Sidebar menu
        VBox sidebar = new VBox(20); // Increased spacing
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 250px;");

        Label title = new Label("Student Portal");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 22px;"); // Increased font size
        title.setPadding(new Insets(10)); // Added padding

        Button btnHome = createSidebarButton("Home", primaryStage);
        Button btnAttendance = createSidebarButton("Attendance", primaryStage);
        Button btnAssessments = createSidebarButton("Assessments", primaryStage);
        Button btnSettings = createSidebarButton("Settings", primaryStage);
        Button btnLogout = createSidebarButton("Logout", primaryStage);

        sidebar.getChildren().addAll(title, btnHome, btnAttendance, btnAssessments, btnSettings, btnLogout);

        // Main content area (centered)
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20); // Added horizontal gap
        grid.setVgap(10); // Added vertical gap

        Label heading = new Label("Attendance Records");
        heading.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        grid.add(heading, 0, 0, 3, 1);

        Label dateLabel = new Label("Date");
        dateLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        grid.add(dateLabel, 0, 1);

        Label courseLabel = new Label("Course");
        courseLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        grid.add(courseLabel, 1, 1);

        Label statusLabel = new Label("Status");
        statusLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        grid.add(statusLabel, 2, 1);

        // Sample data
        String[][] data = {
                {"March 1, 2025", "Programming with Java", "Present"},
                {"March 2, 2025", "Computer Graphics", "Absent"},
                {"March 3, 2025", "Advanced Database", "Present"},
                {"March 4, 2025", "Cybersecurity", "Present"},
                {"March 5, 2025", "Software Engineering", "Absent"},
                {"March 6, 2025", "Artificial Intelligence", "Present"},
                {"March 7, 2025", "Operating Systems", "Present"},
                {"March 8, 2025", "Network Security", "Absent"},
                {"March 9, 2025", "Web Development", "Present"},
                {"March 10, 2025", "Data Structures", "Present"}
        };

        for (int i = 0; i < data.length; i++) {
            Label date = new Label(data[i][0]);
            date.setStyle("-fx-font-size: 16px;");
            grid.add(date, 0, i + 2);

            Label course = new Label(data[i][1]);
            course.setStyle("-fx-font-size: 16px;");
            grid.add(course, 1, i + 2);

            Label status = new Label(data[i][2]);
            status.setStyle("-fx-font-size: 16px;");
            grid.add(status, 2, i + 2);
        }

        // Layout
        BorderPane layout = new BorderPane();
        layout.setLeft(sidebar);
        layout.setCenter(grid);

        Scene scene = new Scene(layout, 900, 700); // Increased window size
        primaryStage.setTitle("Student Attendance View");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createSidebarButton(String text, Stage primaryStage) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: #34495e; -fx-text-fill: white; -fx-pref-width: 200px; -fx-cursor: hand;");

        // Hover Effect
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #1abc9c; -fx-text-fill: white; -fx-pref-width: 200px; -fx-cursor: hand;"));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: #34495e; -fx-text-fill: white; -fx-pref-width: 200px; -fx-cursor: hand;"));

        // Click Event to navigate
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
                    primaryStage.close();
                    break;
            }
        });

        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

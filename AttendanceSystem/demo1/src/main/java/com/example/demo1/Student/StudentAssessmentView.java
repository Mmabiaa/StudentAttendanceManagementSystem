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

public class StudentAssessmentView extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Sidebar menu
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 250px;");

        Label title = new Label("Student Portal");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        Button btnHome = createSidebarButton("\uD83C\uDFE0 Home", primaryStage);
        Button btnAttendance = createSidebarButton("\uD83D\uDC64 Attendance", primaryStage);
        Button btnAssessments = createSidebarButton("\uD83D\uDCCB Assessments", primaryStage);
        Button btnSettings = createSidebarButton("⚙ Settings", primaryStage);
        Button btnLogout = createSidebarButton("\uD83D\uDEAA Logout", primaryStage);

        sidebar.getChildren().addAll(title, btnHome, btnAttendance, btnAssessments, btnSettings, btnLogout);

        // Main content area
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20); // Horizontal gap
        grid.setVgap(10); // Vertical gap

        Label heading = new Label("Assessments");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        grid.add(heading, 0, 0, 4, 1);

        Label courseLabel = new Label("Course");
        courseLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        grid.add(courseLabel, 0, 1);

        Label typeLabel = new Label("Assessment Type");
        typeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        grid.add(typeLabel, 1, 1);

        Label scoreLabel = new Label("Score");
        scoreLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        grid.add(scoreLabel, 2, 1);

        Label gradeLabel = new Label("Grade");
        gradeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        grid.add(gradeLabel, 3, 1);

        // Sample data
        String[][] data = {
                {"Programming with Java", "Quiz", "15", "B"},
                {"Computer Graphics", "Quiz 2", "15", "A"},
                {"Advanced Database", "Final Exam", "70%", "A"},
                {"Cybersecurity", "Lab Test", "20", "B+"},
                {"Software Engineering", "Midterm", "40", "A-"},
                {"Artificial Intelligence", "Assignment", "30", "B"},
                {"Operating Systems", "Quiz 3", "15", "A"},
                {"Network Security", "Project", "50", "B+"},
                {"Web Development", "Final Project", "60", "A-"},
                {"Data Structures", "Quiz 4", "15", "B+"}
        };

        for (int i = 0; i < data.length; i++) {
            Label course = new Label(data[i][0]);
            course.setStyle("-fx-font-size: 16px;");
            grid.add(course, 0, i + 2);

            Label type = new Label(data[i][1]);
            type.setStyle("-fx-font-size: 16px;");
            grid.add(type, 1, i + 2);

            Label score = new Label(data[i][2]);
            score.setStyle("-fx-font-size: 16px;");
            grid.add(score, 2, i + 2);

            Label grade = new Label(data[i][3]);
            grade.setStyle("-fx-font-size: 16px;");
            grid.add(grade, 3, i + 2);
        }

        // Layout
        BorderPane layout = new BorderPane();
        layout.setLeft(sidebar);
        layout.setCenter(grid);

        Scene scene = new Scene(layout, 1000, 700);
        primaryStage.setTitle("Student Assessment View");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createSidebarButton(String text, Stage primaryStage) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: #34495e; -fx-text-fill: white; -fx-pref-width: 200px; -fx-padding: 10px; -fx-font-size: 14px;");
        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #1abc9c; -fx-text-fill: white; -fx-pref-width: 200px; -fx-padding: 10px; -fx-font-size: 14px;"));
        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: #34495e; -fx-text-fill: white; -fx-pref-width: 200px; -fx-padding: 10px; -fx-font-size: 14px;"));

        button.setOnAction(e -> {
            switch (text) {
                case "\uD83C\uDFE0 Home":
                    new StudentDashboard().start(primaryStage);
                    break;
                case "\uD83D\uDC64 Attendance":
                    new StudentAttendanceView().start(primaryStage);
                    break;
                case "\uD83D\uDCCB Assessments":
                    new StudentAssessmentView().start(primaryStage);
                    break;
                case "⚙ Settings":
                    new StudentSettingsView().start(primaryStage);
                    break;
                case "\uD83D\uDEAA Logout":
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

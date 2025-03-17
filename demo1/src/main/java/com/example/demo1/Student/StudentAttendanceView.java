package com.example.demo1.Student;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentAttendanceView extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Sidebar menu
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 250px;");

        Label title = new Label("Student Portal");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        Button btnHome = createSidebarButton("Home");
        Button btnAttendance = createSidebarButton("Attendance");
        Button btnAssessments = createSidebarButton("Assessments");
        Button btnSettings = createSidebarButton("Settings");
        Button btnLogout = createSidebarButton("Logout");

        sidebar.getChildren().addAll(title, btnHome, btnAttendance, btnAssessments, btnSettings, btnLogout);

        // Main content area
        VBox mainContent = new VBox(15);
        mainContent.setPadding(new Insets(20));

        Label heading = new Label("Attendance Records");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TableView<AttendanceRecord> table = new TableView<>();

        TableColumn<AttendanceRecord, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<AttendanceRecord, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));

        TableColumn<AttendanceRecord, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().addAll(dateCol, courseCol, statusCol);

        table.getItems().addAll(
                new AttendanceRecord("March 1, 2025", "Programming with Java", "Present"),
                new AttendanceRecord("March 2, 2025", "Computer Graphics", "Absent"),
                new AttendanceRecord("March 3, 2025", "Advanced Database", "Present")
        );

        mainContent.getChildren().addAll(heading, table);

        // Layout
        BorderPane layout = new BorderPane();
        layout.setLeft(sidebar);
        layout.setCenter(mainContent);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setTitle("Student Attendance View");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createSidebarButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: #34495e; -fx-text-fill: white; -fx-pref-width: 200px;");
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class AttendanceRecord {
    private String date;
    private String course;
    private String status;

    public AttendanceRecord(String date, String course, String status) {
        this.date = date;
        this.course = course;
        this.status = status;
    }

    public String getDate() { return date; }
    public String getCourse() { return course; }
    public String getStatus() { return status; }
}

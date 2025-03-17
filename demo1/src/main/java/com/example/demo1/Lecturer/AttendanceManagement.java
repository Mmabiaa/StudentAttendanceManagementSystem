package com.example.demo1.Lecturer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class AttendanceManagement extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Sidebar
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-padding: 20px;");
        Label dashboardLabel = new Label("Dashboard");
        dashboardLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        Button homeBtn = new Button("Home");
        Button classesBtn = new Button("Classes");
        Button attendanceBtn = new Button("Attendance");
        Button assessmentsBtn = new Button("Assessments");
        Button settingsBtn = new Button("Settings");
        Button logoutBtn = new Button("Logout");

        // Button actions to navigate between pages
        homeBtn.setOnAction(e -> new AssessmentManagement().start(new Stage()));
        classesBtn.setOnAction(e -> new ClassManagement().start(new Stage()));
        attendanceBtn.setOnAction(e -> new AttendanceManagement().start(new Stage()));
        assessmentsBtn.setOnAction(e -> new AssessmentManagement().start(new Stage()));
        settingsBtn.setOnAction(e -> new LecturerSettings().start(new Stage()));



        sidebar.getChildren().addAll(dashboardLabel, homeBtn, classesBtn, attendanceBtn, assessmentsBtn, settingsBtn, logoutBtn);
        sidebar.setPrefWidth(200);

        // Main Content
        VBox mainContent = new VBox(10);
        mainContent.setPadding(new Insets(20));
        Label title = new Label("Attendance Taking");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label classLabel = new Label("Class: Programming with Java");
        Label dateLabel = new Label("Date: " + LocalDate.now().toString());

        TableView<String> table = new TableView<>();
        TableColumn<String, String> indexCol = new TableColumn<>("Index Number");
        TableColumn<String, String> nameCol = new TableColumn<>("Student Name");
        TableColumn<String, CheckBox> statusCol = new TableColumn<>("Status");

        table.getColumns().addAll(indexCol, nameCol, statusCol);

        Button submitBtn = new Button("Submit Attendance");
        submitBtn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");

        mainContent.getChildren().addAll(title, classLabel, dateLabel, table, submitBtn);

        HBox root = new HBox(sidebar, mainContent);
        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Attendance Management");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
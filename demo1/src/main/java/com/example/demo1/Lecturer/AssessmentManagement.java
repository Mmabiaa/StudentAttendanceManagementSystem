package com.example.demo1.Lecturer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AssessmentManagement extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lecturer Dashboard - Assessment Management");

        // Sidebar Navigation
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-padding: 10px;");

        Label portalLabel = new Label("Student Portal");
        portalLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
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

        sidebar.getChildren().addAll(portalLabel, homeBtn, classesBtn, attendanceBtn, assessmentsBtn, settingsBtn, logoutBtn);

        // Main Content
        VBox mainContent = new VBox(10);
        mainContent.setPadding(new Insets(20));
        Label titleLabel = new Label("Assessment Management");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Add Assessment Form
        GridPane form = new GridPane();
        form.setVgap(10);
        form.setHgap(10);
        form.setPadding(new Insets(10));

        Label titleLbl = new Label("Title:");
        TextField titleField = new TextField();
        Label dateLbl = new Label("Due Date:");
        DatePicker datePicker = new DatePicker();
        Label descLbl = new Label("Description:");
        TextArea descArea = new TextArea();
        Button addAssessmentBtn = new Button("Add Assessment");

        form.add(titleLbl, 0, 0);
        form.add(titleField, 1, 0);
        form.add(dateLbl, 0, 1);
        form.add(datePicker, 1, 1);
        form.add(descLbl, 0, 2);
        form.add(descArea, 1, 2);
        form.add(addAssessmentBtn, 1, 3);

        // Table to display assessments
        TableView<String> tableView = new TableView<>();

        mainContent.getChildren().addAll(titleLabel, form, tableView);

        // Root Layout
        HBox root = new HBox();
        root.getChildren().addAll(sidebar, mainContent);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

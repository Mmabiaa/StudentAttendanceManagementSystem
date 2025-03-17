package com.example.demo1.Lecturer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ClassManagement extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Sidebar
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 250px;");

        Label portalTitle = new Label("Student Portal");
        portalTitle.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

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

        sidebar.getChildren().addAll(portalTitle, homeBtn, classesBtn, attendanceBtn, assessmentsBtn, settingsBtn, logoutBtn);

        // Main Content
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));
        mainContent.setStyle("-fx-background-color: #f9f9f9; -fx-pref-width: 600px;");

        Label titleLabel = new Label("Class Management");
        titleLabel.setStyle("-fx-font-size: 24px;");

        HBox formGroup = new HBox(10);
        TextField classNameInput = new TextField();
        classNameInput.setPromptText("Class Name");
        Button addClassBtn = new Button("Add Class");
        addClassBtn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");
        formGroup.getChildren().addAll(new Label("Class Name:"), classNameInput, addClassBtn);

        TableView<String> classTable = new TableView<>();
        classTable.setStyle("-fx-background-color: white;");

        mainContent.getChildren().addAll(titleLabel, formGroup, classTable);

        // Root Layout
        HBox root = new HBox();
        root.getChildren().addAll(sidebar, mainContent);

        Scene scene = new Scene(root, 900, 500);
        primaryStage.setTitle("Attendance Management");
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

    public static void main(String[] args) {
        launch(args);
    }
}

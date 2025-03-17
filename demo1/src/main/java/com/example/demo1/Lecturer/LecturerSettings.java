package com.example.demo1.Lecturer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LecturerSettings extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lecturer Settings");

        // Sidebar
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-padding: 10px;");

        Label portalLabel = new Label("Lecturer Portal");
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

        // Settings Form
        VBox settingsContainer = new VBox(10);
        settingsContainer.setPadding(new Insets(20));
        settingsContainer.setStyle("-fx-background-color: white; -fx-padding: 20px; -fx-border-radius: 10px;");

        Label titleLabel = new Label("Lecturer Settings");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label nameLabel = new Label("Full Name:");
        TextField nameField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label staffIdLabel = new Label("Staff ID:");
        TextField staffIdField = new TextField();

        Label passwordTitle = new Label("Change Password");
        Label oldPasswordLabel = new Label("Old Password:");
        PasswordField oldPasswordField = new PasswordField();
        Label newPasswordLabel = new Label("New Password:");
        PasswordField newPasswordField = new PasswordField();
        Button saveChangesBtn = new Button("Save Changes");

        settingsContainer.getChildren().addAll(titleLabel, nameLabel, nameField, emailLabel, emailField, staffIdLabel, staffIdField,
                passwordTitle, oldPasswordLabel, oldPasswordField, newPasswordLabel, newPasswordField, saveChangesBtn);

        // Layout
        HBox root = new HBox();
        root.getChildren().addAll(sidebar, settingsContainer);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
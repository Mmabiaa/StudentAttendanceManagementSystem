package com.example.demo1.Student;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StudentSettingsView extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Sidebar
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 250px;");

        Label title = new Label("Student Portal");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        Button homeBtn = createSidebarButton("Home");
        Button attendanceBtn = createSidebarButton("Attendance");
        Button assessmentsBtn = createSidebarButton("Assessments");
        Button settingsBtn = createSidebarButton("Settings");
        Button logoutBtn = createSidebarButton("Logout");

        sidebar.getChildren().addAll(title, homeBtn, attendanceBtn, assessmentsBtn, settingsBtn, logoutBtn);

        // Settings Container
        VBox settingsContainer = new VBox(10);
        settingsContainer.setPadding(new Insets(25));
        settingsContainer.setStyle("-fx-background-color: white; -fx-border-radius: 10; -fx-padding: 25; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);");
        settingsContainer.setMinWidth(400);

        Label settingsTitle = new Label("Student Settings");
        settingsTitle.setStyle("-fx-font-size: 22px; -fx-text-fill: #333;");

        TextField nameField = createTextField("Enter your name");
        TextField emailField = createTextField("Enter your email");
        TextField referenceField = createTextField("Enter your reference number");
        referenceField.setDisable(true);

        PasswordField oldPasswordField = createPasswordField("Enter old password");
        PasswordField newPasswordField = createPasswordField("Enter new password");

        Button saveButton = new Button("Save Changes");
        saveButton.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-padding: 12px; -fx-font-size: 16px;");
        saveButton.setOnMouseEntered(e -> saveButton.setStyle("-fx-background-color: #34495e; -fx-text-fill: white;"));
        saveButton.setOnMouseExited(e -> saveButton.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;"));

        settingsContainer.getChildren().addAll(settingsTitle, new Label("Full Name"), nameField, new Label("Email"), emailField,
                new Label("Reference Number"), referenceField, new Separator(), new Label("Change Password"),
                new Label("Old Password"), oldPasswordField, new Label("New Password"), newPasswordField, saveButton);

        // Layout
        HBox root = new HBox();
        root.getChildren().addAll(sidebar, settingsContainer);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Student Settings");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createSidebarButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-padding: 10px; -fx-font-size: 14px;");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #34495e; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white;"));
        return button;
    }

    private TextField createTextField(String prompt) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        textField.setStyle("-fx-padding: 10px; -fx-border-radius: 5; -fx-font-size: 14px; -fx-border-color: #ccc;");
        return textField;
    }

    private PasswordField createPasswordField(String prompt) {
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(prompt);
        passwordField.setStyle("-fx-padding: 10px; -fx-border-radius: 5; -fx-font-size: 14px; -fx-border-color: #ccc;");
        return passwordField;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

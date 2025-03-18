package com.example.demo1.Student;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class StudentSettingsView extends Application {
    private static final String STUDENT_CREDENTIALS_FILE = "student_info.json";

    @Override
    public void start(Stage primaryStage) {
        // Sidebar
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 250px;");

        Label title = new Label("Student Portal");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        Button homeBtn = createSidebarButton("Home", primaryStage, new StudentDashboard());
        Button attendanceBtn = createSidebarButton("Attendance", primaryStage, new StudentAttendanceView());
        Button assessmentsBtn = createSidebarButton("Assessments", primaryStage, new StudentAssessmentView());
        Button settingsBtn = createSidebarButton("Settings", primaryStage, new StudentSettingsView());
        Button logoutBtn = createSidebarButton("Logout", primaryStage, new StudentLogin());

        sidebar.getChildren().addAll(title, homeBtn, attendanceBtn, assessmentsBtn, settingsBtn, logoutBtn);

        // Settings Container
        VBox settingsContainer = new VBox(10);
        settingsContainer.setPadding(new Insets(25));
        settingsContainer.setStyle("-fx-background-color: white; -fx-border-radius: 10; -fx-padding: 25; "
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);");
        settingsContainer.setMinWidth(400);
        settingsContainer.setAlignment(Pos.CENTER); // Center the content

        Label settingsTitle = new Label("Student Settings");
        settingsTitle.setStyle("-fx-font-size: 22px; -fx-text-fill: #333;");

        TextField nameField = createTextField("Enter your name");
        TextField emailField = createTextField("Enter your email");
        TextField referenceField = createTextField("Enter your reference number"); // Editable now

        PasswordField oldPasswordField = createPasswordField("Enter old password");
        PasswordField newPasswordField = createPasswordField("Enter new password");

        Button saveButton = new Button("Save Changes");
        saveButton.setStyle(
                "-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-padding: 12px; -fx-font-size: 16px;");
        saveButton.setOnMouseEntered(e -> saveButton.setStyle("-fx-background-color: #34495e; -fx-text-fill: white;"));
        saveButton.setOnMouseExited(e -> saveButton.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;"));

        saveButton.setOnAction(e -> {
            try {
                saveStudentCredentials(nameField.getText(), emailField.getText(), referenceField.getText(),
                        newPasswordField.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Credentials Saved");
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to Save Credentials");
                alert.showAndWait();
            }
        });

        settingsContainer.getChildren().addAll(settingsTitle, new Label("Full Name"), nameField,
                new Label("Email"), emailField, new Label("Reference Number"), referenceField,
                new Separator(), new Label("Change Password"), new Label("Old Password"), oldPasswordField,
                new Label("New Password"), newPasswordField, saveButton);

        // Center the main content
        StackPane centerPane = new StackPane(settingsContainer);
        centerPane.setAlignment(Pos.CENTER);

        // Layout
        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.setCenter(centerPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Student Settings");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Load existing credentials if available
        try {
            loadStudentCredentials(nameField, emailField, referenceField);
        } catch (Exception ex) {
            System.out.println("No existing credentials found.");
        }
    }

    private void saveStudentCredentials(String name, String email, String reference, String password) throws IOException {
        JSONObject studentInfo = new JSONObject();
        studentInfo.put("name", name);
        studentInfo.put("email", email);
        studentInfo.put("reference", reference);
        studentInfo.put("password", password);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_CREDENTIALS_FILE))) {
            writer.write(studentInfo.toString(4)); // Pretty print JSON
        }
    }

    private void loadStudentCredentials(TextField nameField, TextField emailField, TextField referenceField) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_CREDENTIALS_FILE))) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONObject studentInfo = new JSONObject(tokener);

            nameField.setText(studentInfo.getString("name"));
            emailField.setText(studentInfo.getString("email"));
            referenceField.setText(studentInfo.getString("reference"));
        }
    }

    private Button createSidebarButton(String text, Stage primaryStage, Application targetView) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: transparent; -fx-text-fill: white; -fx-padding: 10px; -fx-font-size: 14px;");
        button.setMaxWidth(Double.MAX_VALUE);
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #34495e; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white;"));
        button.setOnAction(e -> {
            try {
                targetView.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
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

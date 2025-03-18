package com.example.demo1.Lecturer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class LecturerSettings extends Application {

    private static final String LECTURER_INFO_FILE = "lecturer_info.json";
    private static final String JSON_NAME_KEY = "name";
    private static final String JSON_EMAIL_KEY = "email";
    private static final String JSON_STAFF_ID_KEY = "staffId";


    private TextField nameField = new TextField();
    private TextField emailField = new TextField();
    private TextField staffIdField = new TextField();
    private PasswordField oldPasswordField = new PasswordField();
    private PasswordField newPasswordField = new PasswordField();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lecturer Settings");

        // Sidebar
        VBox sidebar = createSidebar(primaryStage);

        // Main Content
        StackPane settingsPane = new StackPane(createSettingsContent());
        settingsPane.setAlignment(Pos.CENTER);
        settingsPane.setPadding(new Insets(20));

        // Layout
        HBox root = new HBox();
        root.getChildren().addAll(sidebar, settingsPane);
        HBox.setHgrow(settingsPane, Priority.ALWAYS);

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createSidebar(Stage primaryStage) {
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50;");
        sidebar.setPrefWidth(220);

        Label portalLabel = new Label("Lecturer Portal");
        portalLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        Button homeBtn = createSidebarButton("🏠 Home", primaryStage);
        Button classesBtn = createSidebarButton("📚 Classes", primaryStage);
        Button attendanceBtn = createSidebarButton("👥 Attendance", primaryStage);
        Button assessmentsBtn = createSidebarButton("📝 Assessments", primaryStage);
        Button settingsBtn = createSidebarButton("⚙ Settings", primaryStage);
        Button logoutBtn = createSidebarButton("🔓 Logout", primaryStage);

        sidebar.getChildren().addAll(portalLabel, homeBtn, classesBtn, attendanceBtn, assessmentsBtn, settingsBtn,
                logoutBtn);
        return sidebar;
    }

    private VBox createSettingsContent() {
        VBox settingsContainer = new VBox(10);
        settingsContainer.setPadding(new Insets(20));
        settingsContainer.setStyle(
                "-fx-background-color: white; -fx-padding: 30px; -fx-border-radius: 10px; -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.2), 10, 0, 0, 5);");
        settingsContainer.setPrefWidth(500);

        Label titleLabel = new Label("Lecturer Settings");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label nameLabel = new Label("Full Name:");
        nameField.setPrefWidth(300);

        Label emailLabel = new Label("Email:");
        emailField.setPrefWidth(300);

        Label staffIdLabel = new Label("Staff ID:");
        staffIdField.setPrefWidth(300);

        Label passwordTitle = new Label("Change Password");
        passwordTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Label oldPasswordLabel = new Label("Old Password:");
        oldPasswordField.setPrefWidth(300);

        Label newPasswordLabel = new Label("New Password:");
        newPasswordField.setPrefWidth(300);

        Button saveChangesBtn = new Button("Save Changes");
        saveChangesBtn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-size: 14px;");

        // Save changes action
        saveChangesBtn.setOnAction(e -> saveSettings());

        settingsContainer.getChildren().addAll(titleLabel, nameLabel, nameField, emailLabel, emailField, staffIdLabel,
                staffIdField, passwordTitle, oldPasswordLabel, oldPasswordField, newPasswordLabel, newPasswordField,
                saveChangesBtn);

        return settingsContainer;
    }

    private void saveSettings() {
        String name = nameField.getText();
        String email = emailField.getText();
        String staffId = staffIdField.getText();
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();

        // Save settings to JSON file
        JSONObject lecturerInfo = new JSONObject();
        lecturerInfo.put(JSON_NAME_KEY, name);
        lecturerInfo.put(JSON_EMAIL_KEY, email);
        lecturerInfo.put(JSON_STAFF_ID_KEY, staffId);

        try (FileWriter fileWriter = new FileWriter(LECTURER_INFO_FILE)) {
            fileWriter.write(lecturerInfo.toString(2)); // Use toString(2) for pretty printing
            System.out.println("Settings saved to " + LECTURER_INFO_FILE);
        } catch (IOException e) {
            System.err.println("Error writing settings to file: " + e.getMessage());
        }

        // Here, you would typically save the settings to a database or a file.
        // For demonstration purposes, let's just print the values to the console.
        System.out.println("Saving settings:");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Staff ID: " + staffId);

        // In a real application, you'd want to validate the old password
        // before changing it to the new password.
        if (!oldPassword.isEmpty() && !newPassword.isEmpty()) {
            System.out.println("Changing password...");
            // Code to change password would go here.
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Settings Saved");
        alert.setHeaderText(null);
        alert.setContentText("Settings have been saved successfully.");
        alert.showAndWait();
    }

    private Button createSidebarButton(String text, Stage primaryStage) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setPrefWidth(180);

        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #34495e; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white;"));

        // Navigation logic
        button.setOnAction(e -> {
            switch (text) {
                case "🏠 Home":
                    new LecturerDashboard().start(primaryStage);
                    break;
                case "📚 Classes":
                    new ClassManagement().start(primaryStage);
                    break;
                case "👥 Attendance":
                    new AttendanceManagement().start(primaryStage);
                    break;
                case "📝 Assessments":
                    new AssessmentManagement().start(primaryStage);
                    break;
                case "⚙ Settings":
                    new LecturerSettings().start(primaryStage);
                    break;
                case "🔓 Logout":
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

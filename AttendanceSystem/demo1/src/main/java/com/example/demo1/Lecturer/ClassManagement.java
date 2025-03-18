package com.example.demo1.Lecturer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClassManagement extends Application {

    private ObservableList<String> classesList = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        // Sidebar
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 250px;");

        Label portalTitle = new Label("Lecturer Portal");
        portalTitle.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        Button homeBtn = createSidebarButton("Home", primaryStage);
        Button classesBtn = createSidebarButton("Classes", primaryStage);
        Button attendanceBtn = createSidebarButton("Attendance", primaryStage);
        Button assessmentsBtn = createSidebarButton("Assessments", primaryStage);
        Button settingsBtn = createSidebarButton("Settings", primaryStage);
        Button logoutBtn = createSidebarButton("Logout", primaryStage);

        sidebar.getChildren().addAll(portalTitle, homeBtn, classesBtn, attendanceBtn, assessmentsBtn, settingsBtn,
                logoutBtn);

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

        ListView<String> classList = new ListView<>(classesList);
        classList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        Button removeClassBtn = new Button("Remove Class");
        removeClassBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");

        // Add some sample classes
        classesList.add("Programming with Java");
        classesList.add("Discrete Mathematics");
        classesList.add("Psychology");

        // Action listener for add class button
        addClassBtn.setOnAction(e -> {
            String className = classNameInput.getText();
            if (!className.isEmpty()) {
                classesList.add(className);
                classNameInput.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Enter a Class Name");
                alert.showAndWait();
            }
        });

        // Action listener for remove class button
        removeClassBtn.setOnAction(e -> {
            String selectedClass = classList.getSelectionModel().getSelectedItem();
            if (selectedClass != null) {
                classesList.remove(selectedClass);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Select a Class to Remove");
                alert.showAndWait();
            }
        });

        mainContent.getChildren().addAll(titleLabel, formGroup, classList, removeClassBtn);

        // Root Layout
        HBox root = new HBox();
        root.getChildren().addAll(sidebar, mainContent);

        Scene scene = new Scene(root, 900, 500);
        primaryStage.setTitle("Class Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createSidebarButton(String text, Stage primaryStage) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setMaxWidth(Double.MAX_VALUE);

        // Button Hover Effect
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #34495e; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white;"));

        // Define Button Actions
        button.setOnAction(e -> {
            switch (text) {
                case "Home":
                    new LecturerDashboard().start(primaryStage); // Load Home Page
                    break;
                case "Classes":
                    new ClassManagement().start(primaryStage); // Stay on the same page
                    break;
                case "Attendance":
                    new AttendanceManagement().start(primaryStage); // Load Attendance Page
                    break;
                case "Assessments":
                    new AssessmentManagement().start(primaryStage); // Load Assessment Page
                    break;
                case "Settings":
                    new LecturerSettings().start(primaryStage); // Load Settings Page
                    break;
                case "Logout":
                    primaryStage.close(); // Close the application
                    break;
            }
        });

        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

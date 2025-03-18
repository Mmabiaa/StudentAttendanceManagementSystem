package com.example.demo1.Lecturer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AttendanceManagement extends Application {
    private ObservableList<String> attendanceList;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lecturer Dashboard - Attendance Management");

        // Sidebar Navigation
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 200px;");

        Label portalLabel = new Label("Student Portal");
        portalLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        Button homeBtn = createSidebarButton("🏠 Home", primaryStage);
        Button classesBtn = createSidebarButton("📚 Classes", primaryStage);
        Button attendanceBtn = createSidebarButton("📋 Attendance", primaryStage);
        Button assessmentsBtn = createSidebarButton("📝 Assessments", primaryStage);
        Button settingsBtn = createSidebarButton("⚙ Settings", primaryStage);
        Button logoutBtn = createSidebarButton("⬅ Logout", primaryStage);

        sidebar.getChildren().addAll(portalLabel, homeBtn, classesBtn, attendanceBtn, assessmentsBtn, settingsBtn,
                logoutBtn);

        // Main Content
        VBox mainContent = createAttendanceContent();

        // Root Layout
        HBox root = new HBox();
        root.getChildren().addAll(sidebar, mainContent);
        HBox.setHgrow(mainContent, Priority.ALWAYS);

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
        primaryStage.show();
    }

    private VBox createAttendanceContent() {
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));
        mainContent.setStyle("-fx-background-color: #f8f9fa;");

        Label titleLabel = new Label("Attendance Management");
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        Label markAttendanceLabel = new Label("Mark Attendance");
        markAttendanceLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ComboBox<String> studentDropdown = new ComboBox<>(getSampleStudents());
        studentDropdown.setPromptText("Select Student");

        Button markPresentBtn = new Button("Mark Present");
        markPresentBtn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");

        Button markAbsentBtn = new Button("Mark Absent");
        markAbsentBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");

        // Initialize attendance list
        attendanceList = getSampleAttendance();

        ListView<String> attendanceListView = new ListView<>(attendanceList);

        // Action listener for mark present button
        markPresentBtn.setOnAction(e -> {
            String selectedStudent = studentDropdown.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                updateAttendanceStatus(selectedStudent, "Present", attendanceListView);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Select a Student");
                alert.showAndWait();
            }
        });

        // Action listener for mark absent button
        markAbsentBtn.setOnAction(e -> {
            String selectedStudent = studentDropdown.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                updateAttendanceStatus(selectedStudent, "Absent", attendanceListView);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Select a Student");
                alert.showAndWait();
            }
        });

        VBox form = new VBox(10, markAttendanceLabel, studentDropdown, markPresentBtn, markAbsentBtn);

        Label attendanceListLabel = new Label("Attendance Records");
        attendanceListLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        mainContent.getChildren().addAll(titleLabel, form, attendanceListLabel, attendanceListView);

        return mainContent;
    }

    private void updateAttendanceStatus(String studentName, String status, ListView<String> attendanceListView) {
        ObservableList<String> items = attendanceListView.getItems();
        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            if (item.startsWith(studentName)) {
                items.set(i, studentName + " - " + status);
                return;
            }
        }
        // If student is not in the list, add them
        items.add(studentName + " - " + status);
    }

    private Button createSidebarButton(String text, Stage primaryStage) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setMaxWidth(Double.MAX_VALUE);

        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #34495e; -fx-text-fill: white;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white;"));

        button.setOnAction(e -> {
            switch (text) {
                case "🏠 Home":
                    new LecturerDashboard().start(primaryStage);
                    break;
                case "📚 Classes":
                    new ClassManagement().start(primaryStage);
                    break;
                case "📋 Attendance":
                    new AttendanceManagement().start(primaryStage);
                    break;
                case "📝 Assessments":
                    new AssessmentManagement().start(primaryStage);
                    break;
                case "⚙ Settings":
                    new LecturerSettings().start(primaryStage);
                    break;
                case "⬅ Logout":
                    primaryStage.close();
                    break;
            }
        });

        return button;
    }

    private ObservableList<String> getSampleStudents() {
        return FXCollections.observableArrayList(
                "Antepim Awuah Vincent",
                "Benedicta Armah Hellen",
                "Oppong Prosper",
                "Bevelyn Sam",
                "Addai Micheal",
                "Boateng Prince Agyenim");
    }

    private ObservableList<String> getSampleAttendance() {
        return FXCollections.observableArrayList(
                "Antepim Awuah Vincent - Present",
                "Benedicta Armah Hellen - Present",
                "Oppong Prosper - Absent",
                "Boateng Prince Agyenim - Absent",
                "Bevelyn Sam - Absent",
                "Addai Micheal - Present");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

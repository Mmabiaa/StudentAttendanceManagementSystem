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

public class StudentAssessmentView extends Application {
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

        Label heading = new Label("Assessments");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        TableView<AssessmentRecord> table = new TableView<>();

        TableColumn<AssessmentRecord, String> courseCol = new TableColumn<>("Course");
        courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));

        TableColumn<AssessmentRecord, String> typeCol = new TableColumn<>("Assessment Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<AssessmentRecord, String> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));

        TableColumn<AssessmentRecord, String> gradeCol = new TableColumn<>("Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

        table.getColumns().addAll(courseCol, typeCol, scoreCol, gradeCol);

        table.getItems().addAll(
                new AssessmentRecord("Programming with Java", "Quiz", "15", "B"),
                new AssessmentRecord("Computer Graphics", "Quiz 2", "15", "A"),
                new AssessmentRecord("Advanced Database", "Final Exam", "70%", "A"),
                new AssessmentRecord(" ", "Attendance", "10", "")
        );

        mainContent.getChildren().addAll(heading, table);

        // Layout
        BorderPane layout = new BorderPane();
        layout.setLeft(sidebar);
        layout.setCenter(mainContent);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setTitle("Student Assessment View");
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

class AssessmentRecord {
    private String course;
    private String type;
    private String score;
    private String grade;

    public AssessmentRecord(String course, String type, String score, String grade) {
        this.course = course;
        this.type = type;
        this.score = score;
        this.grade = grade;
    }

    public String getCourse() { return course; }
    public String getType() { return type; }
    public String getScore() { return score; }
    public String getGrade() { return grade; }
}

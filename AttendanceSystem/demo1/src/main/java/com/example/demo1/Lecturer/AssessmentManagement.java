package com.example.demo1.Lecturer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class AssessmentManagement extends Application {
    private ObservableList<Assessment> assessmentsList = FXCollections.observableArrayList();
    private TableView<Assessment> tableView; // TableView instance

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lecturer Dashboard - Assessment Management");

        // Sidebar Navigation
        VBox sidebar = createSidebar(primaryStage);

        // Main Content
        VBox mainContent = createAssessmentContent();

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

    private VBox createSidebar(Stage primaryStage) {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50; -fx-pref-width: 200px;");

        Label portalLabel = new Label("Lecturer Portal");
        portalLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        Button homeBtn = createSidebarButton("🏠 Home", primaryStage);
        Button classesBtn = createSidebarButton("📚 Classes", primaryStage);
        Button attendanceBtn = createSidebarButton("📋 Attendance", primaryStage);
        Button assessmentsBtn = createSidebarButton("📝 Assessments", primaryStage);
        Button settingsBtn = createSidebarButton("⚙ Settings", primaryStage);
        Button logoutBtn = createSidebarButton("⬅ Logout", primaryStage);

        sidebar.getChildren().addAll(portalLabel, homeBtn, classesBtn, attendanceBtn, assessmentsBtn, settingsBtn,
                logoutBtn);
        return sidebar;
    }

    private VBox createAssessmentContent() {
        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));
        mainContent.setStyle("-fx-background-color: #f8f9fa;");

        Label titleLabel = new Label("Assessment Management");
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        // Add Assessment Form
        GridPane form = createAssessmentForm();

        // Assessment Table
        tableView = createAssessmentTable(); // Assign TableView instance

        mainContent.getChildren().addAll(titleLabel, form, tableView);
        return mainContent;
    }

    private GridPane createAssessmentForm() {
        GridPane form = new GridPane();
        form.setVgap(10);
        form.setHgap(10);
        form.setPadding(new Insets(10));

        TextField titleField = new TextField();
        DatePicker datePicker = new DatePicker();
        TextArea descArea = new TextArea();
        descArea.setPrefRowCount(3);

        Button addAssessmentBtn = new Button("Add Assessment");
        addAssessmentBtn.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");

        // Form layout
        form.add(new Label("Title:"), 0, 0);
        form.add(titleField, 1, 0);
        form.add(new Label("Due Date:"), 0, 1);
        form.add(datePicker, 1, 1);
        form.add(new Label("Description:"), 0, 2);
        form.add(descArea, 1, 2);
        form.add(addAssessmentBtn, 1, 3);

        // Add assessment handler
        addAssessmentBtn.setOnAction(e -> {
            if (validateInput(titleField, datePicker, descArea)) {
                assessmentsList.add(new Assessment(
                        titleField.getText(),
                        datePicker.getValue().format(DateTimeFormatter.ISO_DATE),
                        descArea.getText()
                ));
                clearForm(titleField, datePicker, descArea);
            }
        });

        return form;
    }

    private TableView<Assessment> createAssessmentTable() {
        TableView<Assessment> tableView = new TableView<>();

        // Table Columns
        TableColumn<Assessment, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Assessment, String> dateColumn = new TableColumn<>("DueDate");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        TableColumn<Assessment, String> descColumn = new TableColumn<>("Description");
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Actions Column
        TableColumn<Assessment, Void> actionsColumn = new TableColumn<>("Actions");
        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                // Style buttons
                editButton.setStyle("-fx-background-color: orange; -fx-text-fill: white;");
                deleteButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white;");

                // Edit Button Action
                editButton.setOnAction(event -> {
                    Assessment assessment = getTableRow().getItem();
                    if (assessment != null) {
                        openEditDialog(assessment);
                    }
                });

                // Delete Button Action
                deleteButton.setOnAction(event -> {
                    Assessment assessment = getTableRow().getItem();
                    if (assessment != null) {
                        assessmentsList.remove(assessment);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : new HBox(5, editButton, deleteButton));
            }
        });

        tableView.getColumns().addAll(titleColumn, dateColumn, descColumn, actionsColumn);
        tableView.setItems(assessmentsList);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableView;
    }

    private boolean validateInput(TextField title, DatePicker date, TextArea description) {
        if (title.getText().isEmpty() || date.getValue() == null || description.getText().isEmpty()) {
            showAlert("Error", "All fields are required!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void clearForm(TextField title, DatePicker date, TextArea description) {
        title.clear();
        date.setValue(null);
        description.clear();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.showAndWait();
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

    private void openEditDialog(Assessment assessment) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Edit Assessment");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        TextField titleField = new TextField(assessment.getTitle());
        DatePicker datePicker = new DatePicker(LocalDate.parse(assessment.getDueDate()));
        TextArea descArea = new TextArea(assessment.getDescription());

        gridPane.add(new Label("Title:"), 0, 0);
        gridPane.add(titleField, 1, 0);
        gridPane.add(new Label("Due Date:"), 0, 1);
        gridPane.add(datePicker, 1, 1);
        gridPane.add(new Label("Description:"), 0, 2);
        gridPane.add(descArea, 1, 2);

        dialog.getDialogPane().setContent(gridPane);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                if (!validateEditInput(titleField, datePicker, descArea)) return;

                assessment.setTitle(titleField.getText());
                assessment.setDueDate(datePicker.getValue().format(DateTimeFormatter.ISO_DATE));
                assessment.setDescription(descArea.getText());
                tableView.refresh(); // Refresh the table to reflect changes
            }
        });
    }

    private boolean validateEditInput(TextField title, DatePicker date, TextArea description) {
        if (title.getText().isEmpty() || date.getValue() == null || description.getText().isEmpty()) {
            showAlert("Error", "All fields are required!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    public static class Assessment {
        private String title;
        private String dueDate;
        private String description;

        public Assessment(String title, String dueDate, String description) {
            this.title = title;
            this.dueDate = dueDate;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDueDate() {
            return dueDate;
        }

        public String getDescription() {
            return description;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

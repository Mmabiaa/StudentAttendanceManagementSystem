package com.example.demo1.Student;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class StudentLogin extends Application {

        private Stage primaryStage;

        @Override
        public void start(Stage primaryStage) {
                try {
                        this.primaryStage = primaryStage;
                        showLoginPage();
                } catch (Exception e) {
                        System.err.println("Error in start method: " + e.getMessage());
                }
        }

        // Method to create a background layout
        private StackPane createBackgroundLayout() {
                try {
                        URL backgroundImageUrl = getClass().getResource("/background.jpg");
                        if (backgroundImageUrl != null) {
                                BackgroundImage backgroundImage = new BackgroundImage(
                                        new Image(backgroundImageUrl.toExternalForm()),
                                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.CENTER,
                                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true));

                                StackPane mainLayout = new StackPane();
                                mainLayout.setBackground(new Background(backgroundImage));
                                return mainLayout;
                        } else {
                                System.err.println("Error: Background image not found.");
                                return new StackPane(); // Return an empty StackPane if image is missing
                        }
                } catch (Exception e) {
                        System.err.println("Error creating background layout: " + e.getMessage());
                        return new StackPane();
                }
        }

        // Method to create a styled form container
        private VBox createFormContainer(String titleText, Control... controls) {
                Label title = new Label(titleText);
                title.setFont(Font.font("Arial", 24));
                title.setTextFill(Color.BLACK);

                URL logoUrl = getClass().getResource("/UMaT logo.jpg");
                ImageView logo = null;
                if (logoUrl != null) {
                        logo = new ImageView(new Image(logoUrl.toExternalForm()));
                        logo.setFitWidth(80);
                        logo.setFitHeight(80);
                } else {
                        System.err.println("Error: Logo image not found.");
                }

                VBox formBox = new VBox(10);
                if (logo != null) {
                        formBox.getChildren().add(logo);
                }
                formBox.getChildren().add(title);
                formBox.getChildren().addAll(controls);
                formBox.setAlignment(Pos.CENTER);
                formBox.setPadding(new Insets(20));
                formBox.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 0.9); -fx-border-color: lightgray; -fx-border-width: 1; -fx-border-radius: 10; -fx-padding: 30px;");
                formBox.setMaxWidth(400);

                VBox wrapper = new VBox(formBox);
                wrapper.setAlignment(Pos.CENTER);
                wrapper.setPadding(new Insets(50));

                return wrapper;
        }

        // Student class to hold student details
        public static class Student {
                @JsonProperty("fullName")
                private String fullName;

                @JsonProperty("email")
                private String email;

                @JsonProperty("username")
                private String username;

                @JsonProperty("password")
                private String password;

                // No-arg constructor for Jackson
                public Student() {}

                // Constructor with arguments
                @JsonCreator
                public Student(@JsonProperty("fullName") String fullName, @JsonProperty("email") String email,
                               @JsonProperty("username") String username, @JsonProperty("password") String password) {
                        this.fullName = fullName;
                        this.email = email;
                        this.username = username;
                        this.password = password;
                }

                // Getters and setters
                public String getFullName() {
                        return fullName;
                }

                public void setFullName(String fullName) {
                        this.fullName = fullName;
                }

                public String getEmail() {
                        return email;
                }

                public void setEmail(String email) {
                        this.email = email;
                }

                public String getUsername() {
                        return username;
                }

                public void setUsername(String username) {
                        this.username = username;
                }

                public String getPassword() {
                        return password;
                }

                public void setPassword(String password) {
                        this.password = password;
                }
        }

        // Login Page
        private void showLoginPage() {
                try {
                        primaryStage.setTitle("Login");

                        TextField usernameField = new TextField();
                        usernameField.setPromptText("Student Reference Number/ID");
                        usernameField.setPrefWidth(300);

                        PasswordField passwordField = new PasswordField();
                        passwordField.setPromptText("Password");
                        passwordField.setPrefWidth(300);

                        CheckBox rememberMeCheckBox = new CheckBox("Remember me");

                        Hyperlink forgotPassword = new Hyperlink("Forgot password?");
                        forgotPassword.setTextFill(Color.BLUE);
                        forgotPassword.setOnAction(e -> showForgotPasswordPage());

                        Button loginButton = new Button("Login");
                        loginButton.setStyle(
                                "-fx-background-color: #008000; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 16px;");
                        loginButton.setOnAction(e -> {
                                String username = usernameField.getText();
                                String password = passwordField.getText();

                                if (authenticateUser(username, password)) {
                                        System.out.println("Login successful");
                                        // Redirect to StudentDashboard with username
                                        StudentDashboard dashboard = new StudentDashboard();
                                        dashboard.start(primaryStage, username);
                                } else {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText("Invalid Credentials");
                                        alert.setContentText("Username or password is incorrect.");
                                        alert.showAndWait();
                                }
                        });

                        Hyperlink registerLink = new Hyperlink("Don’t have an account? Register now");
                        registerLink.setTextFill(Color.BLUE);
                        registerLink.setOnAction(e -> showRegistrationPage());

                        VBox formContainer = createFormContainer("Login", usernameField, passwordField, rememberMeCheckBox,
                                forgotPassword, loginButton, registerLink);
                        StackPane mainLayout = createBackgroundLayout();
                        mainLayout.getChildren().add(formContainer);

                        primaryStage.setScene(new Scene(mainLayout, 800, 600));
                        primaryStage.show();
                } catch (Exception e) {
                        System.err.println("Error in showLoginPage method: " + e.getMessage());
                }
        }

        // Forgot Password Page
        private void showForgotPasswordPage() {
                try {
                        primaryStage.setTitle("Forgot Password");

                        TextField emailField = new TextField();
                        emailField.setPromptText("Enter your email");
                        emailField.setPrefWidth(300);

                        Button resetButton = new Button("Send Reset Link");
                        resetButton.setStyle(
                                "-fx-background-color: #008000; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 16px;");
                        resetButton.setOnAction(e -> System.out.println("Reset link sent to " + emailField.getText()));

                        Button backButton = new Button("Back to Login");
                        backButton.setOnAction(e -> showLoginPage());

                        VBox formContainer = createFormContainer("Reset Password", emailField, resetButton, backButton);
                        StackPane mainLayout = createBackgroundLayout();
                        mainLayout.getChildren().add(formContainer);

                        primaryStage.setScene(new Scene(mainLayout, 800, 600));
                        primaryStage.show();
                } catch (Exception e) {
                        System.err.println("Error in showForgotPasswordPage method: " + e.getMessage());
                }
        }

        // Registration Page
        private void showRegistrationPage() {
                try {
                        primaryStage.setTitle("Register");

                        TextField fullNameField = new TextField();
                        fullNameField.setPromptText("Full Name");
                        fullNameField.setPrefWidth(300);

                        TextField emailField = new TextField();
                        emailField.setPromptText("Email");
                        emailField.setPrefWidth(300);

                        TextField usernameField = new TextField();
                        usernameField.setPromptText("Student Reference Number/ID");
                        usernameField.setPrefWidth(300);

                        PasswordField passwordField = new PasswordField();
                        passwordField.setPromptText("Password");
                        passwordField.setPrefWidth(300);

                        PasswordField confirmPasswordField = new PasswordField();
                        confirmPasswordField.setPromptText("Confirm Password");
                        confirmPasswordField.setPrefWidth(300);

                        Button registerButton = new Button("Register");
                        registerButton.setStyle(
                                "-fx-background-color: #008000; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 16px;");
                        registerButton.setOnAction(e -> {
                                if (passwordField.getText().equals(confirmPasswordField.getText())) {
                                        Student student = new Student();
                                        student.setFullName(fullNameField.getText());
                                        student.setEmail(emailField.getText());
                                        student.setUsername(usernameField.getText());
                                        student.setPassword(passwordField.getText());

                                        saveStudentDetails(student);
                                        showLoginPage(); // Redirect to login page after registration
                                } else {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText("Password Mismatch");
                                        alert.setContentText("Passwords do not match. Please try again.");
                                        alert.showAndWait();
                                }
                        });

                        Button backButton = new Button("Back to Login");
                        backButton.setOnAction(e -> showLoginPage());

                        VBox formContainer = createFormContainer("Register", fullNameField, emailField, usernameField, passwordField,
                                confirmPasswordField, registerButton, backButton);
                        StackPane mainLayout = createBackgroundLayout();
                        mainLayout.getChildren().add(formContainer);

                        primaryStage.setScene(new Scene(mainLayout, 800, 600));
                        primaryStage.show();
                } catch (Exception e) {
                        System.err.println("Error in showRegistrationPage method: " + e.getMessage());
                }
        }

        // Method to save student details to a JSON file
        private void saveStudentDetails(Student student) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                        File directory = new File("demo1");
                        if (!directory.exists()) {
                                directory.mkdir(); // Create the directory if it doesn't exist
                        }

                        File file = new File(directory, "Student.json");
                        if (!file.exists()) {
                                file.createNewFile();
                        }

                        // Check if file is empty
                        if (file.length() == 0) {
                                // If empty, write the student as an array
                                mapper.writeValue(file, new Student[]{student});
                        } else {
                                // If not empty, read existing students, add new student, and write back
                                Student[] existingStudents = mapper.readValue(file, Student[].class);
                                Student[] updatedStudents = new Student[existingStudents.length + 1];
                                System.arraycopy(existingStudents, 0, updatedStudents, 0, existingStudents.length);
                                updatedStudents[updatedStudents.length - 1] = student;
                                mapper.writeValue(file, updatedStudents);
                        }
                } catch (IOException ex) {
                        System.err.println("Error saving student details: " + ex.getMessage());
                }
        }

        // Method to authenticate user
        private boolean authenticateUser(String username, String password) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                        File directory = new File("demo1");
                        File file = new File(directory, "Student.json");
                        if (file.exists()) {
                                Student[] students = mapper.readValue(file, Student[].class);
                                for (Student student : students) {
                                        if (student.getUsername().equals(username) && student.getPassword().equals(password)) {
                                                return true;
                                        }
                                }
                        }
                } catch (IOException ex) {
                        System.err.println("Error authenticating user: " + ex.getMessage());
                }
                return false;
        }

        public static void main(String[] args) {
                launch(args);
        }
}
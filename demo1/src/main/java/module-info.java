module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1.Student;
    exports com.example.demo1.Lecturer;
    opens com.example.demo1.Lecturer to javafx.fxml;
    opens com.example.demo1.Student to javafx.fxml;
}
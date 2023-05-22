module com.example {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.javafx to javafx.fxml;
    exports com.javafx;
}

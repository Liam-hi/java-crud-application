module com.example.myfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.myfx to javafx.fxml;
    exports com.example.myfx;
}
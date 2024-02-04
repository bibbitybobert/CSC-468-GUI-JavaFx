module com.example.catcafe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;

    opens book_robert.catcafe to javafx.fxml;
    exports book_robert.catcafe;
}
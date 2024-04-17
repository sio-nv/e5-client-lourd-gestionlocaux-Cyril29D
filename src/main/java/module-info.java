module m2l.desktop.gestion {
    requires javafx.controls;
    requires javafx.fxml;

    opens m2l.desktop.gestion to javafx.fxml;
    opens m2l.desktop.gestion.controller to javafx.fxml;
    opens m2l.desktop.gestion.model to javafx.base;


    exports m2l.desktop.gestion;
    exports m2l.desktop.gestion.controller;

    //modules requis par le projet
    requires mysql.connector.java;
    requires java.sql;
}